package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.getHexColor;

public abstract class AbstractStatus implements Cloneable {

    public String id;
    public Sprite img;
    public Sprite imgBig;
    public String name;
    public String desc;
    public AbstractSkill.CardTarget target;
    public AbstractEntity owner;
    public boolean hasAmount = false;
    public boolean canGoNegative = false;
    public int amount = 0;

    public AbstractStatus(String id, Sprite img, AbstractEntity o, AbstractSkill.CardTarget target) {
        this.id = id;
        this.img = img;
        owner = o;
        this.target = target;
    }

    public abstract String getDesc();

    protected void setAmount(int amt) {
        amount = amt;
        hasAmount = true;
    }


    public final void flash() {
        //TODO 소리 추가
        EffectHandler.add(new UpIconEffect(owner.animX, owner.animY + Gdx.graphics.getHeight() * 0.2f, new Sprite(img.getTexture())));
    }

    public final void flash(AbstractEntity e) {
        //TODO 소리 추가
        EffectHandler.add(new UpIconEffect(e.animX, e.animY + Gdx.graphics.getHeight() * 0.2f, new Sprite(img.getTexture())));
    }

    public void startOfTurn() {

    }

    public void endOfTurn() {

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

    public void onDamaged(AbstractEntity attacker, int damage) {

    }

    public void onHeal(int heal) {

    }

    public void onDeath(AbstractEntity murder) {

    }

    public final AbstractStatus cpy() {
        try {
            return (AbstractStatus) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
