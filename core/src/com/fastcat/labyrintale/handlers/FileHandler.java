package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass;
import lombok.Getter;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;

public class FileHandler implements Disposable {

    //TextureAtlas
    public static final TextureAtlas character = new TextureAtlas("img/char/char.atlas");
    /**
     * Combined all static "JsonValue" variables into hash map to optimize memory.
     */
    private static final HashMap<JsonType, JsonValue> jsonMap = new HashMap<>();

    public static JsonValue getJsonValue(JsonType jsonType){
        return jsonMap.get(jsonType);
    }

    //관리
    private static final Array<HashMap> maps = new Array<>();
    @Getter
    private static final HashMap<String, Sprite> bg = new HashMap<>();
    @Getter
    private static final HashMap<String, Sprite> ui = new HashMap<>();
    @Getter
    private static final HashMap<String, Sprite> vfx = new HashMap<>();
    @Getter
    private static final HashMap<String, FileHandle> skeleton = new HashMap<>();
    @Getter
    private static final HashMap<String, TextureAtlas> atlas = new HashMap<>();
    //캐릭터
    @Getter
    private static final HashMap<PlayerClass, Sprite> charImg = new HashMap<>();
    @Getter
    private static final HashMap<PlayerClass, Sprite> charImgBig = new HashMap<>();
    @Getter
    private static final HashMap<PlayerClass, Sprite> charImgTiny = new HashMap<>();
    @Getter
    private static final HashMap<PlayerClass, Sprite> charBgImg = new HashMap<>();
    @Getter
    private static final HashMap<PlayerClass, Sprite> charPanelImg = new HashMap<>();
    @Getter
    private static final HashMap<AdvisorClass, Sprite> advImg = new HashMap<>();
    //스킬
    @Getter
    private static final HashMap<String, Sprite> skillImg = new HashMap<>();
    //상태
    @Getter
    private static final HashMap<String, Sprite> statusImg = new HashMap<>();
    //상태
    @Getter
    private static final HashMap<String, Sprite> itemImg = new HashMap<>();
    private static final HashMap<String, Sprite> itemImgTrans = new HashMap<>();
    //이벤트
    @Getter
    private static final HashMap<String, Sprite> eventImg = new HashMap<>();
    //영상
    @Getter
    private static final HashMap<String, FileHandle> video = new HashMap<>();
    /***
     * Instance of handler.
     * Initialized on getInstance()
     */
    private static FileHandler instance;

    static {
        jsonMap.put(JsonType.ADV_JSON, generateJson("json/advisors.json"));
        jsonMap.put(JsonType.CHAR_JSON, generateJson("json/chars.json"));
        jsonMap.put(JsonType.CHOICE_JSON, generateJson("json/choices.json"));
        jsonMap.put(JsonType.STATUS_JSON, generateJson("json/status.json"));
        jsonMap.put(JsonType.ENEMY_JSON, generateJson("json/enemies.json"));
        jsonMap.put(JsonType.EVENT_JSON, generateJson("json/events.json"));
        jsonMap.put(JsonType.ITEM_JSON, generateJson("json/items.json"));
        jsonMap.put(JsonType.CARD_JSON_BASIC, generateJson("json/skill/basicCards.json"));
        jsonMap.put(JsonType.CARD_JSON_WAK, generateJson("json/skill/wakCards.json"));
        jsonMap.put(JsonType.CARD_JSON_MANAGER, generateJson("json/skill/managerCards.json"));
        jsonMap.put(JsonType.CARD_JSON_INE, generateJson("json/skill/ineCards.json"));
        jsonMap.put(JsonType.CARD_JSON_VIICHAN, generateJson("json/skill/viichanCards.json"));
        jsonMap.put(JsonType.CARD_JSON_LILPA, generateJson("json/skill/lilpaCards.json"));
        jsonMap.put(JsonType.CARD_JSON_BURGER, generateJson("json/skill/burgerCards.json"));
        jsonMap.put(JsonType.CARD_JSON_GOSEGU, generateJson("json/skill/goseguCards.json"));
        jsonMap.put(JsonType.CARD_JSON_JURURU, generateJson("json/skill/jururuCards.json"));
        jsonMap.put(JsonType.CARD_JSON_ADV, generateJson("json/skill/advCards.json"));
        jsonMap.put(JsonType.CARD_JSON_ENEMY, generateJson("json/skill/enemyCards.json"));
    }
    private FileHandler() {
        generateHashMap();
        generateBG();
        generateUI();
        generateVFX();
        generateCharImg();
        generateSkeleton();
        StringHandler.generate();
        generateAdvImg();
        generateSkillImg();
        generateStatusImg();
        generateItemImg();
        generateEventImg();
        generateVideo();
        setAntiAliased();
    }

