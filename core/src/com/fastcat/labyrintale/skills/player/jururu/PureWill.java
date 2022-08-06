package com.fastcat.labyrintale.skills.player.jururu;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class PureWill extends AbstractSkill {

    private static final String ID = "PureWill";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
    private static final int VALUE = 1;

    public PureWill(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        disposable = true;
    }

    @Override
    public void use() {
        bot(new ApplyStatusAction(new ImmuneStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
