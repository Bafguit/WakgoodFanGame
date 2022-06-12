package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.RandomXC;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.advisors.TestAdvisor;
import com.fastcat.labyrintale.events.TestEvent;
import com.fastcat.labyrintale.items.boss.*;
import com.fastcat.labyrintale.items.bronze.*;
import com.fastcat.labyrintale.items.gold.*;
import com.fastcat.labyrintale.items.shop.*;
import com.fastcat.labyrintale.items.silver.*;
import com.fastcat.labyrintale.items.starter.*;
import com.fastcat.labyrintale.rooms.enemy.boss.TestBoss;
import com.fastcat.labyrintale.rooms.enemy.elite.TestElite;
import com.fastcat.labyrintale.rooms.enemy.normal.Test;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak1;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak2;
import com.fastcat.labyrintale.rooms.other.*;
import com.fastcat.labyrintale.skills.player.basic.Barrier;
import com.fastcat.labyrintale.skills.player.basic.Strike;
import com.fastcat.labyrintale.skills.player.burger.*;
import com.fastcat.labyrintale.skills.player.gosegu.*;
import com.fastcat.labyrintale.skills.player.ine.*;
import com.fastcat.labyrintale.skills.player.jururu.*;
import com.fastcat.labyrintale.skills.player.lilpa.*;
import com.fastcat.labyrintale.skills.player.manager.*;
import com.fastcat.labyrintale.skills.player.viichan.*;
import com.fastcat.labyrintale.skills.player.wak.*;

import java.util.HashMap;
import java.util.Objects;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;
import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;

public class GroupHandler {

    public GroupHandler() {
        SkillGroup.generateSkill();
        ItemGroup.generateItem();
        RoomGroup.generateRoom();
        AdvisorGroup.generateAdvisor();
    }

    public static class AdvisorGroup {

        public static Array<AbstractAdvisor> sort = new Array<>();
        public static HashMap<AbstractAdvisor.AdvisorClass, AbstractAdvisor> advisors = new HashMap<>();

        public static void generateAdvisor() {
            for(AbstractAdvisor.AdvisorClass c : AbstractAdvisor.AdvisorClass.values()) {
                AbstractAdvisor a = getAdvisorInstance(c);
                sort.add(a);
                advisors.put(c, a);
            }
        }

        public static AbstractAdvisor getAdvisorInstance(AbstractAdvisor.AdvisorClass cls) {
            switch (cls) {
                default:
                    return new TestAdvisor();
            }
        }

        public static Array<AbstractAdvisor> staticShuffle(Array<AbstractAdvisor> array) {
            AbstractAdvisor[] items = array.toArray(AbstractAdvisor.class);
            for(int i = array.size - 1; i >= 0; --i) {
                int ii = publicRandom.random(i);
                AbstractAdvisor temp = items[i];
                items[i] = items[ii];
                items[ii] = temp;
            }
            array.clear();
            array.addAll(items);
            return array;
        }
    }

    public static class RoomGroup {

        public static HashMap<String, AbstractRoom> idSort = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> weakGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> normalGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> eliteGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> bossGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> eventGroup = new HashMap<>();
        public static int eventCount;

        public static void generateRoom() {
            generateWeak();
            generateNormal();
            generateElite();
            generateBoss();
            generateEvent();
            sort();
        }

        private static void generateWeak() {
            weakGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new Weak1());
            t.add(new Weak2());
            weakGroup.put(1, t);
        }

