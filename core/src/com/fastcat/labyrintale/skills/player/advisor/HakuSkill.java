package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class HakuSkill extends AbstractSkill {

    private static final String ID = "haku";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.E_L;
    private static final int VALUE = 30;

    public HakuSkill() {
        super(ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE);
        disposable = true;
    }

    @Override
    public void use() {
        ActionHandler.bot(new AttackAction(owner, TARGET, attack, AttackAction.AttackType.HEAVY));
    }

    @Override
    protected void upgradeCard() {

    }
}
