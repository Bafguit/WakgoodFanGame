package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.enemies.TestEnemy2;
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

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.skillRandom;
import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;

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

        public static final HashMap<PlayerClass, Array<AbstractSkill>> allSkill = new HashMap<>();

        public static void generateSkill() {
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
            t.add(new Test11(null));
            t.add(new Test12(null));
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
            t.add(new Test31(null));
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
            t.add(new Test61(null));
            t.add(new Test62(null));
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
            Array<AbstractSkill> b = staticShuffle(allSkill.get(p.playerClass));
            for(int i = 0; i < amount; i++) {
                AbstractSkill t;
                t = Objects.requireNonNull(b.get(i).cpy());
                t.owner = p;
                a.add(t);
            }
            return a;
        }

        public static AbstractSkill getRandomSkill(AbstractPlayer p) {
            AbstractSkill t = Objects.requireNonNull(staticShuffle(allSkill.get(p.playerClass)).get(0).cpy());
            t.owner = p;
            return t;
        }

        public static AbstractSkill getRandomUpgradedSkillFromDeck(AbstractPlayer p, boolean isNone) {
            Array<AbstractSkill> a = new Array<>();
            Array<AbstractSkill> b = p.deck;
            for (int i = 0; i < b.size; i++) {
                AbstractSkill s = b.get(i);
                if(s.upgraded != isNone) a.add(s.cpy());
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
