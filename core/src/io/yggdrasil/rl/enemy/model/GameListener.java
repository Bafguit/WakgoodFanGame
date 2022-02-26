package io.yggdrasil.rl.enemy.model;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.event.events.actions.EntityActionEvent;
import com.fastcat.labyrintale.event.events.entity.EntityDeathEvent;
import com.fastcat.labyrintale.event.listener.EventHandler;
import com.fastcat.labyrintale.event.listener.EventListener;
import com.fastcat.labyrintale.misc.SimpleEntity;
import io.yggdrasil.rl.MasterTrainer;
import io.yggdrasil.rl.enemy.model.environment.GameEnvironment;
import io.yggdrasil.rl.player.model.training.Trainer;

public class GameListener extends Thread implements EventListener {

    public GameListener() {
        super("Enemy Game Listener");
        start();

    }


    @EventHandler
    public void onAction(final EntityActionEvent e) {
        synchronized (GameEnvironment.getCurrentAction()) {
            int reward = 0;
            for (int i = 0; i < e.getTargets().size(); i++) {
                AbstractEntity target = e.getTargets().get(i);
                if (target == null || !target.isAlive()) {
                    reward += 1;
                    continue;
                }

                SimpleEntity targetBefore = e.getTargetsBefore().get(i);
                reward += map(target.health - targetBefore.getHealth(), 0, target.maxHealth, 0, 2);
            }

            GameEnvironment.getCurrentAction().getActionResult().setReward(reward);
           GameEnvironment.getCurrentAction().notifyAll();

        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        Trainer firstTrainer = MasterTrainer.getInstance().getFirstPlayerTrainer();
        io.yggdrasil.rl.enemy.model.training.Trainer secondTrainer = MasterTrainer.getInstance().getSecondPlayerTrainer();
        if (firstTrainer.getPlayer().isDone() || secondTrainer.getPlayer().isDone()) {
            secondTrainer.getPlayer().reset();
        }

    }

    private int map(int value, int fromStart, int fromEnd, int toStart, int toEnd) {
        return (value - fromStart) / (fromEnd - fromStart) * (toEnd - toStart) + toStart;
    }
}

