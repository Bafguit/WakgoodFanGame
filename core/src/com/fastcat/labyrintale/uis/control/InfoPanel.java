package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import com.fastcat.labyrintale.screens.deckview.DeckSkillButton;
import com.fastcat.labyrintale.uis.PlayerIcon;

import java.util.Objects;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;
import static com.fastcat.labyrintale.uis.control.InfoPanel.InfoType.*;

public class InfoPanel extends AbstractUI {

    public Sprite border = FileHandler.ui.get("BORDER_B");
    public String name = "";
    public String desc = "";
    public FontHandler.FontData fontName = CARD_BIG_NAME;
    public FontHandler.FontData fontDesc = CARD_BIG_DESC;
    public PlayerIcon[] pIcons = new PlayerIcon[4];
    public InfoType type = InfoType.COLOR;
    public AbstractSkill skill;
    public AbstractStatus status;
    public AbstractPlayer player;
    public boolean show;
    public boolean renderIcon = true;
    public float nx, ny, nw, nh, dx, dy, dw, dh;

    private AbstractSkill.SkillTarget target;

    public InfoPanel() {
        super(FileHandler.ui.get("BORDER_B"));
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        setPosition(w * 0.5f, h * 0.26f - sHeight / 2);
        nx = dx = w * 0.69f;
        ny = h * 0.41f;
        dy = h * 0.4f - 45 * scale;
        nw = dw = 400 * InputHandler.scale;
        nh = 60 * InputHandler.scale;
        dh = 280 * InputHandler.scale;

        for(int i = 0; i < 4; i++) {
            PlayerIcon c = new PlayerIcon(AbstractLabyrinth.players[i]);
            c.setPosition(w * (0.41f - 0.08f * i) - c.sWidth / 2, h * 0.275f);
            pIcons[i] = c;
        }

    }

    @Override
    public void updateButton() {
        img = null;
        type = InfoType.COLOR;
        skill = null;
        status = null;
        target = null;
        name = "";
        desc = "";
        show = false;
        if(renderIcon) {
            for (int i = 0; i < 4; i++) {
                pIcons[i].update();
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);
            if(img != null) {
                sb.draw(img, x, y, sWidth, sHeight);
                renderLineBotLeft(sb, fontName, name, nx, ny, nw, nh);
                if (type == InfoType.SKILL) {
                    renderCardLeft(sb, skill, fontDesc, desc, dx, dy, dw, dh);
                } else {
                    renderColorLeft(sb, fontDesc, desc, dx, dy, dw);
                }
            }
            sb.draw(border, x, y, sWidth, sHeight);
            if(target != null) renderCenter(sb, fontDesc, AbstractSkill.getTargetString(target), x, y - sHeight * 0.05f, sWidth, sHeight);
        }

        renderIcon = !(Labyrintale.getCurScreen() instanceof BattleScreen);
        if(renderIcon) {
            for (int i = 0; i < 4; i++) {
                pIcons[i].render(sb);
            }
        }
    }

    public void setInfo(AbstractSkill s) {
        if(s != null) {
            img = s.imgBig;
            name = s.name;
            desc = s.desc;
            type = InfoType.SKILL;
            skill = s;
            target = skill.target;
            show = true;
        }
    }

    public void setInfo(AbstractStatus s) {
        if(s != null) {
            img = s.imgBig;
            name = s.name;
            desc = s.getDesc();
            type = STATUS;
            status = s;
            target = status.target;
            show = true;
        }
    }

    public void setInfo(AbstractPlayer p) {
        if(p != null) {
            img = p.imgBig;
            name = p.name;
            desc = p.desc;
            type = PLAYER;
            player = p;
            target = null;
            show = true;
        }
    }

    public void setInfo(Sprite img, String name, String desc) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        type = InfoType.COLOR;
    }

    public enum InfoType {
        SKILL, STATUS, COLOR, PLAYER
    }
}
