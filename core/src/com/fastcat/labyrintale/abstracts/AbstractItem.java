package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.ItemString;

public class AbstractItem {

    public ItemString.ItemData data;
    public Sprite img;
    public String id;
    public String name;
    public String desc;
    public String flav;
    public AbstractPlayer owner;
    public ItemRarity rarity;

    public AbstractItem(String id, AbstractPlayer owner, ItemRarity rarity) {
        this.id = id;
        img = FileHandler.itemImg.get(this.id);
        data = StringHandler.itemString.get(this.id);
        name = data.NAME;
        desc = data.DESC;
        flav = data.FLAV;
        this.rarity = rarity;
        this.owner = owner;
    }

    public final void setOwner(AbstractPlayer owner) {
        this.owner = owner;
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

    public void atBattleStart() {

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

    public float onAttackMultiply(AbstractEntity target, int damage, AbstractEntity.DamageType type) {
        return 1.0f;
    }

    public float onAttackedMultiply(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        return 1.0f;
    }

    public int showAttack(int base) {
        return base;
    }

    public int showSpell(int base) {
        return base;
    }

    public float showAttackMultiply(int base) {
        return 1.0f;
    }

    public float showSpellMultiply(int base) {
        return 1.0f;
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

    protected final void top(AbstractAction a) {
        ActionHandler.top(a);
    }

    protected final void bot(AbstractAction a) {
        ActionHandler.bot(a);
    }

    @Override
    public final AbstractItem clone() {
        try {
            return (AbstractItem) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum ItemRarity {
        STARTER, BRONZE, SILVER, GOLD, BOSS, SHOP, SPECIAL
    }
}
