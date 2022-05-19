package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.RandomXC;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.events.TestEvent;
import com.fastcat.labyrintale.items.boss.*;
import com.fastcat.labyrintale.items.bronze.*;
import com.fastcat.labyrintale.items.silver.*;
import com.fastcat.labyrintale.items.starter.*;
import com.fastcat.labyrintale.rooms.enemy.boss.TestBoss;
import com.fastcat.labyrintale.rooms.enemy.elite.TestElite;
import com.fastcat.labyrintale.rooms.enemy.normal.Test;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak1;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak2;
import com.fastcat.labyrintale.rooms.other.EntryRoom;
import com.fastcat.labyrintale.rooms.other.RestRoom;
import com.fastcat.labyrintale.rooms.other.ShopRoom;
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
    }

    public static class RoomGroup {

        public static HashMap<String, AbstractRoom> idSort = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> weakGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> normalGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> eliteGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> bossGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> eventGroup = new HashMap<>();

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

        public static void roll() {
            for(Array<AbstractRoom> a : weakGroup.values()) {
                staticShuffle(a);
            }
            for(Array<AbstractRoom> a : normalGroup.values()) {
                staticShuffle(a);
            }
            for(Array<AbstractRoom> a : eliteGroup.values()) {
                staticShuffle(a);
            }
            for(Array<AbstractRoom> a : bossGroup.values()) {
                staticShuffle(a);
            }
        }

        public static Array<AbstractRoom> staticShuffle(Array<AbstractRoom> array) {
            return staticShuffle(array, monsterRandom);
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
        public static final Array<AbstractItem> bronzeItem = new Array<>();
        public static final Array<AbstractItem> silverItem = new Array<>();
        public static final Array<AbstractItem> goldItem = new Array<>();
        public static final Array<AbstractItem> shopItem = new Array<>();
        public static final Array<AbstractItem> eventItem = new Array<>();

        public static void generateItem() {
            idSort.clear();
            raritySort.clear();
            allItem.clear();
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
            t.add(new Sedative(null));
            t.add(new Bible(null));
            t.add(new CottonNecklace(null));

            //브론즈
            t.add(new PlatedArmour(null));
            t.add(new NaviNecklace(null));
            t.add(new GlowingStick(null));
            t.add(new ElasticRing(null));
            t.add(new Coffin(null));

            //실버
            t.add(new SafetyHat(null));
            t.add(new AutoFire(null));
            t.add(new BunnyHat(null));
            t.add(new CheeseBall(null));
            t.add(new Purplight(null));

            //골드

            //보스
            t.add(new BossItem(null));
            t.add(new BossItem3(null));
            t.add(new BossItem4(null));
            t.add(new BossItem5(null));

            //싱점

            //이벤트

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
                } else {
                    goldItem.add(item);
                }
            }
            raritySort.put(AbstractItem.ItemRarity.BRONZE, bronzeItem);
            raritySort.put(AbstractItem.ItemRarity.SILVER, silverItem);
            raritySort.put(AbstractItem.ItemRarity.GOLD, goldItem);
            raritySort.put(AbstractItem.ItemRarity.SHOP, shopItem);
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
        public static final HashMap<AbstractSkill.SkillRarity, HashMap<PlayerClass, Array<AbstractSkill>>> raritySort = new HashMap<>();
        public static final HashMap<String, AbstractSkill> idSort = new HashMap<>();
        public static final HashMap<PlayerClass, Array<AbstractSkill>> allSkill = new HashMap<>();
        public static final HashMap<PlayerClass, Array<AbstractSkill>> bronzeSkill = new HashMap<>();
        public static final HashMap<PlayerClass, Array<AbstractSkill>> silverSkill = new HashMap<>();
        public static final HashMap<PlayerClass, Array<AbstractSkill>> goldSkill = new HashMap<>();
        public static final HashMap<String, Integer> discardedCount = new HashMap<>();

        public static void generateSkill() {
            raritySort.clear();
            idSort.clear();
            allSkill.clear();
            bronzeSkill.clear();
            silverSkill.clear();
            goldSkill.clear();
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
            for(PlayerClass cls : allSkill.keySet()) {
                Array<AbstractSkill> s = allSkill.get(cls);
                Array<AbstractSkill> bs = new Array<>();
                Array<AbstractSkill> ss = new Array<>();
                Array<AbstractSkill> gs = new Array<>();
                for (AbstractSkill item : s) {
                    idSort.put(item.id, item);
                    if (item.rarity == AbstractSkill.SkillRarity.BRONZE) {
                        bs.add(item);
                    } else if (item.rarity == AbstractSkill.SkillRarity.SILVER) {
                        ss.add(item);
                    } else {
                        gs.add(item);
                    }
                }
                bronzeSkill.put(cls, bs);
                silverSkill.put(cls, ss);
                goldSkill.put(cls, gs);
            }
            raritySort.put(AbstractSkill.SkillRarity.BRONZE, bronzeSkill);
            raritySort.put(AbstractSkill.SkillRarity.SILVER, silverSkill);
            raritySort.put(AbstractSkill.SkillRarity.GOLD, goldSkill);
        }

        private static void generateBurger() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new Kick(null));
            t.add(new Strong(null));
            t.add(new Purify(null));
            //Silver
            t.add(new Protect(null));
            t.add(new HolyLight(null));
            t.add(new HolySmite(null));
            //Gold
            t.add(new Patience(null));
            t.add(new Test8(null));
            allSkill.put(PlayerClass.BURGER, t);
        }

        private static void generateGosegu() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new BioCloud(null));
            t.add(new Provoke(null));
            t.add(new RustyShard(null));
            //Silver
            t.add(new FlameFlask(null));
            t.add(new Test15(null));
            t.add(new Test16(null));
            //Gold
            t.add(new Test17(null));
            t.add(new Test18(null));
            allSkill.put(PlayerClass.GOSEGU, t);
        }

        private static void generateIne() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new EyeSting(null));
            t.add(new Pruning(null));
            t.add(new Test23(null));
            //Silver
            t.add(new Test24(null));
            t.add(new Test25(null));
            t.add(new Test26(null));
            //Gold
            t.add(new Test27(null));
            t.add(new Test28(null));
            allSkill.put(PlayerClass.INE, t);
        }

        private static void generateJururu() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new Scorn(null));
            t.add(new Pray(null));
            t.add(new Test33(null));
            //Silver
            t.add(new Test34(null));
            t.add(new Test35(null));
            t.add(new Test36(null));
            //Gold
            t.add(new Test37(null));
            t.add(new Test38(null));
            allSkill.put(PlayerClass.JURURU, t);
        }

        private static void generateLilpa() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new Lilpaa(null));
            t.add(new FireBall(null));
            t.add(new Test43(null));
            //Silver
            t.add(new Test44(null));
            t.add(new Test45(null));
            t.add(new Test46(null));
            //Gold
            t.add(new Test47(null));
            t.add(new Test48(null));
            allSkill.put(PlayerClass.LILPA, t);
        }

        private static void generateManager() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new Test51(null));
            t.add(new Test52(null));
            t.add(new Test53(null));
            //Silver
            t.add(new Test54(null));
            t.add(new Test55(null));
            t.add(new Test56(null));
            //Gold
            t.add(new Test57(null));
            t.add(new Test58(null));
            allSkill.put(PlayerClass.MANAGER, t);
        }

        private static void generateViichan() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new DiaSword(null));
            t.add(new ChainMail(null));
            t.add(new Overpower(null));
            //Silver
            t.add(new Test64(null));
            t.add(new Test65(null));
            t.add(new Test66(null));
            //Gold
            t.add(new Test67(null));
            t.add(new Test68(null));
            allSkill.put(PlayerClass.VIICHAN, t);
        }

        private static void generateWak() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new Boost(null));
            t.add(new Encourage(null));
            t.add(new Test73(null));
            //Silver
            t.add(new Test74(null));
            t.add(new Test75(null));
            t.add(new Test76(null));
            //Gold
            t.add(new Test77(null));
            t.add(new Test78(null));
            allSkill.put(PlayerClass.WAK, t);
        }

        private static void resetDiscCount() {
            discardedCount.clear();
            for(Array<AbstractSkill> a : allSkill.values()) {
                for(AbstractSkill s : a) {
                    discardedCount.put(s.id, 0);
                }
            }
        }

        public static AbstractSkill getRandomSkillByRarity(AbstractSkill.SkillRarity rarity) {
            return getRandomSkillByRarity(rarity, 1).get(1);
        }

        public static Array<AbstractSkill> getRandomSkillByRarity(AbstractSkill.SkillRarity rarity, int amount) {
            Array<AbstractSkill> a = new Array<>();
            Array<AbstractSkill> b = new Array<>();
            boolean t;
            for(AbstractPlayer p : players) {
                if(p.isAlive()) {
                    for (AbstractSkill s : raritySort.get(rarity).get(p.playerClass)) {
                        t = true;
                        for (AbstractSkill ss : p.deck) {
                            if (!s.id.equals(ss.id)) {
                                t = false;
                                break;
                            }
                        }
                        if (t) b.add(s);
                    }
                }
            }
            staticShuffle(b);
            for (int i = 0; i < amount; i++) {
                AbstractSkill tt;
                tt = Objects.requireNonNull(b.get(i).clone());
                a.add(tt);
            }
            return a;
        }

        public static Array<AbstractSkill> getRandomSkill(AbstractPlayer p, int amount) {
            Array<AbstractSkill> a = new Array<>();
            Array<AbstractSkill> b = new Array<>();
            boolean t;
            for(AbstractSkill s : allSkill.get(p.playerClass)) {
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
