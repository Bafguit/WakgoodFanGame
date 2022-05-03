package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.strings.ItemString;

public class AbstractItem {

    public ItemString.ItemData data;
    public Sprite img;
    public String id;
    public String name;
    public String desc;
    public String flav;
    public AbstractPlayer owner;

    public AbstractItem(String id) {

    }

    public final void flash() {
        //TODO 소리 추가
        flash(owner);
    }

    public final void flash(AbstractEntity e) {
        //TODO 소리 추가
        EffectHandler.add(new UpIconEffect(e.animX, e.animY + Gdx.graphics.getHeight() * 0.2f, new Sprite(img.getTexture())));
    }

    public void startOfTurn() {

    }

    public void endOfTurn() {

    }

    public void onLoseBlock(int block) {

    }

    public int onGainBlock(int block) {
        return block;
    }

    public void atBattleEnd() {

    }

    public void onDamage(AbstractEntity target, int damage, AbstractEntity.DamageType type) {

    }

    public void onDamaged(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {

    }

    public int onAttack(AbstractEntity target, int damage, AbstractEntity.DamageType type) {
        return damage;
    }

    public int onAttacked(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        return damage;
    }

    public int showAttack(int base) {
        return base;
    }

    public int showSpell(int base) {
        return base;
    }

    public int calculateAttack(int base) {
        return base;
    }

    public int calculateSpell(int base) {
        return base;
    }

    public void onHeal(int heal) {

    }

    public void onDeath(AbstractEntity murder) {

    }

    public void onApplyStatus(AbstractStatus s) {

    }

    public void onGain() {

    }

    public void onRemove() {

    }

    public enum ItemRarity {
        STARTER, BRONZE, SILVER, GOLD, EVENT
    }
}
