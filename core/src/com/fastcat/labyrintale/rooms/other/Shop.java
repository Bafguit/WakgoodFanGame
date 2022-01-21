package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractAbility;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class Shop extends AbstractRoom {

    public SkillItem[] skills = new SkillItem[6];

    public Shop() {
        super(RoomType.SHOP);
        generateItems();
    }

    private static class SkillItem {
        public AbstractSkill skill;
        public int price;
    }
    private static void generateItems() {

    }
}
