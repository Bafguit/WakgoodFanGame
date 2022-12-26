package com.fastcat.labyrintale.screens.runview;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.getPlayerInstance;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.screens.dead.DeadScreen;
import com.fastcat.labyrintale.screens.result.ResultAdvisor;
import com.fastcat.labyrintale.screens.result.ResultText;
import com.fastcat.labyrintale.uis.StatIcon;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import com.fastcat.labyrintale.utils.BuildInfo;
import java.util.Objects;

public class RunViewScreen extends AbstractScreen {

    private final FontData fontName = ENERGY;
    private final FontData fontHp = INFO_HP_BORDER;
    private final FontData fontData = ENERGY;
    private final float w = Gdx.graphics.getWidth(), h = 1440 * scale;

    public AbstractUI.TempUI hb = new AbstractUI.TempUI(FileHandler.getUi().get("HEALTH_BAR"));
    public Sprite hbb = FileHandler.getUi().get("HEALTH_BACK");
    public RunViewDeckIcon[][] deck = new RunViewDeckIcon[4][3];
    public RunViewItemIcon[][] item = new RunViewItemIcon[4][2];
    public RunViewItemIcon[] passive = new RunViewItemIcon[4];
    public StatIcon[][] stats = new StatIcon[4][8];
    public RunViewPlayerIcon[] pIcons = new RunViewPlayerIcon[4];
    public NoRunsText noRuns;
    public RunViewText text;
    public BackToMainRunButton back;
    public ScreenshotRunButton shot;
    public ResultAdvisor adv;
    public IndexButton left;
    public IndexButton right;
    public DeadScreen.ScreenType dType;
    public String diff;
    public String time;
    public String ver;
    public String seed;
    public String score;
    public String date;
    public int index = -1;

    public RunViewScreen() {
        setBg(FileHandler.getBg().get("BG_RUN"));
        left = new IndexButton(this, true);
        left.setPosition(w * 0.02f, h * 0.5f - left.sHeight / 2);
        right = new IndexButton(this, false);
        right.setPosition(w * 0.98f - right.sWidth, h * 0.5f - right.sHeight / 2);
        int c = 0;
        for (int g = 0; g < 2; g++) {
            for (int f = 0; f < 2; f++) {
                for (int i = 0; i < 3; i++) {
                    RunViewDeckIcon b = new RunViewDeckIcon();
                    b.setPosition((495 + 1007 * g + 150 * i) * scale, (865 - 361 * f) * scale);
                    deck[c][i] = b;
                }
                for (int i = 0; i < 2; i++) {
                    RunViewItemIcon b = new RunViewItemIcon();
                    b.setPosition((965 + 1007 * g + 150 * i) * scale, (865 - 361 * f) * scale);
                    item[c][i] = b;
                }
                RunViewItemIcon ps = new RunViewItemIcon();
                ps.setPosition((835 + 1007 * g) * scale, (1038 - 361 * f) * scale);
                passive[c] = ps;
                int cnt = 0;
                for (int j = 3; j >= 0; j--) {
                    for (int i = 1; i >= 0; i--) {
                        StatIcon s = new StatIcon(StatIcon.StatType.values()[cnt]);
                        s.setPosition((1115 + 1007 * g - 140 * i) * scale, (1023 - 361 * f + 39 * j) * scale);
                        stats[c][cnt++] = s;
                    }
                }
                RunViewPlayerIcon pc = new RunViewPlayerIcon();
                pc.clickable = false;
                pc.setPosition((305 + 1007 * g) * scale, (850 - 361 * f) * scale);
                pIcons[c] = pc;
                c++;
            }
        }
        adv = new ResultAdvisor();
        adv.setPosition(w * 0.12f, h * 0.145f);
        text = new RunViewText(DeadScreen.ScreenType.WIN);
        cType = ControlPanel.ControlType.HIDE;
        diff = "";
        time = "";
        ver = "";
        seed = "";
        score = "";
        date = "";
        shot = new ScreenshotRunButton();
        shot.setPosition(Gdx.graphics.getWidth() * 0.1f, 1296 * scale);
        back = new BackToMainRunButton();
        noRuns = new NoRunsText();
        if (RunHandler.runs.size > 0) setIndex(true);
    }

