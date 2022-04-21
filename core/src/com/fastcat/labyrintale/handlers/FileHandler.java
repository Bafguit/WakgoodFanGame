package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;

public class FileHandler implements Disposable {

    //json
    public static final JsonValue ADV_JSON = generateJson("json/advisors.json");
    public static final JsonValue CHAR_JSON = generateJson("json/chars.json");
    public static final JsonValue CHOICE_JSON = generateJson("json/choices.json");
    public static final JsonValue STATUS_JSON = generateJson("json/status.json");
    public static final JsonValue EVENT_JSON = generateJson("json/events.json");
    public static final JsonValue CARD_JSON_BASIC = generateJson("json/skill/basicCards.json");
    public static final JsonValue CARD_JSON_WAK = generateJson("json/skill/wakCards.json");
    public static final JsonValue CARD_JSON_MANAGER = generateJson("json/skill/managerCards.json");
    public static final JsonValue CARD_JSON_INE = generateJson("json/skill/ineCards.json");
    public static final JsonValue CARD_JSON_VIICHAN = generateJson("json/skill/viichanCards.json");
    public static final JsonValue CARD_JSON_LILPA = generateJson("json/skill/lilpaCards.json");
    public static final JsonValue CARD_JSON_BURGER = generateJson("json/skill/burgerCards.json");
    public static final JsonValue CARD_JSON_GOSEGU = generateJson("json/skill/goseguCards.json");
    public static final JsonValue CARD_JSON_JURURU = generateJson("json/skill/jururuCards.json");
    public static final JsonValue CARD_JSON_ADV = generateJson("json/skill/advCards.json");
    public static final JsonValue CARD_JSON_ENEMY = generateJson("json/skill/enemyCards.json");

    //사운드
    public static final Sound LILPAA = getSound("sound/sfx/skill/lilpaa.mp3");
    public static final Sound ATTACK_TEST = getSound("sound/sfx/attack/attackTest.ogg");
    public static final Sound CLICK = getSound("sound/sfx/click.wav");
    public static final Sound OVER = getSound("sound/sfx/over.wav");

    //BGM
    public static final Music BATTLE_1 = getMusic("sound/bgm/battle_1.mp3");

    //스파인 atlas
    public static final TextureAtlas NEKO_ATLAS = new TextureAtlas("spine/test/char_5_neko.atlas");

    //스파인 json
    public static final FileHandle NEKO_JSON = Gdx.files.internal("spine/test/char_5_neko.json");

    //관리
    private static final HashMap<String, HashMap> maps = new HashMap<>();
    public static final HashMap<String, Sprite> bg = new HashMap<>();
    public static final HashMap<String, Sprite> ui = new HashMap<>();

    //캐릭터
    public static final HashMap<PlayerClass, Sprite> charImg = new HashMap<>();
    public static final HashMap<PlayerClass, Sprite> charImgBig = new HashMap<>();
    public static final HashMap<PlayerClass, Sprite> charBgImg = new HashMap<>();
    public static final HashMap<AdvisorClass, Sprite> advImg = new HashMap<>();
    public static final HashMap<AdvisorClass, Sprite> advImgBig = new HashMap<>();
    public static final HashMap<AdvisorClass, Sprite> advBgImg = new HashMap<>();

    //스킬
    public static final HashMap<String, Sprite> skillImg = new HashMap<>();
    public static final HashMap<String, Sprite> skillImgBig = new HashMap<>();

    //상태
    public static final HashMap<String, Sprite> statusImg = new HashMap<>();
    public static final HashMap<String, Sprite> statusImgBig = new HashMap<>();

    //이벤트
    public static final HashMap<String, Sprite> eventImg = new HashMap<>();

    public FileHandler() {
        generateHashMap();
        generateBG();
        generateUI();
        generateCharImg();
        generateAdvImg();
        generateCharImg();
        StringHandler.generate();
        generateSkillImg();
        generateStatusImg();
        generateEventImg();
        setAntiAliased();
    }

    private static void generateHashMap() {
        maps.put("bg", bg);
        maps.put("ui", ui);
        maps.put("charImg", charImg);
        maps.put("charImgBig", charImgBig);
        maps.put("charBgImg", charBgImg);
        maps.put("advImg", advImg);
        maps.put("advImgBig", advImgBig);
        maps.put("advBgImg", advBgImg);
        maps.put("skillImg", skillImg);
        maps.put("skillImgBig", skillImgBig);
        maps.put("statusImg", statusImg);
        maps.put("statusImgBig", statusImgBig);
    }

    private static void generateBG() {
        bg.clear();
        bg.put("BG_BLACK", new Sprite(new Texture("img/ui/fade.png")));
        bg.put("BG_MAIN", new Sprite(new Texture("img/bg/main.png")));
        bg.put("BG_DEAD", new Sprite(new Texture("img/bg/dead.png")));
        bg.put("BG_WIN", new Sprite(new Texture("img/bg/win.png")));
        bg.put("BG_MAP", new Sprite(new Texture("img/bg/map.png")));
        bg.put("BG_CHARSELECT", new Sprite(new Texture("img/bg/charselect.png")));
        bg.put("BG_BATTLE", new Sprite(new Texture("img/bg/battle.png")));
    }

