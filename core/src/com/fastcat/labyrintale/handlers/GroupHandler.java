package com.fastcat.labyrintale.handlers;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;
import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass;
import com.fastcat.labyrintale.advisors.*;
import com.fastcat.labyrintale.events.first.*;
import com.fastcat.labyrintale.events.fourth.*;
import com.fastcat.labyrintale.events.neut.*;
import com.fastcat.labyrintale.events.second.*;
import com.fastcat.labyrintale.events.third.*;
import com.fastcat.labyrintale.items.boss.*;
import com.fastcat.labyrintale.items.bronze.*;
import com.fastcat.labyrintale.items.gold.*;
import com.fastcat.labyrintale.items.shop.*;
import com.fastcat.labyrintale.items.silver.*;
import com.fastcat.labyrintale.items.special.*;
import com.fastcat.labyrintale.items.starter.*;
import com.fastcat.labyrintale.rooms.enemy.boss.act1.Boss1;
import com.fastcat.labyrintale.rooms.enemy.boss.act2.Boss2;
import com.fastcat.labyrintale.rooms.enemy.boss.act3.Boss3;
import com.fastcat.labyrintale.rooms.enemy.boss.act4.Boss4;
import com.fastcat.labyrintale.rooms.enemy.elite.act1.Elite1;
import com.fastcat.labyrintale.rooms.enemy.elite.act1.Elite2;
import com.fastcat.labyrintale.rooms.enemy.elite.act2.Act2Elite1;
import com.fastcat.labyrintale.rooms.enemy.elite.act2.Act2Elite2;
import com.fastcat.labyrintale.rooms.enemy.elite.act3.Act3Elite1;
import com.fastcat.labyrintale.rooms.enemy.elite.act3.Act3Elite2;
import com.fastcat.labyrintale.rooms.enemy.elite.act4.Act4Elite1;
import com.fastcat.labyrintale.rooms.enemy.elite.act4.Act4Elite2;
import com.fastcat.labyrintale.rooms.enemy.normal.act1.*;
import com.fastcat.labyrintale.rooms.enemy.normal.act2.*;
import com.fastcat.labyrintale.rooms.enemy.normal.act3.*;
import com.fastcat.labyrintale.rooms.enemy.normal.act4.*;
import com.fastcat.labyrintale.rooms.enemy.weak.act1.*;
import com.fastcat.labyrintale.rooms.enemy.weak.act2.*;
import com.fastcat.labyrintale.rooms.enemy.weak.act3.*;
import com.fastcat.labyrintale.rooms.enemy.weak.act4.*;
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
import com.fastcat.labyrintale.utils.RandomXC;
import java.util.HashMap;
import java.util.Objects;

public final class GroupHandler {
    /***
     * Instance of handler.
     * Initialized on getInstance()
     */
    private static GroupHandler instance;

    private GroupHandler() {
        SkillGroup.generateSkill();
        ItemGroup.generateItem();
        RoomGroup.generateRoom();
        AdvisorGroup.generateAdvisor();
    }

    /***
     * Returns instance of handler, if not exist, create new.
     * @return instance of handler
     */
    public static GroupHandler getInstance() {
        if (instance == null) return (instance = new GroupHandler());
        return instance;
    }

    public static class AdvisorGroup {

        public static Array<AdvisorClass> sort = new Array<>();

        public static void generateAdvisor() {
            for (AdvisorClass c : AdvisorClass.values()) {
                if (c != AdvisorClass.BUSINESS && c != AdvisorClass.FREETER && c != AdvisorClass.CARNAR) {
                    sort.add(c);
                }
            }
        }

        public static AbstractItem getAdvisorInstance(AdvisorClass cls) {
            switch (cls) {
                case PUNG:
                    return new Pungsin();
                case JK:
                    return new Hyeji();
                case FREETER:
                    return new Freeter();
                case DUKSU:
                    return new Duksu();
                case DOPA:
                    return new DrDopamine();
                case CARNAR:
                    return new CarnarYungter();
                case BUSINESS:
                    return new BusinessKim();
                case SOPHIA:
                    return new Sophia();
                case SECRET:
                    return new SecretGirl();
                case HIKI:
                    return new HikiKing();
                case RUSUK:
                    return new Haerusuk();
                case CALLYCARLY:
                    return new CallyCarlyDavidson();
                case MANDU:
                    return new KimchiMandu();
                default:
                    return new MitsuneHaku();
            }
        }

