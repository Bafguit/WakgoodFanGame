package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.HolySmiteAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class HolySmite extends AbstractSkill {

    private static final String ID = "HolySmite";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST_TWO;
    private static final int ATK = 2;
    private static final int SPL = 2;

    public HolySmite(AbstractEntity e) {
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