        private static void generateNormal() {
            normalGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new Test());
            normalGroup.put(1, t);
        }

        private static void generateElite() {
            eliteGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new TestElite());
            eliteGroup.put(1, t);
        }

        private static void generateBoss() {
            bossGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new TestBoss());
            bossGroup.put(1, t);
        }

        private static void generateEvent() {
            eventGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new AbstractRoom(new TestEvent()));
            eventGroup.put(1, t);
        }

        private static void sort() {
            idSort.clear();

            idSort.put("Entry", new EntryRoom());
            idSort.put("Shop", new ShopRoom());
            idSort.put("Rest", new RestRoom());
            idSort.put("Mystery", new MysteryRoom());
            idSort.put("Placeholder", new PlaceholderRoom());

            for(Array<AbstractRoom> ar : weakGroup.values()) {
                for(AbstractRoom r : ar) {
                    idSort.put(r.id, r);
                }
            }
            for(Array<AbstractRoom> ar : normalGroup.values()) {
                for(AbstractRoom r : ar) {
                    idSort.put(r.id, r);
                }
            }
            for(Array<AbstractRoom> ar : eliteGroup.values()) {
                for(AbstractRoom r : ar) {
                    idSort.put(r.id, r);
                }
            }
            for(Array<AbstractRoom> ar : bossGroup.values()) {
                for(AbstractRoom r : ar) {
                    idSort.put(r.id, r);
                }
            }
            for(Array<AbstractRoom> ar : eventGroup.values()) {
                for(AbstractRoom r : ar) {
                    idSort.put(r.id, r);
                }
            }
        }

        public static AbstractRoom getWeak() {
            return new Weak1();
        }

        public static AbstractRoom getNextEvent() {
            return Objects.requireNonNull(eventGroup.get(currentFloor.floorNum).get(eventCount++).clone());
        }

        public static void roll() {
            for(Array<AbstractRoom> a : weakGroup.values()) {
                staticShuffle(a, monsterRandom);
            }
            for(Array<AbstractRoom> a : normalGroup.values()) {
                staticShuffle(a, monsterRandom);
            }
            for(Array<AbstractRoom> a : eliteGroup.values()) {
                staticShuffle(a, monsterRandom);
            }
            for(Array<AbstractRoom> a : bossGroup.values()) {
                staticShuffle(a, monsterRandom);
            }
        }

        public static Array<AbstractRoom> staticShuffle(Array<AbstractRoom> array, RandomXC r) {
            AbstractRoom[] items = array.toArray(AbstractRoom.class);
            for(int i = array.size - 1; i >= 0; --i) {
                int ii = r.random(i);
                AbstractRoom temp = items[i];
                items[i] = items[ii];
                items[ii] = temp;
            }
            array.clear();
            array.addAll(items);
            return array;
        }
    }

    public static class ItemGroup {
        public static final HashMap<AbstractItem.ItemRarity, Array<AbstractItem>> raritySort = new HashMap<>();
        public static final HashMap<String, AbstractItem> idSort = new HashMap<>();
        public static final Array<AbstractItem> allItem = new Array<>();
        public static final Array<AbstractItem> starterItem = new Array<>();
        public static final Array<AbstractItem> bronzeItem = new Array<>();
        public static final Array<AbstractItem> silverItem = new Array<>();
        public static final Array<AbstractItem> goldItem = new Array<>();
        public static final Array<AbstractItem> shopItem = new Array<>();

        public static void generateItem() {
            idSort.clear();
            raritySort.clear();
            allItem.clear();
            starterItem.clear();
            bronzeItem.clear();
            silverItem.clear();
            goldItem.clear();
            shopItem.clear();
            generateAll();
            sort();
        }

        private static void generateAll() {
            Array<AbstractItem> t = allItem;
            //시작 아이템
            t.add(new Item1(null)); //삭제 예정
            t.add(new OldShield(null));
            t.add(new Pendant(null));
            t.add(new OldArmour(null));
            t.add(new BurgerHat(null));
            t.add(new OldSword(null));
            t.add(new FabricMail(null));
            t.add(new ToxicFlask(null));
            t.add(new CrossPin(null));
            t.add(new Bible(null));
            t.add(new CottonNecklace(null));

            //브론즈
            t.add(new PlatedArmour(null));
            t.add(new NaviNecklace(null));
            t.add(new GlowingStick(null));
            t.add(new ElasticRing(null));
            t.add(new Coffin(null));
            t.add(new BronzeItem3(null));
            t.add(new BronzeItem4(null));
            t.add(new BronzeItem5(null));
            t.add(new BronzeItem7(null));
            t.add(new BronzeItem10(null));
            t.add(new BronzeItem11(null));
            t.add(new BronzeItem12(null));

            //실버
            t.add(new SafetyHat(null));
            t.add(new AutoFire(null));
            t.add(new BunnyHat(null));
            t.add(new CheeseBall(null));
            t.add(new Purplight(null));
            t.add(new SilverItem1(null));
            t.add(new SilverItem2(null));
            t.add(new SilverItem3(null));
            t.add(new SilverItem4(null));
            t.add(new SilverItem5(null));
            t.add(new SilverItem6(null));
            t.add(new SilverItem7(null));

            //골드
            t.add(new GoldItem(null));
            t.add(new GoldItem2(null));
            t.add(new GoldItem3(null));
            t.add(new GoldItem4(null));
            t.add(new GoldItem5(null));
            t.add(new GoldItem7(null));
            t.add(new GoldItem8(null));
            t.add(new GoldItem9(null));
            t.add(new GoldItem10(null));
            t.add(new GoldItem11(null));
            t.add(new GoldItem12(null));

            //보스
            t.add(new BossItem(null));
            t.add(new BossItem2(null));
            t.add(new BossItem4(null));
            t.add(new BossItem5(null));
            t.add(new BossItem6(null));
            t.add(new BossItem7(null));
            t.add(new BossItem8(null));
            t.add(new BossItem9(null));
            t.add(new BossItem10(null));
            t.add(new BossItem11(null));
            t.add(new BossItem12(null));
            t.add(new BrokenTicker(null));

            //싱점
            t.add(new ShopItem1(null));
            t.add(new ShopItem2(null));
            t.add(new ShopItem3(null));
            t.add(new ShopItem4(null));
            t.add(new ShopItem5(null));
            t.add(new ShopItem6(null));
            t.add(new ShopItem7(null));
            t.add(new ShopItem8(null));
            t.add(new ShopItem9(null));
        }

        public static AbstractItem getRandomItemByRarity(AbstractItem.ItemRarity rarity) {
            return getRandomItemByRarity(rarity, 1).get(0);
        }

        public static Array<AbstractItem> getRandomItemByRarity(AbstractItem.ItemRarity rarity, int amount) {
            Array<AbstractItem> a = new Array<>();
            Array<AbstractItem> b = new Array<>();
            for(AbstractItem s : raritySort.get(rarity)) {
                boolean can = false;
                for(AbstractPlayer p : players) {
                    can = p.isAlive() && !p.item[0].id.equals(s.id) && !p.item[1].id.equals(s.id);
                }
                if(can) b.add(s);
            }
            staticShuffle(b);
            for(int i = 0; i < amount; i++) {
                AbstractItem tt;
                tt = Objects.requireNonNull(b.get(i).clone());
                a.add(tt);
            }
            return a;
        }

        public static Array<AbstractItem> getRandomItem(int amount) {
            Array<AbstractItem> a = new Array<>();
            Array<AbstractItem> b = new Array<>();
            for(AbstractItem s : allItem) {
                boolean can = false;
                for(AbstractPlayer p : players) {
                    can = p.isAlive() && !p.item[0].id.equals(s.id) && !p.item[1].id.equals(s.id);
                }
                if(can) b.add(s);
            }
            staticShuffle(b);
            for(int i = 0; i < amount; i++) {
                AbstractItem tt;
                tt = Objects.requireNonNull(b.get(i).clone());
                a.add(tt);
            }
            return a;
        }

        public static AbstractItem getRandomItem() {
            return getRandomItem(1).get(0);
        }

        private static void sort() {
            for(AbstractItem item : allItem) {
                idSort.put(item.id, item);
                if(item.rarity == AbstractItem.ItemRarity.BRONZE) {
                    bronzeItem.add(item);
                } else if (item.rarity == AbstractItem.ItemRarity.SILVER) {
                    silverItem.add(item);
                } else if (item.rarity == AbstractItem.ItemRarity.GOLD) {
                    goldItem.add(item);
                } else if(item.rarity == AbstractItem.ItemRarity.SHOP) {
                    shopItem.add(item);
                } else if(item.rarity == AbstractItem.ItemRarity.STARTER) {
                    starterItem.add(item);
                }
            }
            raritySort.put(AbstractItem.ItemRarity.BRONZE, bronzeItem);
            raritySort.put(AbstractItem.ItemRarity.SILVER, silverItem);
            raritySort.put(AbstractItem.ItemRarity.GOLD, goldItem);
            raritySort.put(AbstractItem.ItemRarity.SHOP, shopItem);
            raritySort.put(AbstractItem.ItemRarity.STARTER, starterItem);
        }

        public static Array<AbstractItem> staticShuffle(Array<AbstractItem> array) {
            return staticShuffle(array, itemRandom);
        }

        public static Array<AbstractItem> staticShuffle(Array<AbstractItem> array, RandomXC r) {
            AbstractItem[] items = array.toArray(AbstractItem.class);
            for(int i = array.size - 1; i >= 0; --i) {
                int ii = r.random(i);
                AbstractItem temp = items[i];
                items[i] = items[ii];
                items[ii] = temp;
            }
            array.clear();
            array.addAll(items);
            return array;
        }
    }

    public static class SkillGroup {
        public static final HashMap<String, AbstractSkill> idSort = new HashMap<>();
        public static final HashMap<PlayerClass, Array<AbstractSkill>> playerSort = new HashMap<>();
        public static HashMap<String, Integer> discardedCount = new HashMap<>();

        public static void generateSkill() {
            idSort.clear();
            playerSort.clear();
            generateBurger();
            generateGosegu();
            generateIne();
            generateJururu();
            generateLilpa();
            generateManager();
            generateViichan();
            generateWak();
            resetDiscCount();
            idSort.put("Strike", new Strike(null));
            idSort.put("Barrier", new Barrier(null));
            sort();
        }

        private static void sort() {
            for(PlayerClass cls : playerSort.keySet()) {
                Array<AbstractSkill> s = playerSort.get(cls);
                for (AbstractSkill item : s) {
                    idSort.put(item.id, item);
                }
            }
        }

        private static void generateBurger() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new Kick(null));
            t.add(new Strong(null));
            t.add(new Purify(null));
            t.add(new Protect(null));
            t.add(new HolyLight(null));
            t.add(new HolySmite(null));
            t.add(new Patience(null));
            t.add(new Test8(null));
            playerSort.put(PlayerClass.BURGER, t);
        }

        private static void generateGosegu() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new BioCloud(null));
            t.add(new Provoke(null));
            t.add(new RustyShard(null));
            t.add(new FlameFlask(null));
            t.add(new Test15(null));
            t.add(new Test16(null));
            t.add(new Test17(null));
            t.add(new Test18(null));
            playerSort.put(PlayerClass.GOSEGU, t);
        }

        private static void generateIne() {
            Array<AbstractSkill> t = new Array<>();
            t.add(new EyeSting(null));
            t.add(new Pruning(null));
            t.add(new Test23(null));
            t.add(new Test24(null));
            t.add(new Charge(null));
            t.add(new Test26(null));
            t.add(new Test27(null));
            t.add(new Test28(null));
            playerSort.put(PlayerClass.INE, t);
        }

        private static void generateJururu() {
            Array<AbstractSkill> t = new Array<>();
            t.add(new Scorn(null));
            t.add(new Pray(null));
            t.add(new Test33(null));
            t.add(new Test34(null));
            t.add(new Test35(null));
            t.add(new Test36(null));
            t.add(new Test37(null));
            t.add(new Test38(null));
            playerSort.put(PlayerClass.JURURU, t);
        }

        private static void generateLilpa() {
            Array<AbstractSkill> t = new Array<>();
            t.add(new Lilpaa(null));
            t.add(new FireBall(null));
            t.add(new Test43(null));
            t.add(new Test44(null));
            t.add(new Test45(null));
            t.add(new Test46(null));
            t.add(new Test47(null));
            t.add(new Test48(null));
            playerSort.put(PlayerClass.LILPA, t);
        }

        private static void generateManager() {
            Array<AbstractSkill> t = new Array<>();
            t.add(new Test51(null));
            t.add(new Test52(null));
            t.add(new Test53(null));
            t.add(new Test54(null));
            t.add(new Test55(null));
            t.add(new Test56(null));
            t.add(new Test57(null));
            t.add(new Test58(null));
            playerSort.put(PlayerClass.MANAGER, t);
        }

        private static void generateViichan() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new DiaSword(null));
            t.add(new ChainMail(null));
            t.add(new Overpower(null));
            t.add(new Test64(null));
            t.add(new Test65(null));
            t.add(new Test66(null));
            t.add(new Test67(null));
            t.add(new Test68(null));
            playerSort.put(PlayerClass.VIICHAN, t);
        }

        private static void generateWak() {
            Array<AbstractSkill> t = new Array<>();
            t.add(new Boost(null));
            t.add(new Encourage(null));
            t.add(new Test73(null));
            t.add(new Test74(null));
            t.add(new Test75(null));
            t.add(new Test76(null));
            t.add(new Test77(null));
            t.add(new Test78(null));
            playerSort.put(PlayerClass.WAK, t);
        }

        private static void resetDiscCount() {
            discardedCount.clear();
            for(Array<AbstractSkill> a : playerSort.values()) {
                for(AbstractSkill s : a) {
                    discardedCount.put(s.id, 0);
                }
            }
        }

        public static Array<AbstractSkill> getRandomSkill(AbstractPlayer p, int amount) {
            Array<AbstractSkill> a = new Array<>();
            Array<AbstractSkill> b = new Array<>();
            boolean t;
            for(AbstractSkill s : playerSort.get(p.playerClass)) {
                t = true;
                for(AbstractSkill ss : p.deck) {
                    if (s.id.equals(ss.id)) {
                        t = false;
                        break;
                    }
                }
                if(t) b.add(s);
            }
            staticShuffle(b);
            for(int i = 0; i < amount; i++) {
                AbstractSkill tt;
                tt = Objects.requireNonNull(b.get(i).clone());
                tt.owner = p;
                a.add(tt);
            }
            return a;
        }

        public static AbstractSkill getRandomSkill(AbstractPlayer p) {
            return getRandomSkill(p, 1).get(0);
        }

        public static AbstractSkill getRandomUpgradedSkillFromDeck(AbstractPlayer p, boolean isNone) {
            Array<AbstractSkill> a = new Array<>();
            Array<AbstractSkill> b = p.deck;
            for (int i = 0; i < b.size; i++) {
                AbstractSkill s = b.get(i);
                if(s.upgraded != isNone) a.add(s.clone());
            }
            if(a.size > 0) {
                return staticShuffle(a).get(0);
            } else return null;
        }

        public static Array<AbstractSkill> staticShuffle(Array<AbstractSkill> array) {
            return staticShuffle(array, skillRandom);
        }

        public static Array<AbstractSkill> staticShuffle(Array<AbstractSkill> array, RandomXC r) {
            AbstractSkill[] items = array.toArray(AbstractSkill.class);
            for(int i = array.size - 1; i >= 0; --i) {
                int ii = r.random(i);
                AbstractSkill temp = items[i];
                items[i] = items[ii];
                items[ii] = temp;
            }
            array.clear();
            array.addAll(items);
            return array;
        }
    }
}
