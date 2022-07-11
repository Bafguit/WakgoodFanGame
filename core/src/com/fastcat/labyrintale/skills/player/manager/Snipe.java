package com.fastcat.labyrintale.skills.player.manager;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class Snipe extends AbstractSkill {

    private static final String ID = "Snipe";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 1;

    public Snipe(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new AttackAction(owner, e, attack, attack > 4 ? AttackAction.AttackType.HEAVY : AttackAction.AttackType.LIGHT));
    }

    @Override
    protected void upgradeCard() {

    }

    @Override
    public int calculateAttack(int a) {
        if(AbstractLabyrinth.cPanel.type == ControlPanel.ControlType.BATTLE) {
            if(AbstractLabyrinth.cPanel.battlePanel.selected == this && Labyrintale.battleScreen.looking.size == 1) {
                return a + owner.tempIndex + Labyrintale.battleScreen.looking.get(0).tempIndex;
            }
        }
        return a;
    }
}
