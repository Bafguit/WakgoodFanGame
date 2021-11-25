package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.skills.Temp;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.FileHandler.CHAR_SELECT;

public class StatusButton extends AbstractUI {

    private Texture border = CHAR_SELECT;
    public AbstractStatus status;
    public boolean isInfo = false;

    public StatusButton() {
        super(CHAR_SELECT);
    }

    public StatusButton(AbstractEntity entity) {
        this();
        setScale(0.4f);
        status = entity.status;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over && !isInfo) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);

            if(status != null) {
                sb.draw(status.img.getTexture(), x, y, sWidth, sHeight);
            }
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if(!isInfo && over && status != null) {
            battleScreen.statusInfo.status = status;
            battleScreen.nameText.text = status.name;
            battleScreen.effectText2.text = status.desc;
            battleScreen.looking = getTargets(status.target);
        } else if(isInfo && status != null) {
            boolean ov = false;
            for (int i = 0; i < 4; i++) {
                if (battleScreen.playerStatus[i].over || battleScreen.enemyStatus[i].over) {
                    ov = true;
                    break;
                }
            }
            if(!ov) {
                status = null;
                battleScreen.nameText.text = "";
                battleScreen.effectText2.text = "";
            }
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {

    }
}
