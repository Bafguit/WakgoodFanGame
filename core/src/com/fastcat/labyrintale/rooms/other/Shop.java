package com.fastcat.labyrintale.rooms.other;

import com.fastcat.labyrintale.abstracts.AbstractAbility;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class Shop extends AbstractRoom {

    public SkillItem[] skills = new SkillItem[6];
    public RemoveItem remove;

    public Shop() {
        super(RoomType.SHOP);
        generateItems();
    }

    public abstract static class ShopItem {
        public int price;
        public ShopItem(int p) {
            price = p;
        }
    }

    private static class SkillItem extends ShopItem{
        public AbstractSkill skill;

        public SkillItem(AbstractSkill skill, boolean sale) {
            super(generateSkillPrice(skill, sale));
            this.skill = skill;
        }

        private static int generateSkillPrice(AbstractSkill skill, boolean sale) {
            float s = sale ? (AbstractLabyrinth.shopRandom.nextInt(5) + 3) * 0.1f : 1;
            int p;
            switch(skill.rarity) {
                case GOLD:
                    p = 150 + AbstractLabyrinth.shopRandom.nextInt(20);
                    break;
                case SILVER:
                    p = 100 + AbstractLabyrinth.shopRandom.nextInt(20);
                    break;
                default:
                    p = 50 + AbstractLabyrinth.shopRandom.nextInt(20);
            }
            return (int)(p * s);
        }
    }

    private static class RemoveItem extends ShopItem{
        public RemoveItem() {
            super(AbstractLabyrinth.getRemovePrice(true));
        }
    }

    private static void generateItems() {

    }
}