        public static Array<AbstractItem> getStartAdvisor() {
            Array<AbstractItem> a = new Array<>();
            a.add(getAdvisorInstance(AdvisorClass.BUSINESS));
            a.add(getAdvisorInstance(AdvisorClass.FREETER));
            a.add(getAdvisorInstance(AdvisorClass.CARNAR));
            return a;
        }

        public static Array<AbstractItem> getAdvisors(int amount) {
            Array<AdvisorClass> t = new Array<>();
            Array<AbstractItem> r = new Array<>();
            for (AdvisorClass advisor : sort) {
                if (!advisor.toString().toLowerCase().equals(AbstractLabyrinth.advisor.id)) t.add(advisor);
            }
            staticShuffle(t);
            for (int i = 0; i < amount; i++) {
                r.add(getAdvisorInstance(t.get(i)));
            }
            return r;
        }

        public static Array<AdvisorClass> staticShuffle(Array<AdvisorClass> array) {
            AdvisorClass[] items = array.toArray(AdvisorClass.class);
            for (int i = array.size - 1; i >= 0; --i) {
                int ii = publicRandom.random(i);
                AdvisorClass temp = items[i];
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
        public static HashMap<String, AbstractEvent> eventSort = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> weakGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> normalGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> eliteGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> bossGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractEvent>> eventGroup = new HashMap<>();
        public static Array<AbstractEvent> eventNeut = new Array<>();
        public static int eventCount;
        public static int neutCount;
        public static int weakCount;
        public static int normalCount;
        public static int eliteCount;
        public static int bossCount;

        public static void generateRoom() {
            generateWeak();
            generateNormal();
            generateElite();
            generateBoss();
            generateEvent();
            generateNeut();
            sort();
        }

        private static void generateWeak() {
            weakGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new Weak1());
            t.add(new Weak2());
            t.add(new Weak3());
            t.add(new Weak4());
            t.add(new Weak5());
            weakGroup.put(1, t);
            Array<AbstractRoom> t2 = new Array<>();
            t2.add(new Act2Weak1());
            t2.add(new Act2Weak2());
            t2.add(new Act2Weak3());
            t2.add(new Act2Weak4());
            t2.add(new Act2Weak5());
            weakGroup.put(2, t2);
            Array<AbstractRoom> t3 = new Array<>();
            t3.add(new Act3Weak1());
            t3.add(new Act3Weak2());
            t3.add(new Act3Weak3());
            t3.add(new Act3Weak4());
            t3.add(new Act3Weak5());
            weakGroup.put(3, t3);
            Array<AbstractRoom> t4 = new Array<>();
            t4.add(new Act4Weak1());
            t4.add(new Act4Weak2());
            t4.add(new Act4Weak3());
            t4.add(new Act4Weak4());
            t4.add(new Act4Weak5());
            weakGroup.put(4, t4);
        }

        private static void generateNormal() {
            normalGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new Normal1());
            t.add(new Normal2());
            t.add(new Normal3());
            t.add(new Normal4());
            t.add(new Normal5());
            normalGroup.put(1, t);
            Array<AbstractRoom> t2 = new Array<>();
            t2.add(new Act2Normal1());
            t2.add(new Act2Normal2());
            t2.add(new Act2Normal3());
            t2.add(new Act2Normal4());
            t2.add(new Act2Normal5());
            normalGroup.put(2, t2);
            Array<AbstractRoom> t3 = new Array<>();
            t3.add(new Act3Normal1());
            t3.add(new Act3Normal2());
            t3.add(new Act3Normal3());
            t3.add(new Act3Normal4());
            t3.add(new Act3Normal5());
            normalGroup.put(3, t3);
            Array<AbstractRoom> t4 = new Array<>();
            t4.add(new Act4Normal1());
            t4.add(new Act4Normal2());
            t4.add(new Act4Normal3());
            t4.add(new Act4Normal4());
            t4.add(new Act4Normal5());
            normalGroup.put(4, t4);
        }

