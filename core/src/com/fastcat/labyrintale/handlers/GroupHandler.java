package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.enemies.TestEnemy2;
import com.fastcat.labyrintale.rewards.SkillReward;
import com.fastcat.labyrintale.rewards.SkillRewardNormal;

import java.util.HashMap;
import java.util.Objects;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.skillRandom;
import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;
import static com.fastcat.labyrintale.rewards.SkillReward.*;

public class GroupHandler {

    private static final AbstractEnemy[] TEST =
            new AbstractEnemy[] {new TestEnemy(), new TestEnemy2(), new TestEnemy2(), new TestEnemy()};

    public static HashMap<String, AbstractFloor> floorGroup = new HashMap<>();
    public static HashMap<String, AbstractEvent> eventGroup = new HashMap<>();
    public static Array<AbstractEnemy[]> normalGroup = new Array<>();
    public static Array<AbstractEnemy[]> eliteGroup = new Array<>();
    public static Array<AbstractEnemy[]> bossGroup = new Array<>();
    public static HashMap<String, AbstractStatus> statusGroup = new HashMap<>();
    public static HashMap<PlayerClass, Texture> cardImgGroup = new HashMap<>();

    public GroupHandler() {
        SkillGroup.generateSkill();
        generateEnemy();
    }
    
    public void generateStatus() {
        
    }

    public void generateEnemy() {
        //normalGroup[0].add(TEST);
    }

    public static class EventGroup {

    }

    public static class SkillGroup {

        private static final int BRONZE = 65; // 65%
        private static final int SILVER = 95; // 30%
        private static final int GOLD = 100; // 5%
        private static final int UPGRADE = 10; // 10%
        private static final int UPGRADE_UP = 20; // 20%

        public static final HashMap<PlayerClass, Array<AbstractSkill>> allSkill = new HashMap<>();
        public static final HashMap<PlayerClass, Array<AbstractSkill>> bronzeSkill = new HashMap<>();
        public static final HashMap<PlayerClass, Array<AbstractSkill>> silverSkill = new HashMap<>();
        public static final HashMap<PlayerClass, Array<AbstractSkill>> goldSkill = new HashMap<>();

        public static void generateSkill() {
            //TODO 카드 처음 생성은 여기서
        }

        public static Array<AbstractSkill> getRandomSkill(AbstractPlayer p, SkillRewardType type, int amount) {
            Array<AbstractSkill> a = new Array<>();
            Array<AbstractSkill> b = bronzeSkill.get(p.playerClass);
            Array<AbstractSkill> s = silverSkill.get(p.playerClass);
            Array<AbstractSkill> g = goldSkill.get(p.playerClass);
            switch (type) {
                case BRONZE:
                    staticShuffle(b);
                    for(int i = 0; i < amount; i++) {
                        AbstractSkill t;
                        t = Objects.requireNonNull(b.get(i).cpy());
                        t.owner = p;
                        if(skillRandom.nextInt(100) < UPGRADE) {
                            t.upgrade();
                        }
                        a.add(t);
                    }
                    return a;
                case SILVER:
                    staticShuffle(s);
                    for(int i = 0; i < amount; i++) {
                        AbstractSkill t;
                        t = Objects.requireNonNull(s.get(i).cpy());
                        t.owner = p;
                        if(skillRandom.nextInt(100) < UPGRADE) {
                            Objects.requireNonNull(t).upgrade();
                        }
                        a.add(t);
                    }
                    return a;
                case GOLD:
                    staticShuffle(g);
                    for(int i = 0; i < amount; i++) {
                        AbstractSkill t;
                        t = Objects.requireNonNull(g.get(i).cpy());
                        t.owner = p;
                        if(skillRandom.nextInt(100) < UPGRADE) {
                            Objects.requireNonNull(t).upgrade();
                        }
                        a.add(t);
                    }
                    return a;
                default:
                    staticShuffle(b);
                    staticShuffle(s);
                    staticShuffle(g);
                    for(int i = 0; i < amount; i++) {
                        AbstractSkill t;
                        int r = skillRandom.nextInt(100);
                        if (r < BRONZE) {
                            t = Objects.requireNonNull(b.get(i).cpy());
                        } else if(r < SILVER) {
                            t = Objects.requireNonNull(s.get(i).cpy());
                        } else {
                            t = Objects.requireNonNull(g.get(i).cpy());
                        }
                        t.owner = p;
                        if(skillRandom.nextInt(100) < UPGRADE) {
                            t.upgrade();
                        }
                        a.add(t);
                    }
                    return a;
            }
        }

        public static void staticShuffle(Array<AbstractSkill> array) {
            staticShuffle(array, skillRandom);
        }

        public static void staticShuffle(Array<AbstractSkill> array, RandomXS128 r) {
            AbstractSkill[] items = array.toArray(AbstractSkill.class);
            for(int i = array.size - 1; i >= 0; --i) {
                int ii = r.nextInt(i + 1);
                AbstractSkill temp = items[i];
                items[i] = items[ii];
                items[ii] = temp;
            }
            array.clear();
            array.addAll(items);
        }
    }
}
