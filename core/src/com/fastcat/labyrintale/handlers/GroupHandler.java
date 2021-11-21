package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;

import java.util.HashMap;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;

public class GroupHandler {
    public static HashMap<String, AbstractFloor> floorGroup;
    public static HashMap<String, AbstractEvent> eventGroup;
    public static HashMap<String, AbstractEnemy[]> enemyGroup;
    public static HashMap<String, AbstractStatus> statusGroup;
    public static HashMap<PlayerClass, Array<AbstractTalent>> skillGroup;
    public static HashMap<PlayerClass, Array<AbstractSkill>> cardGroup;
    public static HashMap<PlayerClass, Texture> cardImgGroup;

    public GroupHandler() {
        
    }
    
    public void generateCard() {
        
    }
    
    public void generateSkill() {
        
    }
    
    public void generateStatus() {
        
    }

    public void generateEnemy() {

    }

    public static class EventGroup {

    }
}