        private static void generateElite() {
            eliteGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new Elite1());
            t.add(new Elite2());
            eliteGroup.put(1, t);
            Array<AbstractRoom> t2 = new Array<>();
            t2.add(new Act2Elite1());
            t2.add(new Act2Elite2());
            eliteGroup.put(2, t2);
            Array<AbstractRoom> t3 = new Array<>();
            t3.add(new Act3Elite1());
            t3.add(new Act3Elite2());
            eliteGroup.put(3, t3);
            Array<AbstractRoom> t4 = new Array<>();
            t4.add(new Act4Elite1());
            t4.add(new Act4Elite2());
            eliteGroup.put(4, t4);
        }

        private static void generateBoss() {
            bossGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new Boss1());
            bossGroup.put(1, t);
            Array<AbstractRoom> t2 = new Array<>();
            t2.add(new Boss2());
            bossGroup.put(2, t2);
            Array<AbstractRoom> t3 = new Array<>();
            t3.add(new Boss3());
            bossGroup.put(3, t3);
            Array<AbstractRoom> t4 = new Array<>();
            t4.add(new Boss4());
            bossGroup.put(4, t4);
        }

        private static void generateEvent() {
            eventGroup.clear();
            Array<AbstractEvent> t = new Array<>();
            t.add(new DoorEvent());
            t.add(new LoyalistEvent());
            t.add(new SurvivorEvent());
            t.add(new CivilizationEvent());
            t.add(new SealedHeartEvent());
            eventGroup.put(1, t);

            Array<AbstractEvent> t2 = new Array<>();
            t2.add(new BettingEvent());
            t2.add(new TrapEvent());
            t2.add(new StrangerEvent());
            t2.add(new UpsetIdolEvent());
            t2.add(new WeaponEvent());
            t2.add(new ConcertEvent());
            eventGroup.put(2, t2);

            Array<AbstractEvent> t3 = new Array<>();
            t3.add(new GeneralStoreEvent());
            t3.add(new FogEvent());
            t3.add(new CorruptedIdolEvent());
            t3.add(new GhostEvent());
            t3.add(new GotchaEvent());
            eventGroup.put(3, t3);

            Array<AbstractEvent> t4 = new Array<>();
            t4.add(new AmnesiaEvent());
            t4.add(new RevelationEvent());
            t4.add(new PunkEvent());
            t4.add(new AmbushEvent());
            t4.add(new BarEvent());
            eventGroup.put(4, t4);
        }

        private static void generateNeut() {
            eventNeut.clear();
            eventNeut.add(new TransformEvent());
            eventNeut.add(new UpgradeEvent());
            eventNeut.add(new ChaosEvent());
            eventNeut.add(new CureEvent());
            eventNeut.add(new PurifyEvent());
            eventNeut.add(new LightEvent());
            eventNeut.add(new MetamorphEvent());
            eventNeut.add(new GoldEvent());
        }

        public static void shuffleAll() {
            for (int i = 0; i < weakGroup.size(); i++) staticShuffle(weakGroup.get(i + 1), groupRandom);
            for (int i = 0; i < normalGroup.size(); i++) staticShuffle(normalGroup.get(i + 1), groupRandom);
            for (int i = 0; i < eliteGroup.size(); i++) staticShuffle(eliteGroup.get(i + 1), groupRandom);
            for (int i = 0; i < bossGroup.size(); i++) staticShuffle(bossGroup.get(i + 1), groupRandom);
            for (int i = 0; i < eventGroup.size(); i++) eventShuffle(eventGroup.get(i + 1), groupRandom);
            eventShuffle(eventNeut, groupRandom);
        }

        private static void sort() {
            idSort.clear();

            idSort.put("StartEvent", new EntryRoom());
            idSort.put("SecondFloor", new SecondFloorRoom());
            idSort.put("ThirdFloor", new ThirdFloorRoom());
            idSort.put("FourthFloor", new FourthFloorRoom());
            idSort.put("Shop", new ShopRoom());
            idSort.put("Rest", new RestRoom());
            idSort.put("Mystery", new MysteryRoom());
            idSort.put("Placeholder", new PlaceholderRoom());

            for (Array<AbstractRoom> ar : weakGroup.values()) {
                for (AbstractRoom r : ar) {
                    idSort.put(r.id, r);
                }
            }
            for (Array<AbstractRoom> ar : normalGroup.values()) {
                for (AbstractRoom r : ar) {
                    idSort.put(r.id, r);
                }
            }
            for (Array<AbstractRoom> ar : eliteGroup.values()) {
                for (AbstractRoom r : ar) {
                    idSort.put(r.id, r);
                }
            }
            for (Array<AbstractRoom> ar : bossGroup.values()) {
                for (AbstractRoom r : ar) {
                    idSort.put(r.id, r);
                }
            }

            eventSort.clear();
            for (Array<AbstractEvent> ar : eventGroup.values()) {
                for (AbstractEvent r : ar) {
                    eventSort.put(r.id, r);
                }
            }
            for (AbstractEvent r : eventNeut) {
                eventSort.put(r.id, r);
            }
        }

        public static AbstractRoom getRoom(String id) {
            return idSort.get(id).clone();
        }

        public static AbstractRoom getNextWeak(int f) {
            return weakGroup.get(f).get(weakCount++).clone();
        }

        public static AbstractRoom getNextNormal(int f) {
            return normalGroup.get(f).get(normalCount++).clone();
        }

        public static AbstractRoom getNextElite(int f) {
            return eliteGroup.get(f).get(eliteCount++).clone();
        }

        public static AbstractRoom getNextBoss(int f) {
            return bossGroup.get(f).get(bossCount++).clone();
        }

        public static AbstractEvent getNextEvent(int floorNum) {
            AbstractEvent r;
            Array<AbstractEvent> t = eventGroup.get(floorNum);
            boolean ef = eventCount < t.size, nf = neutCount < floorNum * 2;
            if (ef) {
                if (nf && eventRandom.random(100) < 25) {
                    r = eventNeut.get(neutCount++).clone();
                } else {
                    r = eventGroup.get(floorNum).get(eventCount++).clone();
                }
            } else {
                r = eventNeut.get(neutCount++).clone();
            }
            return r;
        }

        public static void roll() {
            for (Array<AbstractRoom> a : weakGroup.values()) {
                staticShuffle(a, monsterRandom);
            }
            for (Array<AbstractRoom> a : normalGroup.values()) {
                staticShuffle(a, monsterRandom);
            }
            for (Array<AbstractRoom> a : eliteGroup.values()) {
                staticShuffle(a, monsterRandom);
            }
            for (Array<AbstractRoom> a : bossGroup.values()) {
                staticShuffle(a, monsterRandom);
            }
        }

        public static Array<AbstractRoom> staticShuffle(Array<AbstractRoom> array, RandomXC r) {
            AbstractRoom[] items = array.toArray(AbstractRoom.class);
            for (int i = array.size - 1; i >= 0; --i) {
                int ii = r.random(i);
                AbstractRoom temp = items[i];
                items[i] = items[ii];
                items[ii] = temp;
            }
            array.clear();
            array.addAll(items);
            return array;
        }

        public static Array<AbstractEvent> eventShuffle(Array<AbstractEvent> array, RandomXC r) {
            AbstractEvent[] items = array.toArray(AbstractEvent.class);
            for (int i = array.size - 1; i >= 0; --i) {
                int ii = r.random(i);
                AbstractEvent temp = items[i];
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
        public static final Array<AbstractItem> normalItem = new Array<>();
        public static final Array<AbstractItem> bronzeItem = new Array<>();
        public static final Array<AbstractItem> silverItem = new Array<>();
        public static final Array<AbstractItem> goldItem = new Array<>();
        public static final Array<AbstractItem> bossItem = new Array<>();
        public static final Array<AbstractItem> shopItem = new Array<>();
        public static final Array<AbstractItem> specialItem = new Array<>();

        public static void generateItem() {
            idSort.clear();
            raritySort.clear();
            allItem.clear();
            starterItem.clear();
            bronzeItem.clear();
            silverItem.clear();
            goldItem.clear();
            shopItem.clear();
            specialItem.clear();
            generateAll();
            sort();
        }

        private static void generateAll() {
            Array<AbstractItem> t = allItem;
            t.add(new PlaceHolder(null));
            // 패시브
            t.add(new OldShield(null));
            t.add(new BurgerHat(null));
            t.add(new BattleAxe(null));
            t.add(new OldSword(null));
            t.add(new ToxicFlask(null));
            t.add(new Bible(null));
            t.add(new FlameBook(null));
            t.add(new OldPistol(null));

            // 브론즈
            t.add(new PlatedArmour(null));
            t.add(new NaviNecklace(null));
            t.add(new GlowingStick(null));
            t.add(new ElasticRing(null));
            t.add(new Coffin(null));
            t.add(new RobotHand(null));
            t.add(new Shield(null));
            t.add(new EyePatch(null));
            t.add(new BigRibbon(null));
            t.add(new Bomb(null));
            t.add(new Rocket(null));
            t.add(new Tail(null));
            t.add(new Kettle(null));
            t.add(new Umbrella(null));
            t.add(new Nobles(null));

            // 실버
            t.add(new SafetyHat(null));
            t.add(new AutoFire(null));
            t.add(new BunnyHat(null));
            t.add(new Brainize(null));
            t.add(new Purplight(null));
            t.add(new SuperLadle(null));
            t.add(new LifePotion(null));
            t.add(new OldArmour(null));
            t.add(new Pendant(null));
            t.add(new CottonNecklace(null));
            t.add(new CrossPin(null));
            t.add(new FabricMail(null));
            t.add(new Mosquito(null));
            t.add(new Cop(null));
            t.add(new Kunai(null));

            // 골드
            t.add(new Juggernaut(null));
            t.add(new CheeseBall(null));
            t.add(new Turtle(null));
            t.add(new GoldenRing(null));
            t.add(new Tree(null));
            t.add(new IceFrag(null));
            t.add(new ToyPlane(null));
            t.add(new Rose(null));
            t.add(new SandClock(null));
            t.add(new Flight(null));
            t.add(new FireStaff(null));
            t.add(new ShoulderPlate(null));
            t.add(new Chiwawa(null));
            t.add(new Pirate(null));
            t.add(new Bear(null));

            // 보스
            t.add(new TotoDeck(null));
            t.add(new Snow(null));
            t.add(new Gyeruek(null));
            t.add(new Hwacha(null));
            t.add(new TrafficLight(null));
            t.add(new SportsCar(null));
            t.add(new Butter(null));
            t.add(new OldHood(null));
            t.add(new BrokenTicker(null));
            t.add(new AhpuHat(null));
            t.add(new Megaphone(null));
            t.add(new Spaceship(null));

            // 상점
            t.add(new TrickDeck(null));
            t.add(new LifeContract(null));
            t.add(new Protection(null));
            t.add(new Badge(null));
            t.add(new Potato(null));
            t.add(new BabyDish(null));
            t.add(new Cane(null));
            t.add(new Toadstool(null));
            t.add(new PeridotStone(null));

            // 특별
            t.add(new GreenHeart(null));
            t.add(new CrackedHeart(null));
            t.add(new GolemHead(null));
            t.add(new DevilIdol(null));
            t.add(new LoyalVirus(null));
            t.add(new ShortBase(null));
            t.add(new Sanpellegrino(null));
            t.add(new Amadeus(null));
            t.add(new Water(null));
        }

        @SuppressWarnings("NewApi")
        public static AbstractItem getItem(String id) {
            AbstractItem tt = Objects.requireNonNull(idSort.get(id).clone());
            HashMap<String, Boolean> temp = UnlockHandler.unlocks.get(UnlockHandler.Unlocks.ITEM);
            if(temp.get(tt.id) == null) temp.put(tt.id, true);
            else if (!temp.get(tt.id)) temp.replace(tt.id, true);
            return tt;
        }

        public static AbstractItem getRandomItemByRarity(AbstractItem.ItemRarity rarity) {
            return getRandomItemByRarity(rarity, 1).get(0);
        }

        @SuppressWarnings("NewApi")
        public static Array<AbstractItem> getRandomItemByRarity(AbstractItem.ItemRarity rarity, int amount) {
            Array<AbstractItem> a = new Array<>();
            Array<AbstractItem> b = new Array<>();
            for (AbstractItem s : raritySort.get(rarity)) {
                boolean can = false;
                for (AbstractPlayer p : players) {
                    can = !p.item[0].id.equals(s.id) && !p.item[1].id.equals(s.id);
                    if (!can) break;
                }
                if (can) b.add(s);
            }
            staticShuffle(b);
            for (int i = 0; i < amount; i++) {
                AbstractItem tt;
                tt = Objects.requireNonNull(b.get(i).clone());
                HashMap<String, Boolean> temp = UnlockHandler.unlocks.get(UnlockHandler.Unlocks.ITEM);
                if(temp.get(tt.id) == null) temp.put(tt.id, true);
                else if (!temp.get(tt.id)) temp.replace(tt.id, true);
                a.add(tt);
            }
            UnlockHandler.save();
            return a;
        }

        @SuppressWarnings("NewApi")
        public static Array<AbstractItem> getRandomItem(int amount) {
            Array<AbstractItem> a = new Array<>();
            Array<AbstractItem> b = new Array<>();
            for (AbstractItem s : normalItem) {
                boolean can = true;
                for (AbstractPlayer p : players) {
                    can = !p.item[0].id.equals(s.id) && !p.item[1].id.equals(s.id);
                    if (!can) break;
                }
                if (can) b.add(s);
            }
            staticShuffle(b);
            for (int i = 0; i < amount; i++) {
                AbstractItem tt;
                tt = Objects.requireNonNull(b.get(i).clone());
                HashMap<String, Boolean> temp = UnlockHandler.unlocks.get(UnlockHandler.Unlocks.ITEM);
                if(temp.get(tt.id) == null) temp.put(tt.id, true);
                else if (!temp.get(tt.id)) temp.replace(tt.id, true);
                a.add(tt);
            }
            UnlockHandler.save();
            return a;
        }

        public static AbstractItem getRandomItem() {
            return getRandomItem(1).get(0);
        }

        private static void sort() {
            for (AbstractItem item : allItem) {
                idSort.put(item.id, item);
                if (item.rarity == AbstractItem.ItemRarity.BRONZE) {
                    bronzeItem.add(item);
                    normalItem.add(item);
                } else if (item.rarity == AbstractItem.ItemRarity.SILVER) {
                    silverItem.add(item);
                    normalItem.add(item);
                } else if (item.rarity == AbstractItem.ItemRarity.GOLD) {
                    goldItem.add(item);
                    normalItem.add(item);
                } else if (item.rarity == AbstractItem.ItemRarity.BOSS) {
                    bossItem.add(item);
                } else if (item.rarity == AbstractItem.ItemRarity.SHOP) {
                    shopItem.add(item);
                } else if (item.rarity == AbstractItem.ItemRarity.STARTER) {
                    starterItem.add(item);
                } else if (item.rarity == AbstractItem.ItemRarity.SPECIAL) {
                    specialItem.add(item);
                }
            }
            raritySort.put(AbstractItem.ItemRarity.BRONZE, bronzeItem);
            raritySort.put(AbstractItem.ItemRarity.SILVER, silverItem);
            raritySort.put(AbstractItem.ItemRarity.GOLD, goldItem);
            raritySort.put(AbstractItem.ItemRarity.BOSS, bossItem);
            raritySort.put(AbstractItem.ItemRarity.SHOP, shopItem);
            raritySort.put(AbstractItem.ItemRarity.SPECIAL, specialItem);
            raritySort.put(AbstractItem.ItemRarity.STARTER, starterItem);
        }

        public static Array<AbstractItem> staticShuffle(Array<AbstractItem> array) {
            return staticShuffle(array, itemRandom);
        }

        public static Array<AbstractItem> staticShuffle(Array<AbstractItem> array, RandomXC r) {
            AbstractItem[] items = array.toArray(AbstractItem.class);
            for (int i = array.size - 1; i >= 0; --i) {
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
        public static final HashMap<PlayerClass, Array<AbstractSkill>> normalSkills = new HashMap<>();

        public static void generateSkill() {
            idSort.clear();
            playerSort.clear();
            normalSkills.clear();
            generateBurger();
            generateGosegu();
            generateIne();
            generateJururu();
            generateLilpa();
            generateManager();
            generateViichan();
            generateWak();
            idSort.put("Strike", new Strike(null));
            idSort.put("Barrier", new Barrier(null));
            sort();
        }

        private static void sort() {
            for (PlayerClass cls : playerSort.keySet()) {
                Array<AbstractSkill> s = playerSort.get(cls);
                Array<AbstractSkill> ss = new Array<>();
                for (AbstractSkill item : s) {
                    idSort.put(item.id, item);
                    if (item.rarity == AbstractSkill.SkillRarity.NORMAL) ss.add(item);
                }
                if (ss.size > 0) normalSkills.put(cls, ss);
            }
        }

        private static void generateBurger() {
            Array<AbstractSkill> t = new Array<>();
            // Bronze
            t.add(new KeepOnLine(null));
            t.add(new SuperSave(null));
            t.add(new Purify(null));
            t.add(new Protect(null));
            t.add(new HolyLight(null));
            t.add(new HolySmite(null));
            t.add(new Strong(null));
            t.add(new Bless(null));
            t.add(new Relief(null));
            playerSort.put(PlayerClass.BURGER, t);
        }

        private static void generateGosegu() {
            Array<AbstractSkill> t = new Array<>();
            // Bronze
            t.add(new BioCloud(null));
            t.add(new Provoke(null));
            t.add(new RustyShard(null));
            t.add(new FlameFlask(null));
            t.add(new Poison(null));
            t.add(new HealingPotion(null));
            t.add(new UpgradePotion(null));
            t.add(new ConfidPotion(null));
            t.add(new Catalyst(null));
            playerSort.put(PlayerClass.GOSEGU, t);
        }

        private static void generateIne() {
            Array<AbstractSkill> t = new Array<>();
            t.add(new EyeSting(null));
            t.add(new Pruning(null));
            t.add(new EnduringPotion(null));
            t.add(new ThrowAxe(null));
            t.add(new Charge(null));
            t.add(new Intimidate(null));
            t.add(new Sharp(null));
            t.add(new Channeling(null));
            t.add(new Crush(null));
            playerSort.put(PlayerClass.INE, t);
        }

        private static void generateJururu() {
            Array<AbstractSkill> t = new Array<>();
            t.add(new Scorn(null));
            t.add(new Pray(null));
            t.add(new Support(null));
            t.add(new IronWill(null));
            t.add(new PureWill(null));
            t.add(new Penitence(null));
            t.add(new Penance(null));
            t.add(new BraveWill(null));
            t.add(new Lure(null));
            playerSort.put(PlayerClass.JURURU, t);
        }

        private static void generateLilpa() {
            Array<AbstractSkill> t = new Array<>();
            t.add(new Lilpaa(null));
            t.add(new FireBall(null));
            t.add(new CrystalStrike(null));
            t.add(new Flame(null));
            t.add(new Ignite(null));
            t.add(new Lightning(null));
            t.add(new ShockZone(null));
            t.add(new ChainExp(null));
            t.add(new WarmUp(null));
            playerSort.put(PlayerClass.LILPA, t);
        }

        private static void generateManager() {
            Array<AbstractSkill> t = new Array<>();
            t.add(new Outburst(null));
            t.add(new ShockBomb(null));
            t.add(new Calmness(null));
            t.add(new DramaticExit(null));
            t.add(new PointBlank(null));
            t.add(new Snipe(null));
            t.add(new Impulse(null));
            t.add(new RapidFire(null));
            t.add(new Battle(null));
            playerSort.put(PlayerClass.MANAGER, t);
        }

        private static void generateViichan() {
            Array<AbstractSkill> t = new Array<>();
            // Bronze
            t.add(new DiaSword(null));
            t.add(new ChainMail(null));
            t.add(new Overpower(null));
            t.add(new Insight(null));
            t.add(new Cross(null));
            t.add(new Stab(null));
            t.add(new Onslaught(null));
            t.add(new Counter(null));
            t.add(new Linked(null));
            t.add(new Disarm(null));
            playerSort.put(PlayerClass.VIICHAN, t);
        }

        private static void generateWak() {
            Array<AbstractSkill> t = new Array<>();
            t.add(new Boost(null));
            t.add(new Encourage(null));
            t.add(new AvantProtect(null));
            t.add(new ShieldPush(null));
            t.add(new Faith(null));
            t.add(new Obstinate(null));
            t.add(new IronForm(null));
            t.add(new Guard(null));
            t.add(new Vanguard(null));
            playerSort.put(PlayerClass.WAK, t);
        }

        @SuppressWarnings("NewApi")
        public static Array<AbstractSkill> getRandomSkill(AbstractPlayer p, int amount) {
            Array<AbstractSkill> a = new Array<>();
            Array<AbstractSkill> b = new Array<>();
            boolean t;
            for (AbstractSkill s : normalSkills.get(p.playerClass)) {
                t = true;
                for (AbstractSkill ss : p.deck) {
                    if (s.id.equals(ss.id)) {
                        t = false;
                        break;
                    }
                }
                if (t) b.add(s);
            }
            staticShuffle(b);
            for (int i = 0; i < amount; i++) {
                AbstractSkill tt;
                tt = Objects.requireNonNull(b.get(i).clone());
                tt.owner = p;
                HashMap<String, Boolean> temp = UnlockHandler.unlocks.get(UnlockHandler.Unlocks.SKILL);
                if(temp.get(tt.id) == null) temp.put(tt.id, true);
                else if (!temp.get(tt.id)) temp.replace(tt.id, true);
                a.add(tt);
            }
            UnlockHandler.save();
            return a;
        }

        public static AbstractSkill getRandomSkill(AbstractPlayer p) {
            return getRandomSkill(p, 1).get(0);
        }

        @SuppressWarnings("NewApi")
        public static AbstractSkill getRandomUpgradedSkillFromDeck(AbstractPlayer p, boolean isNone) {
            Array<AbstractSkill> a = new Array<>();
            Array<AbstractSkill> b = p.deck;
            for (int i = 0; i < b.size; i++) {
                AbstractSkill s = b.get(i);
                if (s.upgraded != isNone) {
                    a.add(s.clone());
                    HashMap<String, Boolean> temp = UnlockHandler.unlocks.get(UnlockHandler.Unlocks.SKILL);
                    if(temp.get(s.id) == null) temp.put(s.id, true);
                    else if (!temp.get(s.id)) temp.replace(s.id, true);
                }
            }
            if (a.size > 0) {
                UnlockHandler.save();
                return staticShuffle(a).get(0);
            } else return null;
        }

        public static Array<AbstractSkill> staticShuffle(Array<AbstractSkill> array) {
            return staticShuffle(array, skillRandom);
        }

        public static Array<AbstractSkill> staticShuffle(Array<AbstractSkill> array, RandomXC r) {
            AbstractSkill[] items = array.toArray(AbstractSkill.class);
            for (int i = array.size - 1; i >= 0; --i) {
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
