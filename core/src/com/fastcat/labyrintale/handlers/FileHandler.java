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

    //BG
    public static final Sprite BG_BLACK = new Sprite(new Texture("img/fade.png"));
    public static final Sprite BG_MAIN = new Sprite(new Texture("img/bg/main.png"));
    public static final Sprite BG_DEAD = new Sprite(new Texture("img/bg/dead.png"));
    public static final Sprite BG_WIN = new Sprite(new Texture("img/bg/win.png"));
    public static final Sprite BG_MAP = new Sprite(new Texture("img/bg/map.png"));
    public static final Sprite BG_CHARSELECT = new Sprite(new Texture("img/bg/charselect.png"));
    public static final Sprite BG_BATTLE = new Sprite(new Texture("img/bg/battle.png"));

    //UI
    public static final Sprite FADE = new Sprite(new Texture("img/fade.png"));
    public static final Sprite TURN_BG = new Sprite(new Texture("img/tc.png"));
    public static final Sprite MENU_SELECT = new Sprite(new Texture("img/menuSelect.png"));
    public static final Sprite CONTROL_PANEL = new Sprite(new Texture("img/ui/cPanel.png"));
    public static final Sprite ENERGY_ORB = new Sprite(new Texture("img/ui/energy.png"));
    public static final Sprite BORDER = new Sprite(new Texture("img/border.png"));
    public static final Sprite BORDER_M = new Sprite(new Texture("img/border_m.png"));
    public static final Sprite BORDER_B = new Sprite(new Texture("img/border_p.png"));
    public static final Sprite BORDER_S = new Sprite(new Texture("img/border_s.png"));
    public static final Sprite BORDER_SS = new Sprite(new Texture("img/border_ss.png"));
    public static final Sprite BACK = new Sprite(new Texture("img/back.png"));
    public static final Sprite NEXT = new Sprite(new Texture("img/next.png"));
    public static final Sprite DECK = new Sprite(new Texture("img/ui/deck.png"));
    public static final Sprite DRAW = new Sprite(new Texture("img/ui/draw.png"));
    public static final Sprite GOLD = new Sprite(new Texture("img/ui/gold.png"));
    public static final Sprite DISCARD = new Sprite(new Texture("img/ui/discard.png"));
    public static final Sprite REWARD_CARD = new Sprite(new Texture("img/ui/deck.png"));
    public static final Sprite ENTITY_POINT = new Sprite(new Texture("img/entityPoint.png"));
    public static final Sprite PLAYER_POINT = new Sprite(new Texture("img/playerPoint.png"));
    public static final Sprite CHAR_SKILL_REWARD = new Sprite(new Texture("img/ui/charSkillReward.png"));
    public static final Sprite WAY_SELECT = new Sprite(new Texture("img/ui/wayBG.png"));

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

    //지울 예정
    public static final Sprite BURGER = new Sprite(new Texture("img/advisor/burger.png"));
    public static final Sprite BURGER_BG = new Sprite(new Texture("img/advisor/burger_bg.png"));
    public static final Sprite BURGER_S = new Sprite(new Texture("img/advisor/burger_s.png"));
    public static final Sprite SKILL_STRIKE = new Sprite(new Texture("img/skill/basic/Strike.png"));
    public static final Sprite SKILL_HEAL = new Sprite(new Texture("img/skill/basic/Heal.png"));
    public static final Sprite SKILL_LIGHT = new Sprite(new Texture("img/skill/basic/Light.png"));
    public static final Sprite SKILL_LOVE = new Sprite(new Texture("img/skill/basic/Love.png"));
    public static final Sprite SKILL_POISON = new Sprite(new Texture("img/skill/basic/Poison.png"));
    public static final Sprite SKILL_SHIELD = new Sprite(new Texture("img/skill/basic/Barrier.png"));
    public static final Sprite SKILL_SPIKE = new Sprite(new Texture("img/skill/basic/Spike.png"));

    public FileHandler() {
        generateCharImg();
        //generateAdvImg();
        generateCharImg();
        StringHandler.generate();
        generateSkillImg();
        generateStatusImg();
    }

    private static void generateCharImg() {
        charImg.clear();
        charImgBig.clear();
        charBgImg.clear();
        for(PlayerClass cls : PlayerClass.values()) {
            charImg.put(cls, new Sprite(new Texture("img/char/" + cls.toString().toLowerCase() + ".png")));
            //charImgBig.put(cls, new Sprite(new Texture("img/char/" + cls.toString().toLowerCase() + "_p.png")));
            charBgImg.put(cls, new Sprite(new Texture("img/char/" + cls.toString().toLowerCase() + "_bg.png")));
        }
    }

    private static void generateAdvImg() {
        advImg.clear();
        advImgBig.clear();
        advBgImg.clear();
        for(AdvisorClass cls : AdvisorClass.values()) {
            advImg.put(cls, new Sprite(new Texture("img/adv/" + cls.toString().toLowerCase() + ".png")));
            //advImgBig.put(cls, new Sprite(new Texture("img/adv/" + cls.toString().toLowerCase() + "_p.png")));
            advBgImg.put(cls, new Sprite(new Texture("img/adv/" + cls.toString().toLowerCase() + "_bg.png")));
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

    @Override
    public void dispose() {
        BG_BLACK.getTexture().dispose();
        BG_MAIN.getTexture().dispose();
        BG_DEAD.getTexture().dispose();
        BG_WIN.getTexture().dispose();
        BG_MAP.getTexture().dispose();
        BG_CHARSELECT.getTexture().dispose();
        BG_BATTLE.getTexture().dispose();
        FADE.getTexture().dispose();
        TURN_BG.getTexture().dispose();
        MENU_SELECT.getTexture().dispose();
        CONTROL_PANEL.getTexture().dispose();
        BORDER.getTexture().dispose();
        BORDER_B.getTexture().dispose();
        BORDER_S.getTexture().dispose();
        BORDER_SS.getTexture().dispose();
        BACK.getTexture().dispose();
        NEXT.getTexture().dispose();
        DECK.getTexture().dispose();
        DRAW.getTexture().dispose();
        GOLD.getTexture().dispose();
        DISCARD.getTexture().dispose();
        REWARD_CARD.getTexture().dispose();
        ENTITY_POINT.getTexture().dispose();
        PLAYER_POINT.getTexture().dispose();
        for(Sprite s : charImg.values()) {
            s.getTexture().dispose();
        }
        for(Sprite s : charBgImg.values()) {
            s.getTexture().dispose();
        }
        for(Sprite s : advImg.values()) {
            s.getTexture().dispose();
        }
        for(Sprite s : advBgImg.values()) {
            s.getTexture().dispose();
        }
        for(Sprite s : skillImg.values()) {
            s.getTexture().dispose();
        }
        for(Sprite s : skillImgBig.values()) {
            s.getTexture().dispose();
        }

        //지울 것들
        NEKO_ATLAS.dispose();
        BURGER.getTexture().dispose();
        BURGER_BG.getTexture().dispose();
        BURGER_S.getTexture().dispose();
        SKILL_POISON.getTexture().dispose();
        SKILL_SPIKE.getTexture().dispose();
    }
}