    private static void generateUI() {
        ui.clear();
        ui.put("FADE", new Sprite(new Texture("img/ui/fade.png")));
        ui.put("TURN_BG", new Sprite(new Texture("img/ui/tc.png")));
        ui.put("MENU_SELECT", new Sprite(new Texture("img/ui/menuSelect.png")));
        ui.put("CONTROL_PANEL", new Sprite(new Texture("img/ui/cPanel.png")));
        ui.put("ENERGY_ORB", new Sprite(new Texture("img/ui/energy.png")));
        ui.put("BORDER", new Sprite(new Texture("img/ui/border.png")));
        ui.put("BORDER_M", new Sprite(new Texture("img/ui/border_m.png")));
        ui.put("BORDER_B", new Sprite(new Texture("img/ui/border_p.png")));
        ui.put("BORDER_S", new Sprite(new Texture("img/ui/border_s.png")));
        ui.put("BORDER_SS", new Sprite(new Texture("img/ui/border_ss.png")));
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
    }

    private static void generateCharImg() {
        charImg.clear();
        charImgBig.clear();
        charBgImg.clear();
        for(PlayerClass cls : PlayerClass.values()) {
            String s = cls.toString().toLowerCase();
            charImg.put(cls, new Sprite(new Texture("img/char/" + s + ".png")));
            charImgBig.put(cls, new Sprite(new Texture("img/char/" + s + "_p.png")));
            charBgImg.put(cls, new Sprite(new Texture("img/char/" + s + "_bg.png")));
        }
    }

    private static void generateAdvImg() {
        advImg.clear();
        advImgBig.clear();
        advBgImg.clear();
        for(AdvisorClass cls : AdvisorClass.values()) {
            String s = cls.toString().toLowerCase();
            advImg.put(cls, new Sprite(new Texture("img/advisor/" + s + ".png")));
            advImgBig.put(cls, new Sprite(new Texture("img/advisor/" + s + "_s.png")));
            advBgImg.put(cls, new Sprite(new Texture("img/advisor/" + s + "_bg.png")));
        }
    }

    private static JsonValue generateJson(String url) {
        JsonReader jsonReader = new JsonReader();
        FileHandle fileHandle = Gdx.files.internal(url);
        InputStreamReader is = new InputStreamReader(fileHandle.read(), StandardCharsets.UTF_8);
        return jsonReader.parse(is);
    }

    private static Sound getSound(String url) {
        FileHandle fileHandle = Gdx.files.internal(url);
        return Gdx.audio.newSound(fileHandle);
    }

    private static Music getMusic(String url) {
        FileHandle fileHandle = Gdx.files.internal(url);
        return Gdx.audio.newMusic(fileHandle);
    }

    private static void generateSkillImg() {
        skillImg.clear();
        skillImgBig.clear();
        for (JsonValue js : CARD_JSON_BASIC) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/basic/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/basic/" + js.name + "_p.png")));
        }
        for (JsonValue js : CARD_JSON_WAK) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/wak/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/wak/" + js.name + "_p.png")));
        }
        for (JsonValue js : CARD_JSON_MANAGER) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/manager/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/manager/" + js.name + "_p.png")));
        }
        for (JsonValue js : CARD_JSON_INE) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/ine/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/ine/" + js.name + "_p.png")));
        }
        for (JsonValue js : CARD_JSON_VIICHAN) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/viichan/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/viichan/" + js.name + "_p.png")));
        }
        for (JsonValue js : CARD_JSON_LILPA) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/lilpa/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/lilpa/" + js.name + "_p.png")));
        }
        for (JsonValue js : CARD_JSON_BURGER) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/burger/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/burger/" + js.name + "_p.png")));
        }
        for (JsonValue js : CARD_JSON_GOSEGU) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/gosegu/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/gosegu/" + js.name + "_p.png")));
        }
        for (JsonValue js : CARD_JSON_JURURU) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/jururu/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/jururu/" + js.name + "_p.png")));
        }
        for (JsonValue js : CARD_JSON_ADV) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/adv/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/adv/" + js.name + "_p.png")));
        }
        for (JsonValue js : CARD_JSON_ENEMY) {
            skillImg.put(js.name, new Sprite(new Texture("img/skill/enemy/" + js.name + ".png")));
            skillImgBig.put(js.name, new Sprite(new Texture("img/skill/enemy/" + js.name + "_p.png")));
        }
    }

    private static void generateStatusImg() {
        statusImg.clear();
        statusImgBig.clear();
        for (JsonValue js : STATUS_JSON) {
            if(!js.name.equals("")) {
                statusImg.put(js.name, new Sprite(new Texture("img/status/" + js.name + ".png")));
                statusImgBig.put(js.name, new Sprite(new Texture("img/status/" + js.name + "_p.png")));
            }
        }
    }

    private static void generateEventImg() {
        eventImg.clear();
        for (JsonValue js : EVENT_JSON) {
            if(!js.name.equals("")) {
                String[] ss = js.get("IMAGE").asStringArray();
                for(String id : ss) {
                    eventImg.put(id, new Sprite(new Texture("img/event/" + id + ".png")));
                }
            }
        }
    }

    private static void setAntiAliased() {
        for(HashMap h : maps.values()) {
            for(Object s : h.values()) {
                ((Sprite) s).getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            }
        }
        /*
        for(Texture t : NEKO_ATLAS.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }*/
    }

    @Override
    public void dispose() {
        for(HashMap h : maps.values()) {
            for (Object s : h.values()) {
                ((Sprite) s).getTexture().dispose();
            }
        }
    }
}
