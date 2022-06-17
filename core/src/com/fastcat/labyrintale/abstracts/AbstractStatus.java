package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.StatusString;
import com.fasterxml.jackson.annotation.JsonIgnore;


public abstract class AbstractStatus implements Cloneable {

    public String id;
    public Sprite img;
    public String name;
    public String desc;
    public String[] exDesc;
    public StatusType type;
    public StatusString.StatusData data;
    public AbstractSkill.SkillTarget target;
    public AbstractEntity owner;
    public boolean hasAmount = false;
    public boolean canGoNegative = false;
    public int amount = 0;

    public AbstractStatus(String id, AbstractSkill.SkillTarget target, StatusType type) {
        this.id = id;
        img = FileHandler.statusImg.get(this.id);
        data = StringHandler.statusString.get(this.id);
        name = data.NAME;
        desc = data.DESC;
        exDesc = data.DESC_B;
        this.target = target;
        this.type = type;
    }

    public abstract String getDesc();

    protected void setAmount(int amt) {
        amount = amt;
        hasAmount = true;
    }

    protected final void top(AbstractAction a) {
        ActionHandler.top(a);
    }

    protected final void bot(AbstractAction a) {
        ActionHandler.bot(a);
    }

    public final void flash() {
        //TODO 소리 추가
        flash(owner);
    }

    public final void flash(AbstractEntity e) {
        //TODO 소리 추가
        EffectHandler.add(new UpIconEffect(e.animX, e.animY + Gdx.graphics.getHeight() * 0.15f, img));
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

    public final AbstractStatus cpy() {
        try {
            return (AbstractStatus) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum StatusType {
        BUFF, DEBUFF, STATIC
    }
}
