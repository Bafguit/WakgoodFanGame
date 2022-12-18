package com.fastcat.labyrintale.uis.control;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.uis.*;
import com.fastcat.labyrintale.uis.EnergyPanel;
import com.fastcat.labyrintale.uis.PlayerBigIcon;
import com.fastcat.labyrintale.uis.StatIcon;
import com.fastcat.labyrintale.uis.TurnView;

public class BattlePanel implements Disposable {

    public static EnergyPanel energy = new EnergyPanel();
    private final float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;
    public AbstractSkill selected;
    public ControlPanel cp;
    public SkillButtonPanel[] skill = new SkillButtonPanel[3];
    public SkillButtonPanel mSkill;
    public SkillButtonPanel pSkill;
    public StatIcon[] stats = new StatIcon[8];
    public ItemPanel passive;
    public ItemPanel[] item = new ItemPanel[2];
    public AbstractEntity curPlayer;
    public PlayerBigIcon cpIcon;
    public TurnView turnView;
    public float rx, ry, ex, ey;

    public BattlePanel(ControlPanel cp) {
        this.cp = cp;
        float mid = 187 * scale, panel = 82 * scale;
        cpIcon = new PlayerBigIcon(AbstractLabyrinth.players[0]);
        cpIcon.setPosition(panel, cp.bg.y + cp.bg.sHeight / 2 - cpIcon.sHeight / 2);
        float bottom = cpIcon.y;
        passive = new ItemPanel();
        passive.setScale(0.6f);
        passive.setPosition(320 * scale, bottom);
        item[0] = new ItemPanel();
        item[0].setScale(0.6f);
        item[0].setPosition(445 * scale, bottom);
        item[1] = new ItemPanel();
        item[1].setScale(0.6f);
        item[1].setPosition(550 * scale, bottom);
        mSkill = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.BASIC);
        mSkill.setScale(0.6f);
        mSkill.setPosition(683 * scale, bottom);
        pSkill = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.BASIC);
        pSkill.setScale(0.6f);
        pSkill.setPosition(798 * scale, bottom);
        for (int i = 0; i < 3; i++) {
            SkillButtonPanel s = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.PLAYER);
            s.setPosition((728 - 204 * i) * scale, mid);
            skill[i] = s;
        }
        energy.setPosition(780 * scale, 490 * scale - energy.sHeight);
        rx = panel;
        ex = cpIcon.sWidth;
        ey = cpIcon.y - bottom;
        ry = bottom;
        int cnt = 0;
        for (int j = 1; j >= 0; j--) {
            for (int i = 0; i < 4; i++) {
                StatIcon s = new StatIcon(StatIcon.StatType.values()[cnt]);
                s.setPosition(320 * scale + w * 0.044f * i, 390 * scale + h * 0.027f * j);
                stats[cnt++] = s;
            }
        }

        turnView = new TurnView();
    }

    public void update() {
        Labyrintale.battleScreen.looking = new Array<>();
        for (int i = 0; i < 3; i++) {
            skill[i].update();
        }
        mSkill.update();
        pSkill.update();
        passive.update();
        for (int i = 0; i < 2; i++) {
            item[i].update();
        }
        setEntity(Labyrintale.battleScreen.currentTurnEntity());
        turnView.update();
        cpIcon.setPlayer(curPlayer);
        cpIcon.update();
        energy.update();
    }

    public void render(SpriteBatch sb) {
        cpIcon.render(sb);

        passive.render(sb);
        for (int i = 0; i < 2; i++) {
            item[i].render(sb);
        }
        mSkill.render(sb);
        pSkill.render(sb);
        for (int i = 0; i < 3; i++) {
            skill[i].render(sb);
        }
        for (int i = 0; i < 8; i++) {
            stats[i].render(sb);
        }

        energy.render(sb);

        if (!Labyrintale.playerInfoScreen.showing) {
            turnView.render(sb);
        }
    }

    public void setEntity(AbstractEntity e) {
        if (e != null && e.isAlive()) {
            curPlayer = e;
            if (e.isPlayer) {
                for (int j = 0; j < 3; j++) {
                    skill[j].skill = e.hand[2 - j];
                }
                mSkill.skill = e.moveTemp;
                pSkill.skill = e.pass;
                passive.item = e.passive;
                item[0].item = e.item[0];
                item[1].item = e.item[1];
            } else {
                for (int j = 0; j < 3; j++) {
                    skill[j].skill = null;
                }
                mSkill.skill = null;
                pSkill.skill = null;
                passive.item = null;
                item[0].item = null;
                item[1].item = null;
            }
            cpIcon.setPlayer(e);
        }
    }

    @Override
    public void dispose() {
        for (int i = 0; i < 3; i++) {
            skill[i].dispose();
        }
        mSkill.dispose();
        pSkill.dispose();
        passive.dispose();
        for (int i = 0; i < 2; i++) {
            item[i].dispose();
        }
        cpIcon.dispose();
        energy.dispose();
    }
}
