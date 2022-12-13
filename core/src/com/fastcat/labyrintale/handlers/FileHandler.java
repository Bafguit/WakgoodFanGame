package com.fastcat.labyrintale.handlers;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.GifDecoder;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import lombok.Getter;

public class FileHandler implements Disposable {

    // TextureAtlas
    public static final TextureAtlas character = new TextureAtlas("img/char/char.atlas");
    /** Combined all static "JsonValue" variables into hash map to optimize memory. */
    private static final HashMap<JsonType, JsonValue> jsonMap = new HashMap<>();

    public static JsonValue getJsonValue(JsonType jsonType) {
        return jsonMap.get(jsonType);
    }

    // 관리
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
    // 캐릭터
    @Getter
    private static final HashMap<PlayerClass, Sprite> charImg = new HashMap<>();

    @Getter
    private static final HashMap<PlayerClass, Sprite> charImgTurn = new HashMap<>();

    @Getter
    private static final HashMap<PlayerClass, Sprite> charBgImg = new HashMap<>();

    @Getter
    private static final HashMap<PlayerClass, Sprite> charPanelImg = new HashMap<>();

    @Getter
    private static final HashMap<PlayerClass, Sprite> charCampImg = new HashMap<>();

    @Getter
    private static final HashMap<PlayerClass, Sprite> charUpsetImg = new HashMap<>();

    @Getter
    private static final HashMap<AdvisorClass, Sprite> advImg = new HashMap<>();

    @Getter
    private static final HashMap<String, Sprite> enemyImg = new HashMap<>();

    @Getter
    private static final HashMap<String, Sprite> enemyPanelImg = new HashMap<>();
    // 스킬
    @Getter
    private static final HashMap<String, Sprite> skillImg = new HashMap<>();
    // 상태
    @Getter
    private static final HashMap<String, Sprite> statusImg = new HashMap<>();
    // 아이템
    @Getter
    private static final HashMap<String, Sprite> itemImg = new HashMap<>();

    @Getter
    private static final HashMap<String, Sprite> itemImgTrans = new HashMap<>();
    // 이벤트
    @Getter
    private static final HashMap<String, Sprite> eventImg = new HashMap<>();
    // 업적
    @Getter
    private static final HashMap<AchieveHandler.Achievement, Sprite> achvImg = new HashMap<>();
    // 튜토리얼
    @Getter
    private static final HashMap<String, Sprite> tutorialImg = new HashMap<>();
    // 움짤
    @Getter
    private static final HashMap<String, Animation<Sprite>> gif = new HashMap<>();
    // 영상
    @Getter
    private static final HashMap<String, FileHandle> video = new HashMap<>();
    /***
     * Instance of handler.
     * Initialized on getInstance()
     */
    private static FileHandler instance;

    static {
        jsonMap.put(JsonType.ACHV_JSON, generateJson("json/achieves.json"));
        jsonMap.put(JsonType.CHAR_JSON, generateJson("json/chars.json"));
        jsonMap.put(JsonType.CHOICE_JSON, generateJson("json/choices.json"));
        jsonMap.put(JsonType.STATUS_JSON, generateJson("json/status.json"));
        jsonMap.put(JsonType.ENEMY_JSON, generateJson("json/enemies.json"));
        jsonMap.put(JsonType.EVENT_JSON, generateJson("json/events.json"));
        jsonMap.put(JsonType.ITEM_JSON, generateJson("json/items.json"));
        jsonMap.put(JsonType.KEY_JSON, generateJson("json/keywords.json"));
        jsonMap.put(JsonType.CREDIT_JSON, generateJson("json/credit.json"));
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
        generateEnemyImg();
        generateSkillImg();
        generateStatusImg();
        generateItemImg();
        generateEventImg();
        generateAchieve();
        generateTutorialImg();
        generateGif();
        generateVideo();
        setAntiAliased();
    }

    /***
     * Returns instance of handler, if not exist, create new.
     * @return instance of handler
     */
    public static FileHandler getInstance() {
        if (instance == null) return (instance = new FileHandler());
        return instance;
    }

    private static JsonValue generateJson(String url) {
        JsonReader jsonReader = new JsonReader();
        FileHandle fileHandle = Gdx.files.internal(url);
        InputStreamReader is = new InputStreamReader(fileHandle.read(), StandardCharsets.UTF_8);
        return jsonReader.parse(is);
    }

