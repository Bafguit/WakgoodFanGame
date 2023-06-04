package com.fastcat.labyrintale.screens.playerinfo;

import static com.fastcat.labyrintale.Labyrintale.playerInfoScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.INFO_HP;
import static com.fastcat.labyrintale.handlers.FontHandler.INFO_NAME;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedStat;
import com.fastcat.labyrintale.screens.charinfo.CharInfoScreen;
import com.fastcat.labyrintale.screens.statselect.StatSelectScreen;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.StatIcon;

public class PlayerInfoScreen extends AbstractScreen implements GetSelectedStat {

    public static final Color hbc = new Color(0.4f, 0, 0, 1);

    private final BgImg bg = new BgImg();
    private final FontHandler.FontData fontName = INFO_NAME;
    private final FontHandler.FontData fontHp = INFO_HP;
    private final float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;

    private CharInfoScreen soloInfo;
    public StatSelectScreen statScreen;

    public AbstractUI.TempUI hb = new AbstractUI.TempUI(FileHandler.getUi().get("HEALTH_BAR"));
    public Sprite hbb = FileHandler.getUi().get("HEALTH_BACK");
    public PlayerInfoDeckIcon[][] deck = new PlayerInfoDeckIcon[4][3];
    public PlayerInfoItemIcon[][] item = new PlayerInfoItemIcon[4][2];
    public PlayerInfoItemIcon[] passive = new PlayerInfoItemIcon[4];
    public StatIcon[][] stats = new StatIcon[4][8];
    public PlayerInfoIcon[] pIcons = new PlayerInfoIcon[4];
    public UpgradeStatButton upStat;
    public boolean showing;

    public PlayerInfoScreen() {
        int c = 0;
        for (int g = 0; g < 2; g++) {
            for (int f = 0; f < 2; f++) {
                AbstractUI.SubText.SubWay sw = AbstractUI.SubText.SubWay.values()[f];
                AbstractPlayer player = AbstractLabyrinth.players[c];
                for (int i = 0; i < 3; i++) {
                    PlayerInfoDeckIcon b = new PlayerInfoDeckIcon(player.deck.get(i));
                    b.setPosition(w * (0.2f + 0.435f * g + 0.06f * i) - b.sWidth / 2, h * (0.62f - 0.23f * f));
                    b.subWay = sw;
                    deck[c][i] = b;
                }
                for (int i = 0; i < 2; i++) {
                    PlayerInfoItemIcon b = new PlayerInfoItemIcon(player.item[i]);
                    b.setPosition(w * (0.39f + 0.435f * g + 0.06f * i) - b.sWidth / 2, h * (0.62f - 0.23f * f));
                    b.subWay = sw;
                    item[c][i] = b;
                }
                PlayerInfoItemIcon ps = new PlayerInfoItemIcon(player.passive);
                ps.setPosition(w * (0.33f + 0.435f * g) - ps.sWidth / 2, h * (0.73f - 0.23f * f));
                ps.subWay = sw;
                passive[c] = ps;
                int cnt = 0;
                for (int j = 3; j >= 0; j--) {
                    for (int i = 1; i >= 0; i--) {
                        StatIcon s = new StatIcon(StatIcon.StatType.values()[cnt]);
                        s.entity = player;
                        s.setPosition(w * (0.425f + 0.435f * g - 0.055f * i), h * (0.72f - 0.23f * f + 0.027f * j));
                        s.subWay = sw;
                        stats[c][cnt++] = s;
                    }
                }
                PlayerInfoIcon pc = new PlayerInfoIcon(c);
                pc.setPosition(w * (0.15f + 0.435f * g) - pc.sWidth, h * (0.62f - 0.23f * f));
                pIcons[c] = pc;
                c++;
            }
        }
        cType = Labyrintale.getBaseScreen().cType;
        upStat = new UpgradeStatButton();
        statScreen = new StatSelectScreen();
    }

    public static void view() {
        playerInfoScreen.showing = true;
        if(AbstractLabyrinth.mode == AbstractLabyrinth.Mode.SOLO) {
            playerInfoScreen.soloInfo = new CharInfoScreen(AbstractLabyrinth.players[0]);
            Labyrintale.addTempScreen(playerInfoScreen.soloInfo);
        } else {
            Labyrintale.addTempScreen(playerInfoScreen);
        }
    }

    public static void remove() {
        playerInfoScreen.showing = false;
        if (playerInfoScreen.soloInfo != null) {
            Labyrintale.removeTempScreen(playerInfoScreen.soloInfo);
            playerInfoScreen.soloInfo = null;
        }
        Labyrintale.removeTempScreen(playerInfoScreen);
    }

    @Override
    public void update() {
        cType = Labyrintale.getBaseScreen().cType;
        if (InputHandler.cancel || InputHandler.info) remove();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                deck[i][j].skill = AbstractLabyrinth.players[i].deck.get(j);
                deck[i][j].update();
            }
            for (int j = 0; j < 2; j++) {
                item[i][j].skill = AbstractLabyrinth.players[i].item[j];
                item[i][j].update();
            }
            for (int j = 0; j < 8; j++) {
                stats[i][j].entity = AbstractLabyrinth.players[i];
                stats[i][j].update();
            }
            passive[i].skill = AbstractLabyrinth.players[i].passive;
            passive[i].update();
            pIcons[i].index = AbstractLabyrinth.players[i].index;
            pIcons[i].update();
        }
        /*if (AbstractLabyrinth.sp > 0) {
        upStat.update();
        }*/
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);

        // spine
        // player.infoSpine.render(sb);

        // health bar

        // button
        int cnt = 0;
        for (int f = 0; f < 2; f++) {
            for (int g = 0; g < 2; g++) {
                AbstractPlayer player = AbstractLabyrinth.players[cnt++];
                sb.draw(hbb, w * (0.175f + 0.435f * f), h * (0.75f - 0.23f * g), w * 0.12f, h * 0.03f);
                sb.draw(
                        hb.img,
                        w * (0.175f + 0.435f * f),
                        h * (0.75f - 0.23f * g),
                        0,
                        0,
                        w * 0.12f,
                        h * 0.03f,
                        Math.max(((float) player.health) / ((float) player.maxHealth), 0),
                        1,
                        0);
                FontHandler.renderLineLeft(
                        sb, fontName, player.name, w * (0.175f + 0.435f * f), h * (0.81f - 0.23f * g), w * 0.12f, 50);
                FontHandler.renderCenter(
                        sb,
                        fontHp,
                        player.health + "/" + player.maxHealth,
                        w * (0.175f + 0.435f * f),
                        h * (0.765f - 0.23f * g),
                        w * 0.12f,
                        h * 0.03f);
            }
        }

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
        /*if (AbstractLabyrinth.sp > 0) upStat.render(sb);*/
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    private void resetAnimationScale() {}

    @Override
    public void dispose() {}

    @Override
    public void statSelected(AbstractEntity entity, StatIcon.StatType stat) {}
}
