package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractImage;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.uis.CardPanel;

public class BattleScreen extends AbstractScreen {

    private AbstractImage cardPanel;
    private AbstractImage cardPanel2;

    public AbstractSkill[] cards;

    public BattleScreen(Labyrintale game) {
        super(game);
        cardPanel = new CardPanel(1);
        cardPanel2 = new CardPanel(2);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
