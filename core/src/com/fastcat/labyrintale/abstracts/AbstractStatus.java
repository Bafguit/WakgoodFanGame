package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

public abstract class AbstractStatus implements Cloneable {

    private String id;

    public Sprite img;
    public String name;
    public String desc;
    public AbstractSkill.CardTarget target;

    public AbstractStatus(String id, Sprite img, AbstractSkill.CardTarget target) {
        this.id = id;
        this.img = img;
        this.target = target;
    }

    public void remove() {

    }

    public void onApply() {

    }

    public void onRemove() {

    }

    public void onUseCard(AbstractSkill card) {

    }

    public void onLoseBlock(int block) {

    }

    public int onGainBlock(int block) {
        return block;
    }

    public void atBattleEnd() {

    }

    public void onDamage(AbstractEntity actor, int damage) {

    }

    public void onHeal(int heal) {

    }
}
