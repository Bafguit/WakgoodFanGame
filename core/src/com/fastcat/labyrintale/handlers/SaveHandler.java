package com.fastcat.labyrintale.handlers;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;
import static com.fastcat.labyrintale.handlers.GroupHandler.AdvisorGroup.getAdvisorInstance;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.screens.dead.DeadScreen;
import com.fastcat.labyrintale.utils.RandomXC;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SaveHandler {
    public static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    public static SaveData data;

    static {
        refresh();
    }

    public static void refresh() {
        if (Gdx.files.local("save.json").exists()) {
            try {
                data = mapper.readValue(Gdx.files.local("save.json").file(), SaveData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save() {
        data = SaveData.create();
        try {
            mapper.writeValue(Gdx.files.local("save.json").file(), data);
            AchieveHandler.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void finish(boolean refresh) {
        if (refresh) {
            refresh();
        } else {
            data = SaveData.create();
        }
        if (data.result == null) data.result = DeadScreen.ScreenType.DEAD;
        try {
            String name = "run_" + data.date + ".json";
            Gdx.files.local("runs").mkdirs();
            mapper.writeValue(Gdx.files.local("runs/" + name).file(), data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Gdx.files.local("save.json").exists()) Gdx.files.local("save.json").delete();
        data = null;
        AchieveHandler.save();
    }

    public static void load() {
        AchieveHandler.load();
        GroupHandler.RoomGroup.eventCount = data.eventCount;
        GroupHandler.RoomGroup.neutCount = data.neutCount;
        GroupHandler.RoomGroup.weakCount = data.weakCount;
        GroupHandler.RoomGroup.normalCount = data.normalCount;
        GroupHandler.RoomGroup.eliteCount = data.eliteCount;
        GroupHandler.RoomGroup.bossCount = data.eventCount;
        minute = data.minute;
        second = data.second;
        seed = data.random.seed;
        seedLong = data.random.seedLong;
        diff = data.diff;
        publicRandom = new RandomXC(seedLong, data.random.publicRandom);
        skillRandom = new RandomXC(seedLong, data.random.skillRandom);
        itemRandom = new RandomXC(seedLong, data.random.itemRandom);
        mapRandom = new RandomXC(seedLong, data.random.mapRandom);
        monsterRandom = new RandomXC(seedLong, data.random.monsterRandom);
        eventRandom = new RandomXC(seedLong, data.random.eventRandom);
        shopRandom = new RandomXC(seedLong, data.random.shopRandom);
        groupRandom = new RandomXC(seedLong);
        GroupHandler.RoomGroup.generateRoom();
        GroupHandler.RoomGroup.shuffleAll();

        floors = new AbstractFloor[4];
        for (int i = 0; i < 4; i++) {
            FloorData d = data.floors[i];
            if (d != null) {
                floors[i] = new AbstractFloor(d);
            }
        }
        floorNum = data.currentFloor;
        currentFloor = floors[floorNum - 1];

        for (int i = 0; i < 4; i++) {
            PlayerData d = data.players[i];
            AbstractPlayer p = getPlayerInstance(AbstractPlayer.PlayerClass.valueOf(d.id.toUpperCase()));
            p.setCustomSkin(SettingHandler.setting.skin.get(p.playerClass));
            p.defineIndex(d.index);
            p.goodLuck = d.goodLuck;
            p.badLuck = d.badLuck;
            p.maxRes = d.maxRes;
            p.minRes = d.minRes;
            p.isDead = d.isDead;
            p.maxHealth = d.maxHealth;
            p.health = d.health;

            for (int j = 0; j < 2; j++) {
                AbstractItem it = Objects.requireNonNull(
                        GroupHandler.ItemGroup.idSort.get(d.item[j]).clone());
                it.setOwner(p);
                p.item[j] = it;
            }

            AbstractSkill[] ss = new AbstractSkill[3];
            for (int j = 0; j < 3; j++) {
                SkillData sd = d.deck[j];
                AbstractSkill s = Objects.requireNonNull(
                        GroupHandler.SkillGroup.idSort.get(d.deck[j].id).clone());
                s.usedOnly = sd.usedOnly;
                s.owner = p;
                for (int k = 0; k < sd.upgradeCount; k++) {
                    s.upgrade();
                }
                ss[j] = s;
            }
            p.deck = new Array<>(ss);
            p.stat = d.stat;
            if (p.isDead) p.infoSpine.setAnimation("die");
            players[i] = p;
        }
        String ad = data.advisor;
        if (ad != null) {
            advisor = getAdvisorInstance(AbstractAdvisor.AdvisorClass.valueOf(ad.toUpperCase()));
        }

        itemAble = data.itemAble;
        maxSkillUp = data.selection;
        gold = data.gold;
        sp = data.sp;
        charge = data.charge;
        level = data.level;
        exp = data.exp;
        maxExp = data.maxExp;
        scoreHandle = data.scoreHandle;
        achvCheck = data.achvCheck;
    }

    public static class SaveData {
        public String date;
        public String version;
        public RandomData random;
        public int itemAble;
        public int selection;
        public int charge;
        public int gold;
        public int level;
        public int sp;
        public int exp;
        public int maxExp;
        public String advisor;
        public Difficulty diff;
        public PlayerData[] players = new PlayerData[4];
        public int currentFloor;
        public FloorData[] floors = new FloorData[4];
        public int eventCount;
        public int neutCount;
        public int weakCount;
        public int normalCount;
        public int eliteCount;
        public int bossCount;
        public int minute;
        public int second;
        public DeadScreen.ScreenType result;
        public ScoreHandle scoreHandle;
        public AchieveHandler.AchvLabCheck achvCheck;

        public static SaveData create() {
            SaveData temp = new SaveData();

            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            temp.date = AbstractLabyrinth.date = formatter.format(now);
            temp.version = "1.0.1";
            // temp.version = BuildInfo.BUILD_VERSION;
            temp.random = RandomData.create();
            for (int i = 0; i < 4; i++) {
                AbstractFloor f = AbstractLabyrinth.floors[i];
                temp.floors[i] = f != null ? FloorData.create(f) : null;
            }
            temp.currentFloor = floorNum;
            for (int i = 0; i < 4; i++) {
                temp.players[i] = PlayerData.create(AbstractLabyrinth.players[i]);
            }
            temp.advisor = AbstractLabyrinth.advisor != null ? AbstractLabyrinth.advisor.id : null;
            temp.itemAble = AbstractLabyrinth.itemAble;
            temp.selection = AbstractLabyrinth.maxSkillUp;
            temp.diff = AbstractLabyrinth.diff;
            temp.gold = AbstractLabyrinth.gold;
            temp.charge = AbstractLabyrinth.charge;
            temp.level = AbstractLabyrinth.level;
            temp.exp = AbstractLabyrinth.exp;
            temp.maxExp = AbstractLabyrinth.maxExp;
            temp.eventCount = GroupHandler.RoomGroup.eventCount;
            temp.neutCount = GroupHandler.RoomGroup.neutCount;
            temp.weakCount = GroupHandler.RoomGroup.weakCount;
            temp.normalCount = GroupHandler.RoomGroup.normalCount;
            temp.eliteCount = GroupHandler.RoomGroup.eliteCount;
            temp.bossCount = GroupHandler.RoomGroup.bossCount;
            temp.minute = AbstractLabyrinth.minute;
            temp.second = AbstractLabyrinth.second;
            temp.sp = AbstractLabyrinth.sp;
            temp.result = AbstractLabyrinth.result;
            temp.scoreHandle = AbstractLabyrinth.scoreHandle;
            temp.achvCheck = AbstractLabyrinth.achvCheck;

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
            temp.publicRandom = AbstractLabyrinth.publicRandom.getCounter();
            temp.skillRandom = AbstractLabyrinth.skillRandom.getCounter();
            temp.itemRandom = AbstractLabyrinth.itemRandom.getCounter();
            temp.mapRandom = AbstractLabyrinth.mapRandom.getCounter();
            temp.monsterRandom = AbstractLabyrinth.monsterRandom.getCounter();
            temp.eventRandom = AbstractLabyrinth.eventRandom.getCounter();
            temp.shopRandom = AbstractLabyrinth.shopRandom.getCounter();
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
            for (int i = 0; i < 13; i++) {
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
        public RoomData enemies;
        public ChoiceData[] choices;
        public int selected;
        public boolean isDone;

        public static WayData create(AbstractWay w) {
            WayData temp = new WayData();
            temp.type = w.type.toString();
            temp.enemies = RoomData.create(w.enemies);
            int l = w.choices.length;
            temp.choices = new ChoiceData[l];
            for (int i = 0; i < l; i++) {
                if (w.choices[i] != null) temp.choices[i] = ChoiceData.create(w.choices[i]);
            }
            temp.isDone = w.isDone;
            temp.selected = w.selected;
            return temp;
        }
    }

    public static class ChoiceData {
        public String type;
        public RoomData room;
        public boolean canGo;
        public ArrayList<Integer> linked;
        public ArrayList<Integer> linked2;

        public static ChoiceData create(AbstractChoice c) {
            ChoiceData temp = new ChoiceData();
            temp.type = c.type.toString();
            temp.room = RoomData.create(c.room);
            temp.linked = c.linked;
            temp.linked2 = c.linked2;
            temp.canGo = c.canGo;
            return temp;
        }
    }

    public static class RoomData {
        public String id;
        public boolean battleDone;
        public boolean isDone;

        public static RoomData create(AbstractRoom r) {
            if (r != null) {
                RoomData temp = new RoomData();
                temp.id = r.id;
                temp.battleDone = r.battleDone;
                temp.isDone = r.isDone;
                return temp;
            } else return null;
        }
    }

    public static class PlayerData {
        public String id;
        public String[] item = new String[2];
        public SkillData[] deck;
        public AbstractEntity.EntityStat stat;
        public boolean isDead;
        public int goodLuck;
        public int badLuck;
        public int index;
        public int maxRes;
        public int minRes;
        public int maxHealth;
        public int health;

        public static PlayerData create(AbstractPlayer e) {
            PlayerData temp = new PlayerData();
            temp.id = e.id;
            for (int i = 0; i < 2; i++) {
                AbstractItem t = e.item[i];
                temp.item[i] = t != null ? t.id : null;
            }
            temp.deck = new SkillData[3];
            for (int i = 0; i < 3; i++) {
                temp.deck[i] = SkillData.create(e.deck.get(i));
            }
            temp.isDead = !e.isAlive();
            temp.index = e.index;
            temp.maxRes = e.maxRes;
            temp.minRes = e.minRes;
            temp.maxHealth = e.maxHealth;
            temp.health = e.health;
            temp.stat = e.stat;
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
