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
    private static final int VALUE = 5;

    public Snipe(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE);
        setBaseValue(2, 1);
        setBaseCost(2);
    }

    @Override
    public void use() {}

    @Override
    public void onTarget(AbstractEntity e) {
        top(new AttackAction(new AbstractEntity.DamageInfo(this), e, AttackAction.AttackType.GUN));
    }

    @Override
    protected void upgradeCard() {}

    @Override
    public float showMultiply() {
        if (AbstractLabyrinth.cPanel.type == ControlPanel.ControlType.BATTLE
                && AbstractLabyrinth.cPanel.battlePanel.selected == this
                && Labyrintale.battleScreen.looking.size == 1) {
            return (float) ((owner.index + Labyrintale.battleScreen.looking.get(0).index) * (value - 1) + 6) / 6;
        }
        return 1.0f;
    }

    @Override
    public float attackMultiply(AbstractEntity target) {
        return (float) ((owner.index + target.index) * (value - 1) + 6) / 6;
    }
}
