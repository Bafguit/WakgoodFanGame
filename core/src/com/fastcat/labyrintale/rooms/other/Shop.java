package com.fastcat.labyrintale.rooms.other;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractSkill.SkillRarity;
import com.fastcat.labyrintale.rewards.SkillReward;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillRarity.*;
import static com.fastcat.labyrintale.handlers.GroupHandler.SkillGroup.*;

public class Shop extends AbstractRoom {

    public SkillItem[] skills = new SkillItem[6];

    public Shop() {
        super(RoomType.SHOP);
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
        }

        protected abstract void take();
    }

    private static class SkillItem extends ShopItem{
        public AbstractSkill skill;

        public SkillItem(SkillRarity r) {
            this(r, shopRandom.nextInt(10) < 2);
        }

        public SkillItem(SkillRarity r, boolean sale) {
            this(getRandomSkill(players[shopRandom.nextInt(4)], 1).get(0), sale);
        }

        public SkillItem(AbstractSkill skill, boolean sale) {
            super(generateSkillPrice(skill, sale));
            this.skill = skill;
            img = this.skill.img;
        }

        private static int generateSkillPrice(AbstractSkill skill, boolean sale) {
            float s = sale ? (shopRandom.nextInt(5) + 3) * 0.1f : 1;
            int p;
            switch(skill.rarity) {
                case GOLD:
                    p = 150 + shopRandom.nextInt(20);
                    break;
                case SILVER:
                    p = 100 + shopRandom.nextInt(20);
                    break;
                default:
                    p = 50 + shopRandom.nextInt(20);
            }
            return (int)(p * s);
        }

        @Override
        protected void take() {

        }
    }

    private void generateItems() {
        //스킬 생성
        int t = shopRandom.nextInt(100);
        int b, s;
        if(t >= 60) {
            b = 3;
            s = 2;
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
        for(int i = 0; i < 6; i++) {
            if(i < b) skills[i] = new SkillItem(BRONZE);
            else if(i < b + s) skills[i] = new SkillItem(SILVER);
            else skills[i] = new SkillItem(GOLD);
        }
    }
}