    /***
     * Returns instance of handler, if not exist, create new.
     * @return instance of handler
     */
    public static FileHandler getInstance() {
        if (instance == null)
            return (instance = new FileHandler());
        return instance;
    }

    private static JsonValue generateJson(String url) {
        JsonReader jsonReader = new JsonReader();
        FileHandle fileHandle = Gdx.files.internal(url);
        InputStreamReader is = new InputStreamReader(fileHandle.read(), StandardCharsets.UTF_8);
        return jsonReader.parse(is);
    }

    private void generateHashMap() {
        maps.add(bg);
        maps.add(ui);
        maps.add(vfx);
        maps.add(charImg);
        maps.add(charImgBig);
        maps.add(charImgTiny);
        maps.add(charBgImg);
        maps.add(charPanelImg);
        maps.add(advImg);
        maps.add(skillImg);
        maps.add(statusImg);
        maps.add(itemImg);
        maps.add(itemImgTrans);
        //maps.put("eventImg", eventImg);
    }

    private void generateVideo() {
        video.clear();
        video.put("LOGO", Gdx.files.internal("video/logo.webm"));
    }

    private void generateSkeleton() {
        for (JsonValue js : jsonMap.get(JsonType.ENEMY_JSON)) {
            skeleton.put(js.name, Gdx.files.internal("spine/enemy/" + js.name + "/skeleton.json"));
            atlas.put(js.name, new TextureAtlas("spine/enemy/" + js.name + "/skeleton.atlas"));
        }
    }

    private void generateBG() {
        bg.clear();
        bg.put("BG_BLACK", new Sprite(new Texture("img/ui/fade.png")));
        bg.put("BG_MAIN", new Sprite(new Texture("img/bg/main.png")));
        bg.put("BG_DEAD", new Sprite(new Texture("img/bg/dead.png")));
        bg.put("BG_WIN", new Sprite(new Texture("img/bg/win.png")));
        bg.put("BG_MAP", new Sprite(new Texture("img/bg/map.png")));
        bg.put("BG_CHARSELECT", new Sprite(new Texture("img/bg/charselect.png")));
        bg.put("BG_BATTLE", new Sprite(new Texture("img/bg/battle.png")));
    }

    private void generateVFX() {
        vfx.clear();
        vfx.put("HIT_LIGHT", new Sprite(new Texture("img/vfx/hit_s.png")));
        vfx.put("HIT_HEAVY", new Sprite(new Texture("img/vfx/hit_b.png")));
        vfx.put("INFECTION", new Sprite(new Texture("img/vfx/infection.png")));
        vfx.put("LIGHTNING", new Sprite(new Texture("img/vfx/lightning.png")));
        vfx.put("BURN", new Sprite(new Texture("img/vfx/burn.png")));
        vfx.put("SHIELD", new Sprite(new Texture("img/vfx/shield.png")));
        vfx.put("SLASH_H", new Sprite(new Texture("img/vfx/slash_h.png")));
        vfx.put("SLASH_V", new Sprite(new Texture("img/vfx/slash_v.png")));
        vfx.put("SLASH_D", new Sprite(new Texture("img/vfx/slash_d.png")));
        vfx.put("SMASH", new Sprite(new Texture("img/vfx/smash.png")));
    }

