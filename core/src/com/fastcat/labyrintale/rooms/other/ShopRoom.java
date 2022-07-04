package com.fastcat.labyrintale.rooms.other;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.abstracts.AbstractItem.ItemRarity;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.GroupHandler.ItemGroup;
import com.fastcat.labyrintale.screens.shop.ShopScreen;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;

import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;
import static com.fastcat.labyrintale.Labyrintale.shopScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;

public class ShopRoom extends AbstractRoom {

    public SkillItem[] skills = new SkillItem[6];
    public ItemItem[] items = new ItemItem[5];
    public RollItem roll;

    public ShopRoom() {
        super("Shop", RoomType.SHOP);
    }

    public void entry() {
        System.out.println("SHOP!");
        generateSkills();
        generateItems();
        roll = new RollItem(this);
        shopScreen = new ShopScreen(this);
        fadeOutAndChangeScreen(shopScreen);
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

        public abstract void setPanel();

        public final boolean canBuy() {
            return gold >= price;
        }
    }

    private static class RollItem extends ShopItem {

        private final ShopRoom shop;

        public RollItem(ShopRoom s) {
            super(50);
            shop = s;
            img = FileHandler.ui.get("REWARD_CARD");
        }

        @Override
        protected void take() {
            shop.generateSkills();
            shop.generateItems();
            for(int i = 0; i < 6; i++) {
                shopScreen.skills[i].setItem(shop.skills[i]);
                shopScreen.icons[i].item = shop.skills[i];
            }
            for(int i = 0; i < 5; i++) {
                shopScreen.items[i].setItem(shop.items[i]);
            }
            shopScreen.roll.setItem(shop.roll);
        }

        @Override
        public void setPanel() {
            cPanel.infoPanel.setInfo("새로고침", "제단을 초기화합니다. ");
        }
    }

    public static class SkillItem extends ShopItem{
        public AbstractSkill skill;

        public SkillItem(AbstractSkill skill) {
            super(generateSkillPrice());
            this.skill = skill;
            img = this.skill.img;
        }

        private static int generateSkillPrice() {
            return MathUtils.floor((80 + (10 - shopRandom.random(20))) * (advisor.cls == AbstractAdvisor.AdvisorClass.RUSEOK ? 0.8f : 1));
        }

        @Override
        protected void take() {
            Labyrintale.addTempScreen(new ShopTakeScreen(skill));
        }

        @Override
        public void setPanel() {
            cPanel.infoPanel.setInfo(skill);
        }
    }

    private static class ItemItem extends ShopItem{
        public AbstractItem item;

        public ItemItem(ItemRarity r) {
            this(ItemGroup.getRandomItemByRarity(r));
        }

        public ItemItem(AbstractItem item) {
            super(MathUtils.floor(generateItemPrice(item) * (advisor.cls == AbstractAdvisor.AdvisorClass.RUSEOK ? 0.8f : 1)));
            this.item = item;
            img = this.item.img;
        }

        private static int generateItemPrice(AbstractItem i) {
            switch(i.rarity) {
                case GOLD:
                    return 180 + shopRandom.random(20) - 10;
                case BRONZE:
                    return 60 + shopRandom.random(20) - 10;
                default:
                    return 120 + shopRandom.random(20) - 10;
            }
        }

        @Override
        protected void take() {
            Labyrintale.addTempScreen(new ShopTakeScreen(item, null));
        }

        @Override
        public void setPanel() {
            cPanel.infoPanel.setInfo(item);
        }
    }

    public void generateSkills() {
        //스킬 생성
        int p1 = 1, p2 = 1, p3 = 1, p4 = 1;

        for(int i = 0; i < 2; i++) {
            int t = shopRandom.random(3);
            if (t == 0) p1++;
            else if (t == 1) p2++;
            else if (t == 2) p3++;
            else p4++;
        }

        Array<AbstractSkill> bs = new Array<>();
        bs.addAll(GroupHandler.SkillGroup.getRandomSkill(players[0], p1));
        bs.addAll(GroupHandler.SkillGroup.getRandomSkill(players[1], p2));
        bs.addAll(GroupHandler.SkillGroup.getRandomSkill(players[2], p3));
        bs.addAll(GroupHandler.SkillGroup.getRandomSkill(players[3], p4));
        GroupHandler.SkillGroup.staticShuffle(bs);

        for(int i = 0; i < 6; i++) {
            skills[i] = new SkillItem(bs.get(i));
        }
    }

    public void generateItems() {
        //스킬 생성
        int t = shopRandom.random(100);
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
        Array<AbstractItem> ss = ItemGroup.getRandomItemByRarity(ItemRarity.SILVER, s);
        for(int i = 0; i < b; i++) {
            items[i] = new ItemItem(bs.get(i));
        }
        for(int i = 0; i < s; i++) {
            items[b + i] = new ItemItem(ss.get(i));
        }
        if(g > 0) {
            items[3] = new ItemItem(ItemRarity.GOLD);
        }
        items[4] = new ItemItem(ItemRarity.SHOP);
    }
}
