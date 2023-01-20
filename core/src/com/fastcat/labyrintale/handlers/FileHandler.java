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
import com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.resourcetype.MultipleResourceRequest;
import com.fastcat.labyrintale.utils.Gif;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import lombok.Getter;

public class FileHandler implements Disposable {

    private static final HashMap<JsonType, JsonValue> jsonMap = new HashMap<>();
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
    private static final HashMap<PlayerClass, Sprite> charSelectImg = new HashMap<>();

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
    // 만화
    @Getter
    private static final HashMap<String, Sprite> cartoonImg = new HashMap<>();
    // 움짤
    @Getter
    private static final HashMap<String, Animation<Sprite>> gif = new HashMap<>();
    // 영상
    @Getter
    private static final HashMap<String, FileHandle> video = new HashMap<>();
    // TextureAtlas
    public static TextureAtlas character;
    public static TextureAtlas advisor;
    public static TextureAtlas skill;
    public static TextureAtlas item;
    /***
     * Instance of handler.
     * Initialized on getInstance()
     */

    private static FileHandler instance;

    private FileHandler() {}

    public static JsonValue getJsonValue(JsonType jsonType) {
        return jsonMap.get(jsonType);
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

    private void loadJson() {
        jsonMap.put(JsonType.ACHV_JSON, generateJson("json/achieves.json"));
        jsonMap.put(JsonType.CHAR_JSON, generateJson("json/chars.json"));
        jsonMap.put(JsonType.CHOICE_JSON, generateJson("json/choices.json"));
        jsonMap.put(JsonType.STATUS_JSON, generateJson("json/status.json"));
        jsonMap.put(JsonType.ENEMY_JSON, generateJson("json/enemies.json"));
        jsonMap.put(JsonType.EVENT_JSON, generateJson("json/events.json"));
        jsonMap.put(JsonType.ITEM_JSON, generateJson("json/items.json"));
        jsonMap.put(JsonType.KEY_JSON, generateJson("json/keywords.json"));
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
        StringHandler.generate();
    }

    /***
     * Load files which do not require being called on main thread
     */
    public void loadFiles() {
        loadJson();
        generateHashMap();
        generateVideo();
    }

    /***
     * Load resources which must be loaded on main thread or asset manager
     */
    public void loadResources(ResourceHandler resourceHandler) {
        generateUI(resourceHandler);
        generateBG(resourceHandler);
        generateVFX(resourceHandler);
        generateCharImg(resourceHandler);
        generateSkeleton(resourceHandler);
        generateAdvImg(resourceHandler);
        generateEnemyImg(resourceHandler);
        generateSkillImg(resourceHandler);
        generateStatusImg(resourceHandler);
        generateItemImg(resourceHandler);
        generateEventImg(resourceHandler);
        generateAchieve(resourceHandler);
        generateTutorialImg(resourceHandler);
        generateCartoonImg(resourceHandler);
        if(InputHandler.isDesktop) generateGif(resourceHandler);
    }

    private void generateHashMap() {
        maps.add(bg);
        maps.add(ui);
        maps.add(vfx);
        maps.add(charImg);
        maps.add(charImgTurn);
        maps.add(charBgImg);
        maps.add(charSelectImg);
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
        maps.add(cartoonImg);
    }

    private void generateGif(ResourceHandler resourceHandler) {
        gif.clear();

        HashMap<String, String> resourceNames = new HashMap<>();

        resourceNames.put("MAIN_MENU", "img/gif/main_gif.gif");
        resourceNames.put("BATTLE", "img/gif/battle.gif");
        resourceNames.put("FIRE", "img/gif/fire.gif");

        resourceHandler.requestResource(
                new MultipleResourceRequest<>(resourceNames, Gif.class, (resource, args) -> {
                    Gif gifData = (Gif) resource;
                    String name = args[0].toString();

                    gif.put(name, gifData.getGif());
                }));
    }

    private void generateVideo() {
        video.clear();
        video.put("LOGO", Gdx.files.internal("video/logo.webm"));
    }

    private void generateSkeleton(ResourceHandler resourceHandler) {
        for (JsonValue js : jsonMap.get(JsonType.ENEMY_JSON)) {
            skeleton.put(js.name, Gdx.files.internal("spine/enemy/" + js.name + "/skeleton.json"));

            resourceHandler.requestResource(new ResourceHandler.ResourceRequest<>(
                    "spine/enemy/" + js.name + "/skeleton.atlas",
                    TextureAtlas.class,
                    (resource, args) -> {
                        TextureAtlas textureAtlas = (TextureAtlas) resource;
                        String name = args[0].toString();
                        atlas.put(name, textureAtlas);
                    },
                    js.name));
        }
    }

    private void generateAchieve(ResourceHandler resourceHandler) {

        for (AchieveHandler.Achievement a : AchieveHandler.Achievement.values()) {
            resourceHandler.requestResource(new ResourceHandler.ResourceRequest<>(
                    "img/achieve/" + a.toString().toLowerCase() + ".png",
                    Texture.class,
                    (resource, args) -> {
                        Texture texture = (Texture) resource;
                        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        AchieveHandler.Achievement achievement = (AchieveHandler.Achievement) args[0];
                        achvImg.put(achievement, new Sprite(texture));
                    },
                    a));
        }
    }

    private void generateBG(ResourceHandler resourceHandler) {
        bg.clear();

        HashMap<String, String> resources = new HashMap<>();
        resources.put("BG_OVER", "img/bg/bg_over.png");
        resources.put("BG_GREY", "img/bg/white.png");
        resources.put("BG_BLACK", "img/ui/fade.png");
        resources.put("BG_LOGO", "img/ui/logo.png");
        resources.put("BG_DEAD", "img/bg/dead.png");
        resources.put("BG_WIN", "img/bg/win.png");
        resources.put("BG_MAP", "img/bg/map.png");
        resources.put("BG_DIFF", "img/bg/diff.png");
        resources.put("BG_WAY_1", "img/bg/way_forest.png");
        resources.put("BG_WAY_2", "img/bg/way_deep.png");
        resources.put("BG_WAY_3", "img/bg/way_temple.png");
        resources.put("BG_WAY_4", "img/bg/way_lab.png");
        resources.put("BG_REST_BAG", "img/bg/fire_bag.png");
        resources.put("BG_REST_LIGHT", "img/bg/fire_light.png");
        resources.put("BG_SHOP", "img/bg/shop.png");
        resources.put("BG_LIB", "img/bg/library.png");
        resources.put("BG_RUN", "img/bg/run.png");
        resources.put("BG_DICT", "img/bg/dict.png");
        resources.put("BG_ACHV", "img/bg/achv.png");
        resources.put("BG_CHARSELECT", "img/bg/charselect.png");
        resources.put("MAIN_MENU", "img/bg/main.png");
        resources.put("FIRE", "img/bg/fire.png");

        resourceHandler.requestResource(new MultipleResourceRequest<>(resources, Texture.class, (resource, args) -> {
            Texture texture = (Texture) resource;
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            String resourceName = args[0].toString();

            bg.put(resourceName, new Sprite(texture));
        }));
    }

    private void generateVFX(ResourceHandler resourceHandler) {
        vfx.clear();

        HashMap<String, String> resourceNames = new HashMap<>();

        resourceNames.put("HIT_LIGHT", "img/vfx/hit_s.png");
        resourceNames.put("HIT_HEAVY", "img/vfx/hit_b.png");
        resourceNames.put("INFECTION", "img/vfx/infection.png");
        resourceNames.put("LIGHTNING", "img/vfx/lightning.png");
        resourceNames.put("BURN", "img/vfx/burn.png");
        resourceNames.put("SHIELD", "img/vfx/shield.png");
        resourceNames.put("SLASH_H", "img/vfx/slash_h.png");
        resourceNames.put("SLASH_V", "img/vfx/slash_v.png");
        resourceNames.put("SLASH_D", "img/vfx/slash_d.png");
        resourceNames.put("SMASH", "img/vfx/smash.png");

        resourceHandler.requestResource(
                new MultipleResourceRequest<>(resourceNames, Texture.class, (resource, args) -> {
                    Texture texture = (Texture) resource;
                    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    String resourceName = args[0].toString();

                    vfx.put(resourceName, new Sprite(texture));
                }));
    }

    private void generateUI(ResourceHandler resourceHandler) {
        ui.clear();

        HashMap<String, String> resourceNames = new HashMap<>();
        resourceNames.put("FADE", "img/ui/fade.png");
        resourceNames.put("TURN_BG", "img/ui/tc.png");
        resourceNames.put("CHANGE_H", "img/ui/change_h.png");
        resourceNames.put("CHANGE_V", "img/ui/change_v.png");
        resourceNames.put("MENU_SELECT", "img/ui/menuSelect.png");
        resourceNames.put("CONTROL_PANEL", "img/ui/cPanel.png");
        resourceNames.put("ENERGY_ORB", "img/ui/energy.png");
        resourceNames.put("ENERGY_PANEL", "img/ui/energy_p.png");
        resourceNames.put("BUTTON", "img/ui/button.png");
        resourceNames.put("BUTTON_S", "img/ui/button_s.png");
        resourceNames.put("LEVEL_BACK", "img/ui/level_back.png");
        resourceNames.put("LEVEL_LINE", "img/ui/level_border.png");
        resourceNames.put("LEVEL_EXP", "img/ui/level_exp.png");
        resourceNames.put("CLOSE", "img/ui/close.png");
        resourceNames.put("CLOSE_SET", "img/ui/closeSet.png");
        resourceNames.put("BORDER", "img/ui/border.png");
        resourceNames.put("BORDER_M", "img/ui/border_m.png");
        resourceNames.put("BORDER_P", "img/ui/border_player.png");
        resourceNames.put("BORDER_PL", "img/ui/border_p_p.png");
        resourceNames.put("BORDER_B", "img/ui/border_p.png");
        resourceNames.put("BORDER_CHAR", "img/ui/border_char.png");
        resourceNames.put("BORDER_S", "img/ui/border_s.png");
        resourceNames.put("BORDER_SS", "img/ui/border_ss.png");
        resourceNames.put("BORDER_V", "img/ui/border_v.png");
        resourceNames.put("BORDER_V2", "img/ui/border_v2.png");
        resourceNames.put("BORDER_R", "img/ui/border_r.png");
        resourceNames.put("BORDER_ADV", "img/ui/border_adv.png");
        resourceNames.put("BORDER_T", "img/ui/border_turn.png");
        resourceNames.put("BORDER_T2", "img/ui/border_turn2.png");
        resourceNames.put("BORDER_BACK", "img/ui/border_back.png");
        resourceNames.put("SHADOW_ITEM", "img/ui/shadow_item.png");
        resourceNames.put("SHADOW_ROLL", "img/ui/shadow_roll.png");
        resourceNames.put("GOLD_SHOP", "img/ui/gold_shop.png");
        resourceNames.put("CHAR_TUTO", "img/ui/char_tuto.png");
        resourceNames.put("CHAR_START", "img/ui/char_start.png");
        resourceNames.put("CHAR_GROUND", "img/ui/char_ground.png");
        resourceNames.put("CHAR_LINE", "img/ui/char_line.png");
        resourceNames.put("CHAR_LINE_B", "img/ui/char_line_b.png");
        resourceNames.put("JOB_DEF", "img/ui/job_def.png");
        resourceNames.put("JOB_ATK", "img/ui/job_atk.png");
        resourceNames.put("JOB_SUP", "img/ui/job_sup.png");
        resourceNames.put("REST_HEAL", "img/ui/rest_heal.png");
        resourceNames.put("REST_REVIVE", "img/ui/rest_revive.png");
        resourceNames.put("REST_UPGRADE", "img/ui/rest_upgrade.png");
        resourceNames.put("CAMP", "img/ui/camp.png");
        resourceNames.put("BACK", "img/ui/back.png");
        resourceNames.put("NEXT", "img/ui/next.png");
        resourceNames.put("GOLD", "img/ui/gold.png");
        resourceNames.put("ROLL", "img/ui/roll.png");
        resourceNames.put("SETTING_CLOSE", "img/ui/closeSetting.png");
        resourceNames.put("SETTING_MAIN", "img/ui/mainMenu.png");
        resourceNames.put("SETTING_GIVEUP", "img/ui/giveUp.png");
        resourceNames.put("SETTING_EXIT", "img/ui/exit.png");
        resourceNames.put("ENTITY_POINT_B", "img/ui/entityPoint_boss.png");
        resourceNames.put("PLAYER_POINT_B", "img/ui/playerPoint_boss.png");
        resourceNames.put("ENTITY_POINT", "img/ui/entityPoint.png");
        resourceNames.put("PLAYER_POINT", "img/ui/playerPoint.png");
        resourceNames.put("POINT_TURN", "img/ui/point_turn.png");
        resourceNames.put("POINT_ALLEY", "img/ui/point_alley.png");
        resourceNames.put("POINT_TURN_B", "img/ui/point_turn_b.png");
        resourceNames.put("POINT_ALLEY_B", "img/ui/point_alley_b.png");
        resourceNames.put("WAY_SELECT", "img/ui/wayBG.png");
        resourceNames.put("WAY_1", "img/ui/way_1.png");
        resourceNames.put("WAY_2", "img/ui/way_2.png");
        resourceNames.put("WAY_3", "img/ui/way_3.png");
        resourceNames.put("WAY_4", "img/ui/way_4.png");
        resourceNames.put("EVENT_PANEL", "img/ui/event_paper.png");
        resourceNames.put("EVENT_CHOICE", "img/ui/event_select.png");
        resourceNames.put("CHECK_OFF", "img/ui/check_0.png");
        resourceNames.put("CHECK_ON", "img/ui/check_1.png");
        resourceNames.put("MORE_RESULT", "img/ui/moreResult.png");
        resourceNames.put("RESULT_WIN", "img/ui/result_win.png");
        resourceNames.put("RESULT_DEAD", "img/ui/result_dead.png");
        resourceNames.put("SHIELD", "img/ui/shield.png");
        resourceNames.put("GOLD_PANEL", "img/ui/gold_paper.png");
        resourceNames.put("SETTING_B", "img/ui/setting.png");
        resourceNames.put("TUTORIAL_B", "img/ui/tutorial.png");
        resourceNames.put("LOGO", "img/ui/logo.png");
        resourceNames.put("TITLE", "img/ui/title.png");
        resourceNames.put("TURN_ARROW", "img/ui/turn_arrow.png");
        resourceNames.put("SLIDE_A", "img/ui/slide_a.png");
        resourceNames.put("SLIDE_B", "img/ui/slide_b.png");
        resourceNames.put("SLIDE", "img/ui/slide.png");
        resourceNames.put("LEFT", "img/ui/left.png");
        resourceNames.put("RIGHT", "img/ui/right.png");
        resourceNames.put("HEART", "img/ui/heart.png");
        resourceNames.put("REST", "img/ui/restNode.png");
        resourceNames.put("BATTLE", "img/ui/battle_n.png");
        resourceNames.put("ELITE", "img/ui/battle_e.png");
        resourceNames.put("BOSS", "img/ui/battle_b.png");
        resourceNames.put("BOSS_1", "img/ui/battle_b_1.png");
        resourceNames.put("BOSS_2", "img/ui/battle_b_2.png");
        resourceNames.put("BOSS_3", "img/ui/battle_b_3.png");
        resourceNames.put("BOSS_4", "img/ui/battle_b_4.png");
        resourceNames.put("LOOK", "img/ui/mysteryNode.png");
        resourceNames.put("ENTRY", "img/ui/entryNode.png");
        resourceNames.put("SHOP", "img/ui/shopNode.png");
        resourceNames.put("MAP", "img/ui/map.png");
        resourceNames.put("EVENT", "img/ui/event_img.png");
        resourceNames.put("SUB_TOP", "img/ui/sub_t.png");
        resourceNames.put("SUB_MID", "img/ui/sub_m.png");
        resourceNames.put("SUB_BOT", "img/ui/sub_b.png");
        resourceNames.put("STAT_ATTACK", "img/stat/attack.png");
        resourceNames.put("STAT_SPELL", "img/stat/spell.png");
        resourceNames.put("STAT_CRITICAL", "img/stat/critical.png");
        resourceNames.put("STAT_MULTIPLY", "img/stat/multiply.png");
        resourceNames.put("STAT_SPEED", "img/stat/speed.png");
        resourceNames.put("STAT_MOVERES", "img/stat/moveRes.png");
        resourceNames.put("STAT_DEBURES", "img/stat/debuRes.png");
        resourceNames.put("STAT_NEUTRES", "img/stat/neutRes.png");
        resourceNames.put("UNKNOWN", "img/ui/unknown.png");
        resourceNames.put("STAT_PLUS", "img/ui/statPlus.png");
        resourceNames.put("DIFF_NORMAL", "img/ui/diff_normal.png");
        resourceNames.put("DIFF_HARD", "img/ui/diff_hard.png");
        resourceNames.put("DIFF_COFFIN", "img/ui/diff_coffin.png");
        resourceNames.put("DIFF_LOCKED", "img/ui/diff_locked.png");
        resourceNames.put("LIB_RUNS", "img/ui/lib_runs.png");
        resourceNames.put("LIB_ACHVS", "img/ui/lib_achvs.png");
        resourceNames.put("LIB_DICT", "img/ui/lib_dict.png");
        resourceNames.put("ARROW_RIGHT", "img/ui/arrow_right.png");
        resourceNames.put("HEALTH_BAR", "img/ui/health_bar.png");
        resourceNames.put("HEALTH_BACK", "img/ui/hb_block.png");
        resourceNames.put("SETTING", "img/ui/setting_paper.png");
        resourceNames.put("ACHIEVE", "img/ui/achv_paper.png");
        resourceNames.put("REWARD", "img/ui/reward.png");
        resourceNames.put("DICT", "img/ui/dict_paper.png");
        resourceNames.put("TEXT_DEBU", "img/ui/debuRes.png");
        resourceNames.put("TEXT_NEUT", "img/ui/neutRes.png");
        resourceNames.put("TEXT_MOVE", "img/ui/moveRes.png");
        resourceNames.put("TEXT_CRIT", "img/ui/critical.png");
        resourceNames.put("TEXT_NEUTRAL", "img/ui/neutral.png");
        resourceNames.put("GROUND", "img/ui/entity_ground.png");
        resourceNames.put("CREDIT", "img/credit.png");

        resourceHandler.requestResource(
                new MultipleResourceRequest<>(resourceNames, Texture.class, (resource, args) -> {
                    Texture texture = (Texture) resource;
                    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    String resourceName = args[0].toString();
                    ui.put(resourceName, new Sprite(texture));
                }));
    }

    private void generateCharImg(ResourceHandler resourceHandler) {
        charImg.clear();
        charImgTurn.clear();
        charBgImg.clear();
        charSelectImg.clear();
        charPanelImg.clear();
        charCampImg.clear();
        charUpsetImg.clear();
        skeleton.clear();
        atlas.clear();
        resourceHandler.requestResource(
                new ResourceHandler.ResourceRequest<>("img/char/char.atlas", TextureAtlas.class, (resource, args) -> {
                    character = (TextureAtlas) resource;

                    for (PlayerClass cls : PlayerClass.values()) {
                        String s = cls.toString().toLowerCase();

                        Sprite sp = character.createSprite(s);
                        sp.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        charImg.put(cls, sp);

                        Sprite sp2 = character.createSprite(s + "_p");
                        sp2.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        charImgTurn.put(cls, sp2);

                        Sprite sp3 = character.createSprite(s + "_bg");
                        sp3.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        charBgImg.put(cls, sp3);

                        Sprite spp = character.createSprite(s + "_char");
                        spp.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        charSelectImg.put(cls, spp);

                        Sprite sp4 = character.createSprite(s + "_camp");
                        sp4.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        charCampImg.put(cls, sp4);

                        Sprite sp5 = character.createSprite(s + "_camp_upset");
                        sp5.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        charUpsetImg.put(cls, sp5);

                        Sprite sp6 = character.createSprite(s + "_cPanel");
                        sp6.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        charPanelImg.put(cls, sp6);

                        skeleton.put(s, Gdx.files.internal("spine/" + s + "/basic/skeleton.json"));
                        atlas.put(s, new TextureAtlas("spine/" + s + "/basic/skeleton.atlas"));
                    }
                }));
    }

    private void generateTutorialImg(ResourceHandler resourceHandler) {
        tutorialImg.clear();
        String s;

        HashMap<String, String> resourceNames = new HashMap<>();

        resourceNames.put(s = "CHARSELECT_1", "img/tutorial/" + s.toLowerCase() + ".png");
        resourceNames.put(s = "CHARSELECT_2", "img/tutorial/" + s.toLowerCase() + ".png");
        resourceNames.put(s = "CHARSELECT_3", "img/tutorial/" + s.toLowerCase() + ".png");
        resourceNames.put(s = "BATTLE_1", "img/tutorial/" + s.toLowerCase() + ".png");
        resourceNames.put(s = "BATTLE_2", "img/tutorial/" + s.toLowerCase() + ".png");
        resourceNames.put(s = "BATTLE_3", "img/tutorial/" + s.toLowerCase() + ".png");
        resourceNames.put(s = "WAY_1", "img/tutorial/" + s.toLowerCase() + ".png");
        resourceNames.put(s = "WAY_2", "img/tutorial/" + s.toLowerCase() + ".png");
        resourceNames.put(s = "WAY_3", "img/tutorial/" + s.toLowerCase() + ".png");
        resourceNames.put(s = "REWARD_1", "img/tutorial/" + s.toLowerCase() + ".png");
        resourceNames.put(s = "REWARD_2", "img/tutorial/" + s.toLowerCase() + ".png");

        resourceHandler.requestResource(
                new MultipleResourceRequest<>(resourceNames, Texture.class, (resource, args) -> {
                    Texture texture = (Texture) resource;
                    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    String resourceName = args[0].toString();

                    tutorialImg.put(resourceName, new Sprite(texture));
                }));
    }

    private void generateCartoonImg(ResourceHandler resourceHandler) {
        cartoonImg.clear();
        String s;

        HashMap<String, String> resourceNames = new HashMap<>();

        for(int i = 1; i <= 3; i++) {
            resourceNames.put("CLEAR_ACT1_" + i, "img/cartoon/clear_act1/" + i + ".png");
        }

        for(int i = 1; i <= 3; i++) {
            resourceNames.put("CLEAR_ACT2_" + i, "img/cartoon/clear_act2/" + i + ".png");
        }

        for(int i = 1; i <= 3; i++) {
            resourceNames.put("CLEAR_ACT3_" + i, "img/cartoon/clear_act3/" + i + ".png");
        }

        resourceHandler.requestResource(
                new MultipleResourceRequest<>(resourceNames, Texture.class, (resource, args) -> {
                    Texture texture = (Texture) resource;
                    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    String resourceName = args[0].toString();

                    cartoonImg.put(resourceName, new Sprite(texture));
                }));
    }

    private void generateAdvImg(ResourceHandler resourceHandler) {
        advImg.clear();
        resourceHandler.requestResource(
                new ResourceHandler.ResourceRequest<>("img/advisor/advisor.atlas", TextureAtlas.class, (resource, args) -> {
                    advisor = (TextureAtlas) resource;

                    for (AdvisorClass cls : AdvisorClass.values()) {
                        String s = cls.toString().toLowerCase();

                        Sprite sp = advisor.createSprite(s);
                        sp.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        advImg.put(cls, sp);
                    }
                }));
    }

    private void generateEnemyImg(ResourceHandler resourceHandler) {
        enemyImg.clear();
        enemyPanelImg.clear();
        for (JsonValue js : jsonMap.get(JsonType.ENEMY_JSON)) {

            ResourceHandler.ResourceRequest<Texture> request = new ResourceHandler.ResourceRequest<>(
                    "spine/enemy/" + js.name + "/img.png",
                    Texture.class,
                    (resource, args) -> {
                        Texture texture = (Texture) resource;
                        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        String name = args[0].toString();
                        enemyImg.put(name, new Sprite(texture));
                    },
                    js.name);

            resourceHandler.requestResource(request);

            request = new ResourceHandler.ResourceRequest<>(
                    "spine/enemy/" + js.name + "/img_p.png",
                    Texture.class,
                    (resource, args) -> {
                        Texture texture = (Texture) resource;
                        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        String name = args[0].toString();
                        enemyPanelImg.put(name, new Sprite(texture));
                    },
                    js.name);

            resourceHandler.requestResource(request);
        }
    }

    private void generateSkillImg(ResourceHandler resourceHandler) {
        skillImg.clear();

        HashMap<String, String> resourceNames = new HashMap<>();
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_BASIC)) {
            resourceNames.put(js.name, "img/skill/basic/" + js.name + ".png");
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_WAK)) {
            resourceNames.put(js.name, "img/skill/wak/" + js.name + ".png");
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_MANAGER)) {
            resourceNames.put(js.name, "img/skill/manager/" + js.name + ".png");
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_INE)) {
            resourceNames.put(js.name, "img/skill/ine/" + js.name + ".png");
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_VIICHAN)) {
            resourceNames.put(js.name, "img/skill/viichan/" + js.name + ".png");
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_LILPA)) {
            resourceNames.put(js.name, "img/skill/lilpa/" + js.name + ".png");
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_BURGER)) {
            resourceNames.put(js.name, "img/skill/burger/" + js.name + ".png");
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_GOSEGU)) {
            resourceNames.put(js.name, "img/skill/gosegu/" + js.name + ".png");
        }
        for (JsonValue js : jsonMap.get(JsonType.CARD_JSON_JURURU)) {
            resourceNames.put(js.name, "img/skill/jururu/" + js.name + ".png");
        }
        for (AbstractSkill.IntentType it : AbstractSkill.IntentType.values()) {
            resourceNames.put(it.toString(), "img/skill/intent/" + it.toString().toLowerCase() + ".png");
        }

        resourceHandler.requestResource(
                new MultipleResourceRequest<>(resourceNames, Texture.class, (resource, args) -> {
                    Texture texture = (Texture) resource;
                    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    String resourceName = args[0].toString();

                    skillImg.put(resourceName, new Sprite(texture));
                }));
    }

    private void generateStatusImg(ResourceHandler resourceHandler) {
        statusImg.clear();

        HashMap<String, String> resourceNames = new HashMap<>();
        for (JsonValue js : jsonMap.get(JsonType.STATUS_JSON)) {
            if (!js.name.equals("")) {
                resourceNames.put(js.name, "img/status/" + js.name + ".png");
            }
        }

        resourceHandler.requestResource(
                new MultipleResourceRequest<>(resourceNames, Texture.class, (resource, args) -> {
                    Texture texture = (Texture) resource;
                    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    String resourceName = args[0].toString();

                    statusImg.put(resourceName, new Sprite(texture));
                }));
    }

    private void generateItemImg(ResourceHandler resourceHandler) {
        itemImg.clear();
        itemImgTrans.clear();

        HashMap<String, String> itemResources = new HashMap<>();
        HashMap<String, String> itemTransResources = new HashMap<>();
        for (JsonValue js : jsonMap.get(JsonType.ITEM_JSON)) {
            if (!js.name.equals("")) {

                itemResources.put(js.name, "img/item/" + js.name + ".png");
                itemTransResources.put(js.name, "img/item/" + js.name + "_t.png");
            }
        }

        resourceHandler.requestResource(
                new MultipleResourceRequest<>(itemResources, Texture.class, (resource, args) -> {
                    Texture texture = (Texture) resource;
                    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    String resourceName = args[0].toString();

                    itemImg.put(resourceName, new Sprite(texture));
                }));

        resourceHandler.requestResource(
                new MultipleResourceRequest<>(itemTransResources, Texture.class, (resource, args) -> {
                    Texture texture = (Texture) resource;
                    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    String resourceName = args[0].toString();

                    itemImgTrans.put(resourceName, new Sprite(texture));
                }));
    }

    private void generateEventImg(ResourceHandler resourceHandler) {
        eventImg.clear();
        HashMap<String, String> resourceNames = new HashMap<>();
        for (JsonValue js : jsonMap.get(JsonType.EVENT_JSON)) {
            if (!js.name.equals("")) {
                String[] ss = js.get("IMAGE").asStringArray();
                for (String id : ss) {
                    resourceNames.put(id, "img/event/" + id + ".png");
                }
            }
        }

        resourceHandler.requestResource(
                new MultipleResourceRequest<>(resourceNames, Texture.class, (resource, args) -> {
                    Texture texture = (Texture) resource;
                    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    String resourceName = args[0].toString();

                    eventImg.put(resourceName, new Sprite(texture));
                }));
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
