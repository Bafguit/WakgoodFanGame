package com.fastcat.labyrintale.abstracts;

public abstract class AbstractAbility {

    public String id;
    public final AbilityRarity rarity;
    public AbstractSkill.SkillTarget target;

    public AbstractAbility(String id, AbilityRarity rarity, AbstractSkill.SkillTarget target) {
        this.id = id;
        this.rarity = rarity;
        this.target = target;
    }

    public enum AbilityRarity {
        COMMON, UNCOMMON, ABSOLUTE
    }
}
