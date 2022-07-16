package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.StartAdvisorAction;

public class StartAdvisorSkill extends AbstractSkill {

    private static final String ID = "start";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.NONE;

    public StartAdvisorSkill() {
        super(ID, TYPE, RARITY, TARGET);
        passive = true;
        setBaseSpell(1, 1);
    }

    @Override
    public void use() {

    }

    public void atBattleEnd() {
        bot(new StartAdvisorAction(spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
