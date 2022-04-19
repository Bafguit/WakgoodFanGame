package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.HolySmiteAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class Test6 extends AbstractSkill {

    private static final String ID = "Test6";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.SILVER;
    private static final SkillTarget TARGET = SkillTarget.E_DF;
    private static final int ATK = 2;
    private static final int SPL = 1;

    public Test6(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(ATK);
        setBaseSpell(SPL, 1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new HolySmiteAction(owner, target, attack, spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