    private void generateUI() {
        ui.clear();
        ui.put("FADE", new Sprite(new Texture("img/ui/fade.png")));
        ui.put("TURN_BG", new Sprite(new Texture("img/ui/tc.png")));
        ui.put("MENU_SELECT", new Sprite(new Texture("img/ui/menuSelect.png")));
        ui.put("CONTROL_PANEL", new Sprite(new Texture("img/ui/cPanel.png")));
        ui.put("BATTLE_PANEL", new Sprite(new Texture("img/ui/cPanel_b.png")));
        ui.put("ENERGY_ORB", new Sprite(new Texture("img/ui/energy.png")));
        ui.put("BORDER", new Sprite(new Texture("img/ui/border.png")));
        ui.put("BORDER_M", new Sprite(new Texture("img/ui/border_m.png")));
        ui.put("BORDER_B", new Sprite(new Texture("img/ui/border_p.png")));
        ui.put("BORDER_S", new Sprite(new Texture("img/ui/border_s.png")));
        ui.put("BORDER_SS", new Sprite(new Texture("img/ui/border_ss.png")));
        ui.put("BORDER_V", new Sprite(new Texture("img/ui/border_v.png")));
        ui.put("BACK", new Sprite(new Texture("img/ui/back.png")));
        ui.put("NEXT", new Sprite(new Texture("img/ui/next.png")));
        ui.put("DECK", new Sprite(new Texture("img/ui/deck.png")));
        ui.put("DRAW", new Sprite(new Texture("img/ui/draw.png")));
        ui.put("GOLD", new Sprite(new Texture("img/ui/gold.png")));
        ui.put("DISCARD", new Sprite(new Texture("img/ui/discard.png")));
        ui.put("REWARD_CARD", new Sprite(new Texture("img/ui/deck.png")));
        ui.put("ENTITY_POINT", new Sprite(new Texture("img/ui/entityPoint.png")));
        ui.put("PLAYER_POINT", new Sprite(new Texture("img/ui/playerPoint.png")));
        ui.put("CHAR_SKILL_REWARD", new Sprite(new Texture("img/ui/charSkillReward.png")));
        ui.put("WAY_SELECT", new Sprite(new Texture("img/ui/wayBG.png")));
        ui.put("EVENT_CHOICE", new Sprite(new Texture("img/ui/event_choice.png")));
        ui.put("CHECK_OFF", new Sprite(new Texture("img/ui/check_0.png")));
        ui.put("CHECK_ON", new Sprite(new Texture("img/ui/check_1.png")));
        ui.put("OPTION_PANEL", new Sprite(new Texture("img/ui/optionPanel.png")));
        ui.put("SHIELD", new Sprite(new Texture("img/ui/shield.png")));
        ui.put("LOGO", new Sprite(new Texture("img/ui/logo.png")));
        ui.put("TITLE", new Sprite(new Texture("img/ui/title.png")));
        ui.put("CURSOR", new Sprite(new Texture("img/ui/cursor.png")));
        ui.put("SLIDE_A", new Sprite(new Texture("img/ui/slide_a.png")));
        ui.put("SLIDE_B", new Sprite(new Texture("img/ui/slide_b.png")));
        ui.put("SLIDE_L", new Sprite(new Texture("img/ui/slide_l.png")));
        ui.put("SLIDE_SL", new Sprite(new Texture("img/ui/slide_sl.png")));
        ui.put("SLIDE_SR", new Sprite(new Texture("img/ui/slide_sr.png")));
        ui.put("SLOT_UP", new Sprite(new Texture("img/ui/slot_up.png")));
        ui.put("LEFT", new Sprite(new Texture("img/ui/left.png")));
        ui.put("RIGHT", new Sprite(new Texture("img/ui/right.png")));
        ui.put("MYSTERY", new Sprite(new Texture("img/ui/mystery.png")));
        ui.put("HEAL", new Sprite(new Texture("img/ui/heal.png")));
        ui.put("UPGRADE", new Sprite(new Texture("img/ui/upgrade.png")));
        ui.put("HEART", new Sprite(new Texture("img/ui/heart.png")));
        ui.put("REST", new Sprite(new Texture("img/ui/restNode.png")));
        ui.put("BATTLE", new Sprite(new Texture("img/ui/battle_n.png")));
        ui.put("ELITE", new Sprite(new Texture("img/ui/battle_e.png")));
        ui.put("BOSS", new Sprite(new Texture("img/ui/battle_b.png")));
        ui.put("LOOK", new Sprite(new Texture("img/ui/mysteryNode.png")));
        ui.put("ENTRY", new Sprite(new Texture("img/ui/entryNode.png")));
        ui.put("SHOP", new Sprite(new Texture("img/ui/shopNode.png")));
        ui.put("MAP", new Sprite(new Texture("img/ui/map.png")));
    }

