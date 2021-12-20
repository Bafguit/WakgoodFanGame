package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.enemies.TestEnemy2;

import java.util.HashMap;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;

public class GroupHandler {

    private static final AbstractEnemy[] TEST =
            new AbstractEnemy[] {new TestEnemy(), new TestEnemy2(), new TestEnemy2(), new TestEnemy()};

    public static HashMap<String, AbstractFloor> floorGroup;
    public static HashMap<String, AbstractEvent> eventGroup;
    public static Array<AbstractEnemy[]> normalGroup = new Array<>();
    public static Array<AbstractEnemy[]> eliteGroup = new Array<>();
    public static Array<AbstractEnemy[]> bossGroup = new Array<>();
    public static HashMap<String, AbstractStatus> statusGroup;
    public static HashMap<PlayerClass, Array<AbstractTalent>> skillGroup;
    public static HashMap<PlayerClass, Array<AbstractSkill>> cardGroup;
    public static HashMap<PlayerClass, Texture> cardImgGroup;

    public GroupHandler() {
        generateSkill();
        generateEnemy();
    }
    
    public void generateSkill() {

    }
    
    public void generateStatus() {
        
    }

    public void generateEnemy() {
        //normalGroup[0].add(TEST);
    }

    public static class EventGroup {

    }

    public static class CardGroup {
        public static HashMap<PlayerClass, Array<AbstractSkill>> bronzeCard;
        public static HashMap<PlayerClass, Array<AbstractSkill>> silverCard;
        public static HashMap<PlayerClass, Array<AbstractSkill>> goldCard;
    }
}
