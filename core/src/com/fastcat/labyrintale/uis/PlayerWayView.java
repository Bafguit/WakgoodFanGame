package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.Labyrintale.wayScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.SelectMoveTargetAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;

public class PlayerWayView extends AbstractUI implements GetSelectedTarget {

    public AbstractPlayer player;
    public boolean isTarget = false;

    public PlayerWayView(AbstractPlayer cls) {
        super(FileHandler.getUi().get("ENTITY_POINT"));
        player = cls;
        showImg = false;
    }

    @Override
    protected void updateButton() {
        clickable = player.isAlive();
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (!player.isDead) {
            sb.setColor(Color.WHITE);
            player.render(sb);
        }
    }

    @Override
    protected void onClick() {
        if (!wayScreen.isSelecting) {
            ActionHandler.bot(new SelectMoveTargetAction(this));
        } else {
            wayScreen.gets.onTargetSelected(player);
        }
    }

    @Override
    public void onTargetSelected(AbstractEntity e) {
        AbstractPlayer tar = (AbstractPlayer) e;
        int pi = player.index, ti = tar.index;
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        player.index = ti;
        tar.index = pi;
        AbstractLabyrinth.players[ti] = player;
        AbstractLabyrinth.players[pi] = tar;
        PlayerWayView tv = wayScreen.players[ti];
        tar.setAnimXY(w * 0.425f - w * 0.1f * pi, h * 0.515f);
        tar.ui = this;
        player.setAnimXY(w * 0.425f - w * 0.1f * ti, h * 0.515f);
        player.ui = tv;
        tv.player = player;
        player = tar;
        wayScreen.isSelecting = false;
    }

    @Override
    public boolean setTarget() {
        boolean can = false;
        for (PlayerWayView pv : wayScreen.players) {
            if (pv.player.isAlive() && pv.player != player) {
                pv.isTarget = true;
                can = true;
            } else {
                pv.isTarget = false;
            }
        }
        return can;
    }
}