    private void generateCharImg() {
        charImg.clear();
        charImgBig.clear();
        charImgTiny.clear();
        charBgImg.clear();
        charPanelImg.clear();
        skeleton.clear();
        atlas.clear();
        for (PlayerClass cls : PlayerClass.values()) {
            String s = cls.toString().toLowerCase();
            charImg.put(cls, character.createSprite(s));
            charImgBig.put(cls, character.createSprite(s + "_p"));
            charImgTiny.put(cls, character.createSprite(s + "_s"));
            charBgImg.put(cls, character.createSprite(s + "_bg"));
            charPanelImg.put(cls, character.createSprite(s + "_cPanel"));
            skeleton.put(s, Gdx.files.internal("spine/" + s + "/skeleton.json"));
            atlas.put(s, new TextureAtlas("spine/" + s + "/skeleton.atlas"));
        }
    }

    private void generateAdvImg() {
        advImg.clear();
        for (AdvisorClass cls : AdvisorClass.values()) {
            String s = cls.toString().toLowerCase();
            advImg.put(cls, new Sprite(new Texture("img/advisor/" + s + ".png")));
        }
    }

    private void generateSkillImg() {
        skillImg.clear();
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_BASIC)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/basic/" + js.name + ".png")));
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_WAK)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/wak/" + js.name + ".png")));
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_MANAGER)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/manager/" + js.name + ".png")));
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_INE)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/ine/" + js.name + ".png")));
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_VIICHAN)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/viichan/" + js.name + ".png")));
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_LILPA)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/lilpa/" + js.name + ".png")));
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_BURGER)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/burger/" + js.name + ".png")));
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_GOSEGU)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/gosegu/" + js.name + ".png")));
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_JURURU)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/jururu/" + js.name + ".png")));
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_ENEMY)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/enemy/" + js.name + ".png")));
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_ADV)) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/adv/" + js.name + ".png")));
        }
    }

    private void generateStatusImg() {
        statusImg.clear();
        for (JsonValue js : jsonMap.get(JsonType.STATUS_JSON)) {
            if (!js.name.equals("")) {
                statusImg.put(js.name, new Sprite(new Texture("img/status/" + js.name + ".png")));
            }
        }
    }

    private void generateItemImg() {
        itemImg.clear();
        itemImgTrans.clear();
        for (JsonValue js : jsonMap.get(JsonType.ITEM_JSON)) {
            if (!js.name.equals("")) {
                itemImg.put(js.name, new Sprite(new Texture("img/item/" + js.name + ".png")));
                //itemImgTrans.put(js.name, new Sprite(new Texture("img/item/" + js.name + "_t.png"))); //TODO 이미지 추가
            }
        }
    }

    private void generateEventImg() {
        eventImg.clear();
        for (JsonValue js : jsonMap.get(JsonType.EVENT_JSON)) {
            if (!js.name.equals("")) {
                String[] ss = js.get("IMAGE").asStringArray();
                for (String id : ss) {
                    eventImg.put(id, new Sprite(new Texture("img/event/" + id + ".png")));
                }
            }
        }
    }

    private void setAntiAliased() {
        for (HashMap h : maps) {
            for (Object s : h.values()) {
                ((Sprite) s).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
        }
        /*
        for(TextureAtlas a : atlas.values()) {
            for (Texture t : a.getTextures()) {
                t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
        }*/
    }

    @Override
    public void dispose() {
        for (HashMap h : maps) {
            for (Object s : h.values()) {
                ((Sprite) s).getTexture().dispose();
            }
        }
        for (TextureAtlas a : atlas.values()) {
            a.dispose();
        }
    }

    public enum JsonType {
        ADV_JSON,
        CHAR_JSON,
        CHOICE_JSON,
        STATUS_JSON,
        ENEMY_JSON,
        EVENT_JSON,
        ITEM_JSON,
        CARD_JSON_BASIC,
        CARD_JSON_WAK,
        CARD_JSON_MANAGER,
        CARD_JSON_INE,
        CARD_JSON_VIICHAN,
        CARD_JSON_LILPA,
        CARD_JSON_BURGER,
        CARD_JSON_GOSEGU,
        CARD_JSON_JURURU,
        CARD_JSON_ADV,
        CARD_JSON_ENEMY
    }
}
