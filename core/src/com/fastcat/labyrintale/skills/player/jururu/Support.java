package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.TempSpeedStatus;

public class Support extends AbstractSkill {

    private static final String ID = "Support";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;

    public Support(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(10);

    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new TempSpeedStatus(value), owner, e, false));
    }

    @Override
    protected void upgradeCard() {
        setBaseValue(value + 10);
    }
}
