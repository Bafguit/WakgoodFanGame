package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.RandomXC;
import com.fastcat.labyrintale.abstracts.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;
import static com.fastcat.labyrintale.handlers.GroupHandler.AdvisorGroup.getAdvisorInstance;

public class SaveHandler {

    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static FileHandle saveFile = Gdx.files.local("save.json");
    public static SaveData data;
    public static boolean hasSave;

    public SaveHandler() {
        refresh();
    }

    public static void refresh() {
        hasSave = saveFile.exists();
        if(hasSave) {
            try {
                data = mapper.readValue(new File("save.json"), SaveData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save() {
        data = SaveData.create();
        try {
            mapper.writeValue(new File("save.json"), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void finish(boolean refresh) {
        if(refresh) refresh();
        /*if(hasSave) {
            try {
                mapper.writeValue(new File("run_" + data.date + ".json"), data);
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveFile.delete();
        }*/
        refresh();
    }

    public static void load() {
        GroupHandler.RoomGroup.eventCount = data.eventCount;
        GroupHandler.RoomGroup.neutCount = data.neutCount;
        GroupHandler.RoomGroup.weakCount = data.weakCount;
        GroupHandler.RoomGroup.normalCount = data.normalCount;
        GroupHandler.RoomGroup.eliteCount = data.eliteCount;
        GroupHandler.RoomGroup.bossCount = data.eventCount;
        seed = data.random.seed;
        seedLong = data.random.seedLong;
        publicRandom = new RandomXC(seedLong, data.random.publicRandom);
        skillRandom = new RandomXC(seedLong, data.random.skillRandom);
        itemRandom = new RandomXC(seedLong, data.random.itemRandom);
        mapRandom = new RandomXC(seedLong, data.random.mapRandom);
        monsterRandom = new RandomXC(seedLong, data.random.monsterRandom);
        eventRandom = new RandomXC(seedLong, data.random.eventRandom);
        shopRandom = new RandomXC(seedLong, data.random.shopRandom);
        groupRandom = new RandomXC(seedLong);
        GroupHandler.RoomGroup.shuffleAll();
        restriction.setData(data.restriction);

        floors = new AbstractFloor[4];
        for(int i = 0; i < 4; i++) {
            FloorData d = data.floors[i];
            if(d != null) {
                floors[i] = new AbstractFloor(d);
            }
        }
        floorNum = data.currentFloor;
        currentFloor = floors[floorNum - 1];

        for (int i = 0; i < 4; i++) {
            PlayerData d = data.players[i];
            AbstractPlayer p = getPlayerInstance(AbstractPlayer.PlayerClass.valueOf(d.id.toUpperCase()));
            p.defineIndex(d.index);
            p.isDead = d.isDead;
            p.maxHealth = d.maxHealth;
            p.health = d.health;

            for(int j = 0; j < 2; j++) {
                AbstractItem it = Objects.requireNonNull(GroupHandler.ItemGroup.idSort.get(d.item[j]).clone());
                it.setOwner(p);
                p.item[j] = it;
            }

            AbstractSkill[] ss = new AbstractSkill[3];
            for(int j = 0; j < 3; j++) {
                SkillData sd = d.deck[j];
                AbstractSkill s = Objects.requireNonNull(GroupHandler.SkillGroup.idSort.get(d.deck[j].id).clone());
                s.usedOnly = sd.usedOnly;
                s.owner = p;
                for(int k = 0; k < sd.upgradeCount; k++) {
                    s.upgrade();
                }
                ss[j] = s;
                p.slot[j] = d.slot[j];
            }
            p.deck = new Array<>(ss);
            if(p.isDead) p.infoSpine.setAnimation("die");
            players[i] = p;
        }
        AdvisorData ad = data.advisor;
        if(ad != null) {
            AbstractAdvisor a = getAdvisorInstance(AbstractAdvisor.AdvisorClass.valueOf(ad.cls.toUpperCase()));
            a.skill.usedOnly = ad.skill.usedOnly;
            for (int i = 0; i < ad.skill.upgradeCount; i++) {
                a.skill.upgrade();
            }
            advisor = a;
        }

        itemAble = data.itemAble;
        maxSlotUp = data.selection;
        maxEnergy = data.maxEnergy;
        gold = data.gold;
        bleak = data.bleak;
        bleakMin = data.bleakMin;
        bleakMax = data.bleakMax;
        bleakAdd = data.bleakAdd;
    }

    public static class SaveData {
        public String date;
        public RandomData random;
        public RestrictionData restriction;
        public int itemAble;
        public int selection;
        public int maxEnergy;
        public int gold;
        public int bleak;
        public int bleakMin;
        public int bleakMax;
        public int bleakAdd;
        public AdvisorData advisor;
        public PlayerData[] players = new PlayerData[4];
        public int currentFloor;
        public FloorData[] floors = new FloorData[4];
        public int eventCount;
        public int neutCount;
        public int weakCount;
        public int normalCount;
        public int eliteCount;
        public int bossCount;

        public static SaveData create() {
            SaveData temp = new SaveData();

            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            temp.date = formatter.format(now);
            temp.random = RandomData.create();
            temp.restriction = RestrictionData.create();
            for(int i = 0; i < 4; i++) {
                AbstractFloor f = AbstractLabyrinth.floors[i];
                temp.floors[i] = f != null ? FloorData.create(f) : null;
            }
            temp.currentFloor = floorNum;
            for(int i = 0; i < 4; i++) {
                temp.players[i] = PlayerData.create(AbstractLabyrinth.players[i]);
            }
            temp.advisor = AbstractLabyrinth.advisor != null ? AdvisorData.create(AbstractLabyrinth.advisor) : null;
            temp.itemAble = AbstractLabyrinth.itemAble;
            temp.selection = AbstractLabyrinth.maxSlotUp;
            temp.maxEnergy = AbstractLabyrinth.maxEnergy;
            temp.gold = AbstractLabyrinth.gold;
            temp.bleak = AbstractLabyrinth.bleak;
            temp.bleakMin = AbstractLabyrinth.bleakMin;
            temp.bleakMax = AbstractLabyrinth.bleakMax;
            temp.bleakAdd = AbstractLabyrinth.bleakAdd;
            temp.eventCount = GroupHandler.RoomGroup.eventCount;
            temp.neutCount = GroupHandler.RoomGroup.neutCount;
            temp.weakCount = GroupHandler.RoomGroup.weakCount;
            temp.normalCount = GroupHandler.RoomGroup.normalCount;
            temp.eliteCount = GroupHandler.RoomGroup.eliteCount;
            temp.bossCount = GroupHandler.RoomGroup.bossCount;

            return temp;
        }
    }

    public static class RandomData {
        public String seed;
        public long seedLong;
        public int publicRandom;
        public int skillRandom;
        public int itemRandom;
        public int mapRandom;
        public int monsterRandom;
        public int eventRandom;
        public int shopRandom;

        public static RandomData create() {
            RandomData temp = new RandomData();
            temp.seed = AbstractLabyrinth.seed;
            temp.seedLong = AbstractLabyrinth.seedLong;
            temp.publicRandom = AbstractLabyrinth.publicRandom.counter;
            temp.skillRandom = AbstractLabyrinth.skillRandom.counter;
            temp.itemRandom = AbstractLabyrinth.itemRandom.counter;
            temp.mapRandom = AbstractLabyrinth.mapRandom.counter;
            temp.monsterRandom = AbstractLabyrinth.monsterRandom.counter;
            temp.eventRandom = AbstractLabyrinth.eventRandom.counter;
            temp.shopRandom = AbstractLabyrinth.shopRandom.counter;
            return temp;
        }
    }

    public static class RestrictionData {
        public int GRW;
        public int STR;
        public int INT;
        public int HUG;
        public int FAM;
        public int FOG;
        public int POV;
        public int MSR;
        public int FTG;
        public int ANX;

        public static RestrictionData create() {
            RestrictionData temp = new RestrictionData();
            temp.GRW = restriction.GRW;
            temp.STR = restriction.STR;
            temp.INT = restriction.INT;
            temp.HUG = restriction.HUG;
            temp.FAM = restriction.FAM;
            temp.FOG = restriction.FOG;
            temp.POV = restriction.POV;
            temp.MSR = restriction.MSR;
            temp.FTG = restriction.FTG;
            temp.ANX = restriction.ANX;
            return temp;
        }
    }

    public static class FloorData {
        public RoomData currentRoom;
        public WayData currentWay;
        public WayData[] ways = new WayData[13];
        public boolean isDone;
        public int floorNum;
        public int num;

        public static FloorData create(AbstractFloor f) {
            FloorData temp = new FloorData();
            temp.currentRoom = f.currentRoom != null ? RoomData.create(f.currentRoom) : null;
            temp.currentWay = f.currentWay != null ? WayData.create(f.currentWay) : null;
            for(int i = 0; i < 13; i++) {
                temp.ways[i] = WayData.create(f.ways[i]);
            }
            temp.isDone = f.isDone;
            temp.floorNum = f.floorNum;
            temp.num = f.num;
            return temp;
        }
    }

    public static class WayData {
        public String type;
        public ChoiceData[] choices;
        public boolean isDone;

        public static WayData create(AbstractWay w) {
            WayData temp = new WayData();
            temp.type = w.type.toString();
            int l = w.choices.length;
            temp.choices = new ChoiceData[l];
            for(int i = 0; i < l; i++) {
                temp.choices[i] = ChoiceData.create(w.choices[i]);
            }
            temp.isDone = w.isDone;
            return temp;
        }
    }

    public static class ChoiceData {
        public String type;
        public RoomData room;
        public boolean must;
        public int prob;

        public static ChoiceData create(AbstractChoice c) {
            ChoiceData temp = new ChoiceData();
            temp.type = c.type.toString();
            temp.room = RoomData.create(c.room);
            temp.must = c.must;
            temp.prob = c.prob;
            return temp;
        }
    }

    public static class RoomData {
        public String id;
        public boolean battleDone;
        public boolean isDone;

        public static RoomData create(AbstractRoom r) {
            RoomData temp = new RoomData();
            temp.id = r.id;
            temp.battleDone = r.battleDone;
            temp.isDone = r.isDone;
            return temp;
        }
    }

    public static class PlayerData {
        public String id;
        public String[] item = new String[2];
        public SkillData[] deck;
        public boolean isDead;
        public int[] slot = new int[3];
        public int index;
        public int maxHealth;
        public int health;

        public static PlayerData create(AbstractPlayer e) {
            PlayerData temp = new PlayerData();
            temp.id = e.id;
            for(int i = 0; i < 2; i++) {
                AbstractItem t = e.item[i];
                temp.item[i] = t != null ? t.id : null;
            }
            temp.deck = new SkillData[3];
            for(int i = 0; i < 3; i++) {
                temp.deck[i] = SkillData.create(e.deck.get(i));
                temp.slot[i] = e.slot[i];
            }
            temp.isDead = !e.isAlive();
            temp.index = e.index;
            temp.maxHealth = e.maxHealth;
            temp.health = e.health;
            return temp;
        }
    }

    public static class AdvisorData {
        public String cls;
        public SkillData skill;

        public static AdvisorData create(AbstractAdvisor a) {
            AdvisorData temp = new AdvisorData();
            temp.cls = a.cls.toString();
            temp.skill = SkillData.create(a.skill);
            return temp;
        }
    }

    public static class SkillData {
        public String id;
        public boolean usedOnly;
        public int upgradeCount;

        public static SkillData create(AbstractSkill s) {
            SkillData temp = new SkillData();
            temp.id = s.id;
            temp.usedOnly = s.usedOnly;
            temp.upgradeCount = s.upgradeCount;
            return temp;
        }
    }
}
