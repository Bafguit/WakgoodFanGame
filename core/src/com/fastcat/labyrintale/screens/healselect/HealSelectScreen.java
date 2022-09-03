package com.fastcat.labyrintale.screens.healselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedExp;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.rewards.HealReward;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;
import com.fastcat.labyrintale.screens.slotselect.SlotSelectScreen;
import com.fastcat.labyrintale.uis.BgImg;

public class HealSelectScreen extends AbstractScreen implements GetSelectedPlayer, GetSelectedExp {

    public BgImg bg = new BgImg();
    public HealSelectText playerSelectText;
    public HealConfirmButton nextButton;
    public HealButton selected;
    public HealButton[] exp;
    private HealReward.HealType selectedType;

    public HealSelectScreen() {
        playerSelectText = new HealSelectText();
        nextButton = new HealConfirmButton(this);

        exp = new HealButton[3];
        float w = Gdx.graphics.getWidth() * 0.25f, h = Gdx.graphics.getHeight();

        HealButton adv = new HealButton(HealReward.HealType.HEAL, this);
        adv.setPosition(w - adv.sWidth / 2, h * 0.6f);
        exp[0] = adv;

        adv = new HealButton(HealReward.HealType.MAX_HEALTH, this);
        adv.setPosition(w * 2 - adv.sWidth / 2, h * 0.6f);
        exp[1] = adv;

        boolean canRevive = false;
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            if (!p.isAlive()) {
                canRevive = true;
                break;
            }
        }
        adv = new HealButton(HealReward.HealType.REVIVE, this);
        adv.clickable = canRevive;
        adv.setPosition(w * 3 - adv.sWidth / 2, h * 0.6f);
        exp[2] = adv;
    }

    public static AbstractPlayer[] getPlayers(HealReward.HealType type) {
        if (type == HealReward.HealType.SKILL_SLOT) {
            Array<AbstractPlayer> pp = new Array<>();
            for (int i = 0; i < 4; i++) {
                AbstractPlayer p = AbstractLabyrinth.players[i];
                if (p.isAlive() && p.hasSlot()) pp.add(p);
            }
            AbstractPlayer[] pa = new AbstractPlayer[pp.size];
            for (int i = 0; i < pp.size; i++) {
                pa[i] = pp.get(i);
            }
            return pa;
        } else if (type == HealReward.HealType.REVIVE) {
            Array<AbstractPlayer> pp = new Array<>();
            for (int i = 0; i < 4; i++) {
                AbstractPlayer p = AbstractLabyrinth.players[i];
                if (!p.isAlive()) pp.add(p);
            }
            AbstractPlayer[] pa = new AbstractPlayer[pp.size];
            for (int i = 0; i < pp.size; i++) {
                pa[i] = pp.get(i);
            }
            return pa;
        } else {
            Array<AbstractPlayer> pp = new Array<>();
            for (int i = 0; i < 4; i++) {
                AbstractPlayer p = AbstractLabyrinth.players[i];
                if (p.isAlive()) pp.add(p);
            }
            AbstractPlayer[] pa = new AbstractPlayer[pp.size];
            for (int i = 0; i < pp.size; i++) {
                pa[i] = pp.get(i);
            }
            return pa;
        }
    }

    @Override
    public void update() {
        if (selected != null) AbstractLabyrinth.cPanel.infoPanel.setInfo(selected.name, selected.desc);
        for (HealButton advisorButton : exp) {
            advisorButton.update();
        }
        nextButton.update();
        playerSelectText.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for (HealButton advisorButton : exp) {
            advisorButton.render(sb);
        }
        nextButton.render(sb);
        playerSelectText.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (HealButton advisorButton : exp) {
            advisorButton.dispose();
        }
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        if (selectedType == HealReward.HealType.HEAL) {
            SoundHandler.playSfx("HEAL");
            player.heal(4);
            Labyrintale.removeTempScreen(this);
        } else if (selectedType == HealReward.HealType.MAX_HEALTH) {
            player.addMaxHealth(2);
            Labyrintale.removeTempScreen(this);
        } else {
            player.revive();
            Labyrintale.removeTempScreen(this);
        }
    }

    @Override
    public void expSelected(HealReward.HealType type) {
        selectedType = type;
        Labyrintale.addTempScreen(new PlayerSelectScreen(getPlayers(type), this));
    }
}