package io.yggdrasil.rl.enemy.model.environment;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import io.yggdrasil.rl.enemy.model.agent.GameAgent;
import io.yggdrasil.rl.enemy.model.state.CurrentAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameEnvironment {


    private static final CurrentAction currentAction = new CurrentAction();

    private static final Random random = new Random(4124);

    public GameEnvironment() {

    }

    public static CurrentAction getCurrentAction() {
        return currentAction;
    }

    public static int encodeSkill(AbstractSkill skill) {
        switch (skill.type) {
            case ATTACK: {
                return 1;
            }
            case DEFENCE: {
                return 2;
            }
            case SCHEME: {
                return 3;
            }
            default: {
                return 0;
            }
        }
    }

    public List<AbstractEntity> getEnemies() {
        if (!(Labyrintale.getCurScreen() instanceof BattleScreen))
            return new ArrayList<>();

        BattleScreen screen = (BattleScreen) Labyrintale.getCurScreen();

        //In second player view, a player is the enemy.
        List<AbstractEntity> enemies = new ArrayList<>();
        for (PlayerView view : screen.players)
            enemies.add(view.player);
        return enemies;
    }

    public List<AbstractEntity> getAllies() {
        if (!(Labyrintale.getCurScreen() instanceof BattleScreen))
            return new ArrayList<>();
        BattleScreen screen = (BattleScreen) Labyrintale.getCurScreen();
        //In second player view, an enemy is the player.
        List<AbstractEntity> allies = new ArrayList<>();
        for (EnemyView view : screen.enemies)
            allies.add(view.enemy);

        return allies;
    }


    public ActionResult doAction(int action) throws InterruptedException {
        synchronized (getCurrentAction()) {
            int entityIndex = action / 4;
            int skillIndex = action % 6;//6, 6, 6,6 ...로  되어있기에, 각 카드 구하려면 %4
            AbstractEntity ally = getAllies().get(entityIndex);
            AbstractSkill skill;
            if (ally.hand.length > skillIndex) {
                skill = ally.hand[skillIndex];
            } else {
                return new ActionResult(-1);
            }
            //Action for allies
            if (isSkillForEnemy(skill.target)) {
                //Cannot use skill for enemy to allies.
                return new ActionResult(-1);
            }
            skill.useCard();
          //  GameAgent.getLogger().info("Enemy: Wait for action completed...");
            getCurrentAction().setActionResult(new ActionResult(0));
            getCurrentAction().wait();
        }
        return getCurrentAction().getActionResult();
    }

    private boolean isSkillForAll(AbstractSkill.SkillTarget target) {
        return target == AbstractSkill.SkillTarget.ALL;
    }

    private boolean isSkillForEnemy(AbstractSkill.SkillTarget target) {
        return target == AbstractSkill.SkillTarget.E_L || target == AbstractSkill.SkillTarget.E_DF || target == AbstractSkill.SkillTarget.E_DL || target == AbstractSkill.SkillTarget.E_F;
    }

    public boolean areAlliesDead() {
        boolean flag = true;
        for (AbstractEntity entity : getAllies()) {
            if (entity.isAlive())
                flag = false;
        }
        return flag;
    }

    public boolean areEnemiesDead() {
        boolean flag = true;
        for (AbstractEntity entity : getEnemies()) {
            if (entity.isAlive())
                flag = false;
        }
        return flag;
    }
}
