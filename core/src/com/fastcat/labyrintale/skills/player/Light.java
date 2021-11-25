package com.fastcat.labyrintale.skills.player;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_HEAL;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_LIGHT;

public class Light extends AbstractSkill {

    private static final String ID = "Light";
    private static final Texture IMG = SKILL_LIGHT;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final int VALUE = 3;

    public Light(AbstractEntity e) {
        super(e, ID, IMG, CLASS, RARITY);
        setEnemyTarget(true, true, true, true);
        setBaseAttack(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new AttackAction(owner, getTargets(), attack, null));
    }

    @Override
    public void upgrade() {

    }
}
