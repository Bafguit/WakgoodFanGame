package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.enemies.TestEnemy2;
import com.fastcat.labyrintale.rooms.enemy.normal.Test;
import com.fastcat.labyrintale.rooms.enemy.weak.Weak1;
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

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.monsterRandom;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.skillRandom;
import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;

public class GroupHandler {

    private static final AbstractEnemy[] TEST =
            new AbstractEnemy[] {new TestEnemy(), new TestEnemy2(), new TestEnemy2(), new TestEnemy()};

    public static HashMap<String, AbstractFloor> floorGroup = new HashMap<>();
    public static HashMap<String, AbstractEvent> eventGroup = new HashMap<>();
    public static HashMap<String, AbstractStatus> statusGroup = new HashMap<>();

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

    public static class EnemyGroup {

        public static HashMap<Integer, Array<AbstractRoom>> weakGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> normalGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> eliteGroup = new HashMap<>();
        public static HashMap<Integer, Array<AbstractRoom>> bossGroup = new HashMap<>();

        public static void generateEnemy() {
            generateWeak();
            generateNormal();
            generateElite();
            generateBoss();
        }

        private static void generateWeak() {
            weakGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new Weak1());
            weakGroup.put(1, t);
        }

        private static void generateNormal() {
            normalGroup.clear();
            Array<AbstractRoom> t = new Array<>();
            t.add(new Test());
            normalGroup.put(1, t);
        }

        private static void generateElite() {

        }

        private static void generateBoss() {

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

        public static Array<AbstractRoom> staticShuffle(Array<AbstractRoom> array, RandomXS128 r) {
            AbstractRoom[] items = array.toArray(AbstractRoom.class);
            for(int i = array.size - 1; i >= 0; --i) {
                int ii = r.nextInt(i + 1);
                AbstractRoom temp = items[i];
                items[i] = items[ii];
                items[ii] = temp;
            }
            array.clear();
            array.addAll(items);
            return array;
        }
    }

    public static class SkillGroup {

        public static final HashMap<PlayerClass, Array<AbstractSkill>> allSkill = new HashMap<>();

        public static void generateSkill() {
            allSkill.clear();
            generateBurger();
            generateGosegu();
            generateIne();
            generateJururu();
            generateLilpa();
            generateManager();
            generateViichan();
            generateWak();
        }

        private static void generateBurger() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new Test1(null));
            t.add(new Test2(null));
            t.add(new Test3(null));
            //Silver
            t.add(new Test4(null));
            t.add(new Test5(null));
            t.add(new Test6(null));
            //Gold
            t.add(new Test7(null));
            t.add(new Test8(null));
            allSkill.put(PlayerClass.BURGER, t);
        }

        private static void generateGosegu() {
            Array<AbstractSkill> t = new Array<>();
            //Bronze
            t.add(new SeguBeam(null));
            t.add(new Provoke(null));
            t.add(new Test13(null));
            //Silver
            t.add(new Test14(null));
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
            t.add(new Captivate(null));
            t.add(new Test32(null));
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
            t.add(new Test42(null));
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
            t.add(new Test63(null));
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
            t.add(new Test71(null));
            t.add(new Test72(null));
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
            AbstractSkill t = getRandomSkill(p, 1).get(0);
            t.owner = p;
            return t;
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

        public static Array<AbstractSkill> staticShuffle(Array<AbstractSkill> array, RandomXS128 r) {
            AbstractSkill[] items = array.toArray(AbstractSkill.class);
            for(int i = array.size - 1; i >= 0; --i) {
                int ii = r.nextInt(i + 1);
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
