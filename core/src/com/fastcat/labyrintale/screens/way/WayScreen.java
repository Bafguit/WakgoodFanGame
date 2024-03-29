package com.fastcat.labyrintale.screens.way;

import static com.fastcat.labyrintale.handlers.FontHandler.HP;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.FloorChangeEffect;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;
import com.fastcat.labyrintale.screens.tutorial.TutorialScreen;
import com.fastcat.labyrintale.uis.PlayerWayView;
import com.fastcat.labyrintale.uis.WayBgImg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class WayScreen extends AbstractScreen {
    private final WayBgImg bgImg;

    public AbstractUI.TempUI hb = new AbstractUI.TempUI(FileHandler.getUi().get("HEALTH_BAR"));
    public AbstractUI.TempUI ground = new AbstractUI.TempUI(FileHandler.getUi().get("GROUND"));
    public Sprite hbb = FileHandler.getUi().get("HEALTH_BACK");
    public Array<WaySelectButton> buttons;
    public Array<WayIcon> icons;
    public Array<WayDesc> desc;
    public AbstractWay way;
    public PlayerWayView[] players = new PlayerWayView[4];
    public GetSelectedTarget gets;
    public int wayCount;
    public boolean isSelecting = false;
    public boolean isNew = true;

    public WayScreen() {
        this(AbstractLabyrinth.currentFloor.currentWay);
    }

    public WayScreen(AbstractWay wy) {
        way = wy;
        cType = ControlPanel.ControlType.BASIC;
        ground.setPosition(0, 0);
        float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;
        for (int i = 0; i < 4; i++) {
            PlayerWayView pv = new PlayerWayView(AbstractLabyrinth.players[i]);
            pv.setPosition(w * 0.425f - w * 0.1f * i - pv.sWidth / 2, h * 0.49f - 40 * scale);
            pv.player.setAnimXY(w * 0.425f - w * 0.1f * i, h * 0.515f - 40 * scale);
            pv.player.ui = pv;
            players[i] = pv;
        }
        buttons = new Array<>();
        icons = new Array<>();
        desc = new Array<>();
        for (int i = 0; i < way.choices.length; i++) {
            AbstractChoice ch = way.choices[i];
            if (ch != null && ch.canGo) {
                float tw = w * (0.6f + 0.135f * i);

                WaySelectButton b = new WaySelectButton(this, ch, AbstractLabyrinth.floorNum);
                b.setPosition(tw - b.sWidth / 2, h * 0.65f - b.sHeight / 2 - 40 * scale);
                buttons.add(b);

                Sprite s;
                if(ch.type == AbstractChoice.ChoiceType.BOSS) {
                    s = FileHandler.getUi().get("BOSS_" + AbstractLabyrinth.floorNum);
                } else {
                    s = ch.img;
                }
                WayIcon c = new WayIcon(b, s);
                c.setPosition(tw - c.sWidth / 2, h * 0.74f - c.sHeight / 2 - 40 * scale);
                icons.add(c);

                WayDesc d = new WayDesc(ch.desc);
                d.setPosition(tw - d.sWidth / 2, h * 0.62f - d.sHeight / 2 - 40 * scale);
                desc.add(d);
            }
        }
        wayCount = buttons.size;
        setBg(FileHandler.getBg().get("BG_WAY_" + AbstractLabyrinth.floorNum));
        bgImg = new WayBgImg(this);
    }

    @Override
    public void update() {
        if (isNew && AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.ENTRY) {
            EffectHandler.add(new FloorChangeEffect(AbstractLabyrinth.floorNum));
            isNew = false;
        }
        for (int i = 0; i < 4; i++) {
            players[i].update();
        }
        for (int i = 0; i < wayCount; i++) {
            buttons.get(i).update();
            icons.get(i).update();
            desc.get(i).update();
        }
        bgImg.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        if (isSelecting) {
            for (int i = 3; i >= 0; i--) {
                PlayerWayView pv = players[i];
                if (!pv.isTarget) pv.render(sb);
            }
            bgImg.render(sb);
            for (int i = 3; i >= 0; i--) {
                PlayerWayView pv = players[i];
                if (pv.isTarget) pv.render(sb);
            }
        } else {
            for (int i = 3; i >= 0; i--) {
                players[i].render(sb);
            }
        }
        for (int i = 0; i < wayCount; i++) {
            buttons.get(i).render(sb);
            icons.get(i).render(sb);
            desc.get(i).render(sb);
        }
        sb.setColor(Color.WHITE);
        sb.draw(ground.img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        float h = 1440 * InputHandler.scale;
        for (int i = 0; i < 4; i++) {
            PlayerWayView tp = players[i];
            float px = tp.player.animX - 80 * scale, py = tp.player.animY - h * 0.025f;
            if (!tp.player.isDead) {
                sb.draw(hbb, px, py, 160 * scale, 22 * scale);
                sb.draw(
                        hb.img,
                        px,
                        py,
                        0,
                        0,
                        160 * scale,
                        22 * scale,
                        Math.max(((float) tp.player.health) / ((float) tp.player.maxHealth), 0),
                        1,
                        0);
                renderCenter(
                        sb,
                        HP,
                        tp.player.health + "/" + tp.player.maxHealth,
                        px,
                        py + hb.sHeight / 2,
                        160 * scale,
                        hb.sHeight);
            }
        }
    }

    public void selectTarget(GetSelectedTarget gets) {
        this.gets = gets;
        isSelecting = true;
    }

    @Override
    public void show() {
        SoundHandler.addWay().stop = false;
        for(WaySelectButton b : buttons) {
            b.show();
        }
        for(WayIcon c : icons) {
            c.show();
        }
        if (SettingHandler.setting.wayTutorial) {
            AbstractLabyrinth.cPanel.type = ControlPanel.ControlType.BASIC;
            Labyrintale.openTutorial(TutorialScreen.TutorialType.WAY);
            SettingHandler.setting.wayTutorial = false;
            SettingHandler.save();
        }
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
