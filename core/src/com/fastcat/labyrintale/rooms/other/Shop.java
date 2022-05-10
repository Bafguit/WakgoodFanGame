package com.fastcat.labyrintale.rooms.other;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractItem.ItemRarity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillRarity;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.GroupHandler.ItemGroup;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillRarity.*;
import static com.fastcat.labyrintale.handlers.GroupHandler.SkillGroup.*;

public class Shop extends AbstractRoom {

    public SkillItem[] skills = new SkillItem[6];
    public ItemItem[] items = new ItemItem[5];
    public RollItem roll;

    public Shop() {
        super(RoomType.SHOP);
        roll = new RollItem(this);
        generateSkills();
        generateItems();
    }

    public abstract static class ShopItem {
        public int price;
        public boolean isDone = false;
        public Sprite img;

        public ShopItem(int p) {
            price = p;
        }

        public final void takeItem() {
            take();
            isDone = true;
            AbstractLabyrinth.gold -= price;
        }

        protected abstract void take();
    }

    private static class RollItem extends ShopItem {

        private final Shop shop;

        public RollItem(Shop s) {
            super(50);
            shop = s;
            img = FileHandler.ui.get("REWARD_CARD");
        }

        @Override
        protected void take() {
            shop.generateSkills();
            shop.generateItems();
        }
    }

    private static class SkillItem extends ShopItem{
        public AbstractSkill skill;

        public SkillItem(SkillRarity r) {
            this(getRandomSkillByRarity(r));
        }

        public SkillItem(AbstractSkill skill) {
            super(generateSkillPrice(skill));
            this.skill = skill;
            img = this.skill.img;
        }

        private static int generateSkillPrice(AbstractSkill skill) {
            switch(skill.rarity) {
                case GOLD:
                    return 180 + shopRandom.nextInt(20) - 10;
                case SILVER:
                    return 120 + shopRandom.nextInt(20) - 10;
                default:
                    return 60 + shopRandom.nextInt(20) - 10;
            }
        }

        @Override
        protected void take() {

        }
    }

    private static class ItemItem extends ShopItem{
        public AbstractItem item;

        public ItemItem(ItemRarity r) {
            this(ItemGroup.getRandomItemByRarity(r));
        }

        public ItemItem(AbstractItem item) {
            super(generateSkillPrice(item));
            this.item = item;
            img = this.item.img;
        }

        private static int generateSkillPrice(AbstractItem i) {
            switch(i.rarity) {
                case GOLD:
                    return 180 + shopRandom.nextInt(20) - 10;
                case BRONZE:
                    return 60 + shopRandom.nextInt(20) - 10;
                default:
                    return 120 + shopRandom.nextInt(20) - 10;
            }
        }

        @Override
        protected void take() {

        }
    }

    public void generateSkills() {
        //스킬 생성
        int t = shopRandom.nextInt(100);
        int b, s;
        if(t >= 60) {
            b = 3;
            s = 2;
        } else if(t >= 40) {
            b = 2;
            s = 3;
        } else if(t >= 20) {
            b = 1;
            s = 4;
        } else if(t >= 10) {
            b = 2;
            s = 2;
        } else {
            b = 1;
            s = 3;
        }
        int g = 6 - b - s;
        Array<AbstractSkill> bs = GroupHandler.SkillGroup.getRandomSkillByRarity(BRONZE, b);
        Array<AbstractSkill> ss = GroupHandler.SkillGroup.getRandomSkillByRarity(SILVER, s);
        for(int i = 0; i < b; i++) {
            skills[i] = new SkillItem(bs.get(i));
        }
        for(int i = b; i < b + s; i++) {
            skills[i] = new SkillItem(ss.get(i));
        }
        if(g > 0) {
            Array<AbstractSkill> gs = GroupHandler.SkillGroup.getRandomSkillByRarity(GOLD, g);
            for(int i = b + s; i < 6; i++) {
                skills[i] = new SkillItem(gs.get(i));
            }
        }
    }

    public void generateItems() {
        //스킬 생성
        int t = shopRandom.nextInt(100);
        int b, s, g;
        if(t >= 70) {
            b = 3;
            s = 1;
            g = 0;
        } else if(t >= 40) {
            b = 2;
            s = 2;
            g = 0;
        } else if(t >= 20) {
            b = 3;
            s = 1;
            g = 0;
        } else if(t >= 10) {
            b = 2;
            s = 1;
            g = 1;
        } else {
            b = 1;
            s = 2;
            g = 1;
        }

        Array<AbstractItem> bs = ItemGroup.getRandomItemByRarity(ItemRarity.BRONZE, b);
        Array<AbstractItem> ss = ItemGroup.getRandomItemByRarity(ItemRarity.SILVER, b);
        for(int i = 0; i < b; i++) {
            items[i] = new ItemItem(bs.get(i));
        }
        for(int i = b; i < b + s; i++) {
            items[i] = new ItemItem(ss.get(i));
        }
        if(g > 0) {
            Array<AbstractItem> gs = ItemGroup.getRandomItemByRarity(ItemRarity.GOLD, b);
            for(int i = b + s; i < 4; i++) {
                items[i] = new ItemItem(gs.get(i));
            }
        }
        items[4] = new ItemItem(ItemRarity.SHOP);
    }
}