    public void setIndex(boolean add) {
        if (add && index < RunHandler.runs.size - 1) index++;
        else if (!add && index > 0) index--;
        SaveHandler.SaveData data = RunHandler.runs.get(index);
        int c = 0;
        for (int g = 0; g < 2; g++) {
            for (int f = 0; f < 2; f++) {
                AbstractPlayer p;
                SaveHandler.PlayerData d = data.players[c];
                p = getPlayerInstance(AbstractPlayer.PlayerClass.valueOf(d.id.toUpperCase()));
                p.defineIndex(d.index);
                p.goodLuck = d.goodLuck;
                p.badLuck = d.badLuck;
                p.maxRes = d.maxRes;
                p.minRes = d.minRes;
                p.isDead = d.isDead;
                p.maxHealth = d.maxHealth;
                p.health = d.health;

                for (int j = 0; j < 2; j++) {
                    AbstractItem it = Objects.requireNonNull(
                            GroupHandler.ItemGroup.idSort.get(d.item[j]).clone());
                    it.setOwner(p);
                    p.item[j] = it;
                }

                AbstractSkill[] ss = new AbstractSkill[3];
                for (int j = 0; j < 3; j++) {
                    SaveHandler.SkillData sd = d.deck[j];
                    AbstractSkill s = Objects.requireNonNull(
                            GroupHandler.SkillGroup.idSort.get(d.deck[j].id).clone());
                    s.usedOnly = sd.usedOnly;
                    s.owner = p;
                    for (int k = 0; k < sd.upgradeCount; k++) {
                        s.upgrade();
                    }
                    ss[j] = s;
                }
                p.deck = new Array<>(ss);
                p.stat = d.stat;
                for (int i = 0; i < 3; i++) {
                    deck[c][i].setSkill(p.deck.get(i));
                }
                for (int i = 0; i < 2; i++) {
                    item[c][i].setItem(p.item[i]);
                }
                passive[c].setItem(p.passive);
                int cnt = 0;
                for (int j = 3; j >= 0; j--) {
                    for (int i = 1; i >= 0; i--) {
                        stats[c][cnt++].setEntity(p);
                    }
                }
                pIcons[c].setPlayer(p);
                c++;
            }
        }
        if (data.advisor != null) {
            adv.item = GroupHandler.AdvisorGroup.getAdvisorInstance(
                    AbstractAdvisor.AdvisorClass.valueOf(data.advisor.toUpperCase()));
        } else {
            adv.item = null;
        }
        text = new RunViewText(data.result);
        diff = "";
        if (data.diff == AbstractLabyrinth.Difficulty.NORMAL) diff += "일반";
        else if (data.diff == AbstractLabyrinth.Difficulty.HARD) diff += "어려움";
        else diff += "관";
        time = data.minute + "분 " + data.second + "초";
        ver = "" + (InputHandler.isDesktop ? BuildInfo.BUILD_VERSION : "ANDROID");
        seed = "" + data.random.seed;
        score = "" + data.scoreHandle.score;
        shot.setDate(data.date);
        shot.text = "스크린샷";
    }

    @Override
    public void update() {
        if (RunHandler.runs.size > 0) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    deck[i][j].update();
                }
                for (int j = 0; j < 2; j++) {
                    item[i][j].update();
                }
                for (int j = 0; j < 8; j++) {
                    stats[i][j].update();
                }
                passive[i].update();
                pIcons[i].update();
            }
            adv.update();
            text.update();
            shot.update();
        }
        back.update();
        left.update();
        right.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        if (RunHandler.runs.size > 0) {
            text.render(sb);
            shot.render(sb);
            adv.render(sb);
            int cnt = 0;
            for (int f = 0; f < 2; f++) {
                for (int g = 0; g < 2; g++) {
                    AbstractPlayer player = pIcons[cnt++].p;
                    sb.draw(hbb, (495 + 1007 * f) * scale, (1052 - 361 * g) * scale, w * 0.12f, h * 0.03f);
                    sb.draw(
                            hb.img,
                            scale * (495 + 1007 * f),
                            scale * (1052 - 361 * g),
                            0,
                            0,
                            w * 0.12f,
                            h * 0.03f,
                            Math.max(((float) player.health) / ((float) player.maxHealth), 0),
                            1,
                            0);
                    FontHandler.renderLineLeft(
                            sb,
                            fontName,
                            player.name,
                            scale * (495 + 1007 * f),
                            scale * (1138 - 361 * g),
                            w * 0.12f,
                            50);
                    FontHandler.renderCenter(
                            sb,
                            fontHp,
                            player.health + "/" + player.maxHealth,
                            scale * (495 + 1007 * f),
                            scale * (1073 - 361 * g),
                            w * 0.12f,
                            h * 0.03f);
                }
            }

            renderCenter(sb, fontData, diff, scale * 731, scale * 265, 300 * scale, h * 0.1f);
            renderCenter(sb, fontData, time, scale * 1031, scale * 265, 300 * scale, h * 0.1f);
            renderCenter(sb, fontData, score, scale * 1331, scale * 265, 300 * scale, h * 0.1f);
            renderCenter(sb, fontData, seed, scale * 1631, scale * 265, 300 * scale, h * 0.1f);
            renderCenter(sb, fontData, ver, scale * 1931, scale * 265, 300 * scale, h * 0.1f);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    deck[i][j].render(sb);
                }
                for (int j = 0; j < 2; j++) {
                    item[i][j].render(sb);
                }
                for (int j = 0; j < 8; j++) {
                    stats[i][j].render(sb);
                }
                passive[i].render(sb);
                pIcons[i].render(sb);
            }
        } else {
            noRuns.render(sb);
        }
        back.render(sb);
        left.render(sb);
        right.render(sb);
    }

    @Override
    public void show() {
        back.over = false;
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
