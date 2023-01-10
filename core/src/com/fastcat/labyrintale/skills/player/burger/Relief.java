package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;

public class Relief extends AbstractSkill {

    private static final String ID = "Relief";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 3;

    public Relief(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 2);
    }

    @Override
    public void use() {}

    @Override
    public void onTarget(AbstractEntity e) {
        top(new HealAction(owner, e, spell));
        top(new MoveAction(e, owner, 3, 0.3f));
    }

    @Override
    protected void upgradeCard() {}
}
