package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import java.util.LinkedList;

public class TurnView extends AbstractUI {

    private LinkedList<TurnIcon> icons = new LinkedList<>();
    private TurnIcon now;
    private float nx, ny, nw;

    public TurnView() {
        super(FileHandler.getUi().get("BORDER"));
    }

    public void setNewTurns(Array<AbstractEntity> turns) {
        icons.clear();
        for (int i = 0; i < turns.size; i++) {
            TurnIcon c = new TurnIcon(turns.get(i), this);
            icons.add(c);
        }
        fontData = FontHandler.ROUND;
        nx = 1830 * InputHandler.scale;
        ny = Gdx.graphics.getHeight() * 0.97f;
        nw = 200 * InputHandler.scale;
    }

    @Override
    protected void updateButton() {
        if (icons.size() > 0) {
            int index = Labyrintale.battleScreen.getCurrentTurn();
            float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), wh = h * 0.87f, wc = w - h * 0.13f;
            for (int i = 0; i < index; i++) {
                TurnIcon t = icons.get(i);
                t.isMain = false;
                t.setPosition(-10000, -10000);
            }

            TurnIcon tx = icons.get(index);
            tx.isMain = true;
            tx.setPosition(wc, wh);

            int cnt = 1;
            for (int i = index + 1; i < icons.size(); i++) {
                TurnIcon t = icons.get(i);
                t.isMain = false;
                if (cnt <= 3) {
                    t.setPosition(wc - t.sWidth * 0.8f * (cnt), wh + t.bb.sHeight - t.sHeight);
                } else {
                    t.setPosition(-10000, -10000);
                }
                cnt++;
            }
            now = null;
            for (int i = icons.size() - 1; i >= 0; i--) {
                icons.get(i).update();
            }
            if (now != null) {
                Labyrintale.battleScreen.looking.add(now.view);
                AbstractLabyrinth.cPanel.battlePanel.setPlayer(now.view);
            }
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        for (int i = icons.size() - 1; i >= 0; i--) {
            icons.get(i).render(sb);
        }

        FontHandler.renderLineRight(sb, FontHandler.ROUND, "라운드 " + Labyrintale.battleScreen.round, nx, ny, nw, nw);
    }

    private static class TurnIcon extends AbstractUI {

        private final TempUI arrow;
        private final TempUI bb;
        private final TurnView tv;
        public AbstractEntity view;
        public AbstractSkill skill;
        public boolean isMain;

        public TurnIcon(AbstractEntity entity, TurnView tv) {
            super(FileHandler.getUi().get("BORDER_T"));
            this.tv = tv;
            bb = new TempUI(FileHandler.getUi().get("BORDER_T2"));
            arrow = new TempUI(FileHandler.getUi().get("TURN_ARROW"));
            clickable = false;
            this.view = entity;
            isMain = false;
            isPixmap = true;
        }

        @Override
        protected void updateButton() {
            if (over && view.isAlive()) {
                tv.now = this;
            }
            arrow.update();
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if (enabled) {
                if (view.isAlive()) sb.setColor(Color.WHITE);
                else sb.setColor(Color.DARK_GRAY);
                if (isMain) {
                    if (view != null) {
                        sb.draw(view.imgTurn, x, y, bb.sWidth, bb.sHeight);
                    }
                    sb.draw(bb.img, x, y, bb.sWidth, bb.sHeight);
                    if (view.isAlive()) {
                        sb.draw(
                                arrow.img,
                                x + bb.sWidth / 2 - arrow.sWidth / 2,
                                y - arrow.sHeight * 0.75f,
                                arrow.sWidth,
                                arrow.sHeight);
                    }
                } else {
                    if (view != null) {
                        sb.draw(view.imgTurn, x, y, sWidth, sHeight);
                    }
                    sb.draw(img, x, y, sWidth, sHeight);
                }
            }
        }
    }
}
