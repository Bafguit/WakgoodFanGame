package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.StatusString;


public abstract class AbstractStatus implements Cloneable {

    public String id;
    public Sprite img;
    public Sprite imgBig;
    public String name;
    public String desc;
    public String[] exDesc;
    public StatusType type;
    public StatusString.StatusData data;
    public AbstractSkill.SkillTarget target;
    public AbstractEntity source;
    public AbstractEntity owner;
    public boolean hasAmount = false;
    public boolean canGoNegative = false;
    public int amount = 0;

    public AbstractStatus(String id, AbstractSkill.SkillTarget target, StatusType type) {
        this.id = id;
        img = FileHandler.statusImg.get(this.id);
        imgBig = FileHandler.statusImgBig.get(this.id);
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
        BUFF, DEBUFF
    }
}