    public static JsonValue generateJson(FileHandle fileHandle) {
        JsonReader jsonReader = new JsonReader();
        InputStreamReader is = new InputStreamReader(fileHandle.read(), StandardCharsets.UTF_8);
        return jsonReader.parse(is);
    }

    private void generateHashMap() {
        maps.add(bg);
        maps.add(ui);
        maps.add(vfx);
        maps.add(charImg);
        maps.add(charImgTurn);
        maps.add(charBgImg);
        maps.add(charPanelImg);
        maps.add(charCampImg);
        maps.add(charUpsetImg);
        maps.add(advImg);
        maps.add(achvImg);
        maps.add(enemyImg);
        maps.add(enemyPanelImg);
        maps.add(skillImg);
        maps.add(statusImg);
        maps.add(itemImg);
        maps.add(itemImgTrans);
        maps.add(eventImg);
        maps.add(tutorialImg);
    }

    private void generateGif() {
        gif.clear();
        gif.put(
                "MAIN_MENU",
                GifDecoder.loadGIFAnimation(
                        Animation.PlayMode.LOOP,
                        Gdx.files.internal("img/gif/main_gif.gif").read()));
        gif.put(
                "FIRE_LIGHT",
                GifDecoder.loadGIFAnimation(
                        Animation.PlayMode.LOOP,
                        Gdx.files.internal("img/gif/fire_light.gif").read()));
        gif.put(
                "FIRE",
                GifDecoder.loadGIFAnimation(
                        Animation.PlayMode.LOOP,
                        Gdx.files.internal("img/gif/fire.gif").read()));
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

    private void generateAchieve() {
        for (AchieveHandler.Achievement a : AchieveHandler.Achievement.values()) {
            achvImg.put(a, new Sprite(new Texture("img/achieve/" + a.toString().toLowerCase() + ".png")));
        }
    }

    private void generateBG() {
        bg.clear();
        bg.put("BG_BLACK", new Sprite(new Texture("img/ui/fade.png")));
        bg.put("BG_GREY", new Sprite(new Texture("img/bg/white.png")));
        bg.put("BG_LOGO", new Sprite(new Texture("img/ui/logo.png")));
        bg.put("BG_MAIN", new Sprite(new Texture("img/bg/main.png")));
        bg.put("BG_DEAD", new Sprite(new Texture("img/bg/dead.png")));
        bg.put("BG_WIN", new Sprite(new Texture("img/bg/win.png")));
        bg.put("BG_MAP", new Sprite(new Texture("img/bg/map.png")));
        bg.put("BG_DIFF", new Sprite(new Texture("img/bg/diff.png")));
        bg.put("BG_WAY_1", new Sprite(new Texture("img/bg/way_forest.png")));
        bg.put("BG_WAY_2", new Sprite(new Texture("img/bg/way_deep.png")));
        bg.put("BG_WAY_3", new Sprite(new Texture("img/bg/way_temple.png")));
        bg.put("BG_WAY_4", new Sprite(new Texture("img/bg/way_lab.png")));
        bg.put("BG_REST_BAG", new Sprite(new Texture("img/bg/fire_bag.png")));
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
        ui.put("CHANGE_H", new Sprite(new Texture("img/ui/change_h.png")));
        ui.put("CHANGE_V", new Sprite(new Texture("img/ui/change_v.png")));
        ui.put("MENU_SELECT", new Sprite(new Texture("img/ui/menuSelect.png")));
        ui.put("CONTROL_PANEL", new Sprite(new Texture("img/ui/cPanel.png")));
        ui.put("BATTLE_PANEL", new Sprite(new Texture("img/ui/cPanel_b.png")));
        ui.put("ENERGY_ORB", new Sprite(new Texture("img/ui/energy.png")));
        ui.put("BUTTON", new Sprite(new Texture("img/ui/button.png")));
        ui.put("BUTTON_S", new Sprite(new Texture("img/ui/button_s.png")));
        ui.put("LEVEL_BACK", new Sprite(new Texture("img/ui/level_back.png")));
        ui.put("LEVEL_LINE", new Sprite(new Texture("img/ui/level_border.png")));
        ui.put("LEVEL_EXP", new Sprite(new Texture("img/ui/level_exp.png")));
        ui.put("CLOSE", new Sprite(new Texture("img/ui/close.png")));
        ui.put("CLOSE_SET", new Sprite(new Texture("img/ui/closeSet.png")));
        ui.put("BORDER", new Sprite(new Texture("img/ui/border.png")));
        ui.put("BORDER_M", new Sprite(new Texture("img/ui/border_m.png")));
        ui.put("BORDER_P", new Sprite(new Texture("img/ui/border_player.png")));
        ui.put("BORDER_PL", new Sprite(new Texture("img/ui/border_p_p.png")));
        ui.put("BORDER_B", new Sprite(new Texture("img/ui/border_p.png")));
        ui.put("BORDER_S", new Sprite(new Texture("img/ui/border_s.png")));
        ui.put("BORDER_SS", new Sprite(new Texture("img/ui/border_ss.png")));
        ui.put("BORDER_V", new Sprite(new Texture("img/ui/border_v.png")));
        ui.put("BORDER_V2", new Sprite(new Texture("img/ui/border_v2.png")));
        ui.put("BORDER_R", new Sprite(new Texture("img/ui/border_r.png")));
        ui.put("BORDER_ADV", new Sprite(new Texture("img/ui/border_adv.png")));
        ui.put("BORDER_T", new Sprite(new Texture("img/ui/border_turn.png")));
        ui.put("BORDER_T2", new Sprite(new Texture("img/ui/border_turn2.png")));
        ui.put("BORDER_BACK", new Sprite(new Texture("img/ui/border_back.png")));
        ui.put("REST_HEAL", new Sprite(new Texture("img/ui/rest_heal.png")));
        ui.put("REST_REVIVE", new Sprite(new Texture("img/ui/rest_revive.png")));
        ui.put("REST_UPGRADE", new Sprite(new Texture("img/ui/rest_upgrade.png")));
        ui.put("CAMP", new Sprite(new Texture("img/ui/camp.png")));
        ui.put("BACK", new Sprite(new Texture("img/ui/back.png")));
        ui.put("NEXT", new Sprite(new Texture("img/ui/next.png")));
        ui.put("GOLD", new Sprite(new Texture("img/ui/gold.png")));
        ui.put("ROLL", new Sprite(new Texture("img/ui/roll.png")));
        ui.put("ENTITY_POINT_B", new Sprite(new Texture("img/ui/entityPoint_boss.png")));
        ui.put("PLAYER_POINT_B", new Sprite(new Texture("img/ui/playerPoint_boss.png")));
        ui.put("ENTITY_POINT", new Sprite(new Texture("img/ui/entityPoint.png")));
        ui.put("PLAYER_POINT", new Sprite(new Texture("img/ui/playerPoint.png")));
        ui.put("POINT_TURN", new Sprite(new Texture("img/ui/point_turn.png")));
        ui.put("POINT_ALLEY", new Sprite(new Texture("img/ui/point_alley.png")));
        ui.put("POINT_ATTACK", new Sprite(new Texture("img/ui/point_attack.png")));
        ui.put("POINT_TURN_B", new Sprite(new Texture("img/ui/point_turn_b.png")));
        ui.put("POINT_ALLEY_B", new Sprite(new Texture("img/ui/point_alley_b.png")));
        ui.put("POINT_ATTACK_B", new Sprite(new Texture("img/ui/point_attack_b.png")));
        ui.put("WAY_SELECT", new Sprite(new Texture("img/ui/wayBG.png")));
        ui.put("EVENT_PANEL", new Sprite(new Texture("img/ui/event_paper.png")));
        ui.put("EVENT_CHOICE", new Sprite(new Texture("img/ui/event_select.png")));
        ui.put("CHECK_OFF", new Sprite(new Texture("img/ui/check_0.png")));
        ui.put("CHECK_ON", new Sprite(new Texture("img/ui/check_1.png")));
        ui.put("SHIELD", new Sprite(new Texture("img/ui/shield.png")));
        ui.put("GOLD_PANEL", new Sprite(new Texture("img/ui/gold_paper.png")));
        ui.put("SETTING_B", new Sprite(new Texture("img/ui/setting.png")));
        ui.put("LOGO", new Sprite(new Texture("img/ui/logo.png")));
        ui.put("TITLE", new Sprite(new Texture("img/ui/title.png")));
        ui.put("CURSOR", new Sprite(new Texture("img/ui/cursor.png")));
        ui.put("TURN_ARROW", new Sprite(new Texture("img/ui/turn_arrow.png")));
        ui.put("SLIDE_A", new Sprite(new Texture("img/ui/slide_a.png")));
        ui.put("SLIDE_B", new Sprite(new Texture("img/ui/slide_b.png")));
        ui.put("SLIDE_L", new Sprite(new Texture("img/ui/slide_l.png")));
        ui.put("SLIDE_SL", new Sprite(new Texture("img/ui/slide_sl.png")));
        ui.put("SLIDE_SR", new Sprite(new Texture("img/ui/slide_sr.png")));
        ui.put("LEFT", new Sprite(new Texture("img/ui/left.png")));
        ui.put("RIGHT", new Sprite(new Texture("img/ui/right.png")));
        ui.put("HEART", new Sprite(new Texture("img/ui/heart.png")));
        ui.put("REST", new Sprite(new Texture("img/ui/restNode.png")));
        ui.put("BATTLE", new Sprite(new Texture("img/ui/battle_n.png")));
        ui.put("ELITE", new Sprite(new Texture("img/ui/battle_e.png")));
        ui.put("BOSS", new Sprite(new Texture("img/ui/battle_b.png")));
        ui.put("LOOK", new Sprite(new Texture("img/ui/mysteryNode.png")));
        ui.put("ENTRY", new Sprite(new Texture("img/ui/entryNode.png")));
        ui.put("SHOP", new Sprite(new Texture("img/ui/shopNode.png")));
        ui.put("MAP", new Sprite(new Texture("img/ui/map.png")));
        ui.put("EVENT", new Sprite(new Texture("img/ui/event_img.png")));
        ui.put("SUB_TOP", new Sprite(new Texture("img/ui/sub_t.png")));
        ui.put("SUB_MID", new Sprite(new Texture("img/ui/sub_m.png")));
        ui.put("SUB_BOT", new Sprite(new Texture("img/ui/sub_b.png")));
        ui.put("STAT_ATTACK", new Sprite(new Texture("img/stat/attack.png")));
        ui.put("STAT_SPELL", new Sprite(new Texture("img/stat/spell.png")));
        ui.put("STAT_CRITICAL", new Sprite(new Texture("img/stat/critical.png")));
        ui.put("STAT_MULTIPLY", new Sprite(new Texture("img/stat/multiply.png")));
        ui.put("STAT_SPEED", new Sprite(new Texture("img/stat/speed.png")));
        ui.put("STAT_MOVERES", new Sprite(new Texture("img/stat/moveRes.png")));
        ui.put("STAT_DEBURES", new Sprite(new Texture("img/stat/debuRes.png")));
        ui.put("STAT_NEUTRES", new Sprite(new Texture("img/stat/neutRes.png")));
        ui.put("UNKNOWN", new Sprite(new Texture("img/ui/unknown.png")));
        ui.put("STAT_PLUS", new Sprite(new Texture("img/ui/statPlus.png")));
        ui.put("DIFF_NORMAL", new Sprite(new Texture("img/ui/diff_normal.png")));
        ui.put("DIFF_HARD", new Sprite(new Texture("img/ui/diff_hard.png")));
        ui.put("DIFF_COFFIN", new Sprite(new Texture("img/ui/diff_coffin.png")));
        ui.put("DIFF_LOCKED", new Sprite(new Texture("img/ui/diff_locked.png")));
        ui.put("LIB_RUNS", new Sprite(new Texture("img/ui/lib_runs.png")));
        ui.put("LIB_ACHVS", new Sprite(new Texture("img/ui/lib_achvs.png")));
        ui.put("LIB_DICT", new Sprite(new Texture("img/ui/lib_dict.png")));
        ui.put("ARROW_RIGHT", new Sprite(new Texture("img/ui/arrow_right.png")));
        ui.put("HEALTH_BAR", new Sprite(new Texture("img/ui/health_bar.png")));
        ui.put("HEALTH_BACK", new Sprite(new Texture("img/ui/hb_block.png")));
        ui.put("SETTING", new Sprite(new Texture("img/ui/setting_paper.png")));
        ui.put("ACHIEVE", new Sprite(new Texture("img/ui/achv_paper.png")));
        ui.put("REWARD", new Sprite(new Texture("img/ui/reward.png")));
        ui.put("DICT", new Sprite(new Texture("img/ui/dict_paper.png")));
        ui.put("TEXT_DEBU", new Sprite(new Texture("img/ui/debuRes.png")));
        ui.put("TEXT_NEUT", new Sprite(new Texture("img/ui/neutRes.png")));
        ui.put("TEXT_MOVE", new Sprite(new Texture("img/ui/moveRes.png")));
        ui.put("TEXT_CRIT", new Sprite(new Texture("img/ui/critical.png")));
        ui.put("TEXT_NEUTRAL", new Sprite(new Texture("img/ui/neutral.png")));
        ui.put("CREDIT", new Sprite(new Texture("img/credit.png")));
    }

    private void generateCharImg() {
        charImg.clear();
        charImgTurn.clear();
        charBgImg.clear();
        charPanelImg.clear();
        charCampImg.clear();
        charUpsetImg.clear();
        skeleton.clear();
        atlas.clear();
        for (PlayerClass cls : PlayerClass.values()) {
            String s = cls.toString().toLowerCase();
            charImg.put(cls, character.createSprite(s));
            charImgTurn.put(cls, character.createSprite(s + "_p"));
            charBgImg.put(cls, character.createSprite(s + "_bg"));
            charCampImg.put(cls, character.createSprite(s + "_camp"));
            charUpsetImg.put(cls, character.createSprite(s + "_camp_upset"));
            charPanelImg.put(cls, character.createSprite(s + "_cPanel"));
            skeleton.put(s, Gdx.files.internal("spine/" + s + "/skeleton.json"));
            atlas.put(s, new TextureAtlas("spine/" + s + "/skeleton.atlas"));
        }
    }

    private void generateTutorialImg() {
        tutorialImg.clear();
        String s;
        tutorialImg.put(s = "CHARSELECT_1", new Sprite(new Texture("img/tutorial/" + s.toLowerCase() + ".png")));
        tutorialImg.put(s = "CHARSELECT_2", new Sprite(new Texture("img/tutorial/" + s.toLowerCase() + ".png")));
        tutorialImg.put(s = "CHARSELECT_3", new Sprite(new Texture("img/tutorial/" + s.toLowerCase() + ".png")));
        tutorialImg.put(s = "BATTLE_1", new Sprite(new Texture("img/tutorial/" + s.toLowerCase() + ".png")));
        tutorialImg.put(s = "BATTLE_2", new Sprite(new Texture("img/tutorial/" + s.toLowerCase() + ".png")));
        tutorialImg.put(s = "BATTLE_3", new Sprite(new Texture("img/tutorial/" + s.toLowerCase() + ".png")));
        tutorialImg.put(s = "WAY_1", new Sprite(new Texture("img/tutorial/" + s.toLowerCase() + ".png")));
        tutorialImg.put(s = "WAY_2", new Sprite(new Texture("img/tutorial/" + s.toLowerCase() + ".png")));
        tutorialImg.put(s = "REWARD_1", new Sprite(new Texture("img/tutorial/" + s.toLowerCase() + ".png")));
        tutorialImg.put(s = "REWARD_2", new Sprite(new Texture("img/tutorial/" + s.toLowerCase() + ".png")));
    }

    private void generateAdvImg() {
        advImg.clear();
        for (AdvisorClass cls : AdvisorClass.values()) {
            String s = cls.toString().toLowerCase();
            advImg.put(cls, new Sprite(new Texture("img/advisor/" + s + ".png")));
        }
    }

    private void generateEnemyImg() {
        enemyImg.clear();
        enemyPanelImg.clear();
        for (JsonValue js : jsonMap.get(JsonType.ENEMY_JSON)) {
            enemyImg.put(js.name, new Sprite(new Texture("spine/enemy/" + js.name + "/img.png")));
            enemyPanelImg.put(js.name, new Sprite(new Texture("spine/enemy/" + js.name + "/img_p.png")));
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
        for (AbstractSkill.IntentType it : AbstractSkill.IntentType.values()) {
            skillImg.put(
                    it.toString(),
                    new Sprite(new Texture("img/skill/intent/" + it.toString().toLowerCase() + ".png")));
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
                itemImgTrans.put(js.name, new Sprite(new Texture("img/item/" + js.name + "_t.png")));
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
        for(Animation<Sprite> arr : gif.values()) {
        for(Sprite s : arr.getKeyFrames()) {
        	s.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        }
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
        ACHV_JSON,
        CHAR_JSON,
        CHOICE_JSON,
        STATUS_JSON,
        ENEMY_JSON,
        EVENT_JSON,
        CREDIT_JSON,
        ITEM_JSON,
        KEY_JSON,
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
