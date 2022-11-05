package com.fastcat.labyrintale.screens.slotselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.uis.BgImg;

public class ConfirmSlotScreen extends AbstractScreen implements GetSelectedSlot {

    public BgImg bg = new BgImg();
    public SlotSelectScreen sc;
    public SlotCheckButton from;
    public SlotCheckButton to;
    public ConfirmButton confirm;
    public CancelButton cancel;
    public AbstractPlayer player;
    public AbstractSkill skill;
    public AbstractUI.TempUI arrow;
    public int index;

    public ConfirmSlotScreen(AbstractPlayer p, int index, SlotSelectScreen sc) {
        player = p;
        this.index = index;
        skill = p.deck.get(index);
        from = new SlotCheckButton(skill);
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        from.setPosition(w * 0.4f - from.sWidth / 2, h * 0.6f);
        arrow = new AbstractUI.TempUI(FileHandler.getUi().get("ARROW_RIGHT"));
        arrow.setPosition(w * 0.5f - from.sWidth / 2, h * 0.6f);
        AbstractSkill up = skill.clone();
        up.upgrade();
        to = new SlotCheckButton(up);
        to.setPosition(w * 0.6f - from.sWidth / 2, h * 0.6f);
        this.sc = sc;
        confirm = new ConfirmButton(this);
        cancel = new CancelButton(this);
    }

    @Override
    public void update() {
        from.update();
        arrow.update();
        to.update();
        confirm.update();
        cancel.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        from.render(sb);
        arrow.render(sb);
        to.render(sb);
        confirm.render(sb);
        cancel.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void slotSelected(AbstractPlayer player, int index) {
        sc.slotSelected(player, index);
    }
}
