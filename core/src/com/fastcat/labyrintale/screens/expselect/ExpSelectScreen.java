package com.fastcat.labyrintale.screens.expselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.interfaces.GetSelectedExp;
import com.fastcat.labyrintale.rewards.ExpReward;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.screens.slotselect.SlotSelectScreen;
import com.fastcat.labyrintale.uis.BgImg;

public class ExpSelectScreen extends AbstractScreen implements GetSelectedPlayer, GetSelectedExp, GetSelectedSlot {

    public BgImg bg = new BgImg();
    public ExpSelectText playerSelectText;
    public ExpConfirmButton nextButton;
    public ExpButton selected;
    public ExpButton[] exp;
    private ExpReward.ExpType selectedType;

    public ExpSelectScreen() {
        playerSelectText = new ExpSelectText();
        nextButton = new ExpConfirmButton(this);

        exp = new ExpButton[4];
        float w = Gdx.graphics.getWidth() * 0.2f, h = Gdx.graphics.getHeight();

        ExpButton adv = new ExpButton(ExpReward.ExpType.SKILL_SLOT, this);
        adv.setPosition(w - adv.sWidth / 2, h * 0.6f);
        exp[0] = adv;

        adv = new ExpButton(ExpReward.ExpType.HEAL, this);
        adv.setPosition(w * 2 - adv.sWidth / 2, h * 0.6f);
        exp[1] = adv;

        adv = new ExpButton(ExpReward.ExpType.MAX_HEALTH, this);
        adv.setPosition(w * 3 - adv.sWidth / 2, h * 0.6f);
        exp[2] = adv;

        boolean canRevive = false;
        for(AbstractPlayer p : AbstractLabyrinth.players) {
            if(!p.isAlive()) {
                canRevive = true;
                break;
            }
        }
        adv = new ExpButton(ExpReward.ExpType.REVIVE, this);
        adv.clickable = canRevive;
        adv.setPosition(w * 4 - adv.sWidth / 2, h * 0.6f);
        exp[3] = adv;
    }

    @Override
    public void update() {
        for (ExpButton advisorButton : exp) {
            advisorButton.update();
        }
        nextButton.update();
        playerSelectText.update();
        if(selected != null) AbstractLabyrinth.cPanel.infoPanel.setInfo(selected.name, selected.desc);
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for (ExpButton advisorButton : exp) {
            advisorButton.render(sb);
        }
        nextButton.render(sb);
        playerSelectText.render(sb);
    }

    public static AbstractPlayer[] getPlayers(ExpReward.ExpType type) {
        if(type == ExpReward.ExpType.SKILL_SLOT) {
            Array<AbstractPlayer> pp = new Array<>();
            for(int i = 0; i < 4; i++) {
                AbstractPlayer p = AbstractLabyrinth.players[i];
                if(p.isAlive() && p.hasSlot()) pp.add(p);
            }
            AbstractPlayer[] pa = new AbstractPlayer[pp.size];
            for(int i = 0; i < pp.size; i++) {
                pa[i] = pp.get(i);
            }
            return pa;
        } else if(type == ExpReward.ExpType.REVIVE) {
            Array<AbstractPlayer> pp = new Array<>();
            for(int i = 0; i < 4; i++) {
                AbstractPlayer p = AbstractLabyrinth.players[i];
                if(!p.isAlive()) pp.add(p);
            }
            AbstractPlayer[] pa = new AbstractPlayer[pp.size];
            for(int i = 0; i < pp.size; i++) {
                pa[i] = pp.get(i);
            }
            return pa;
        } else {
            Array<AbstractPlayer> pp = new Array<>();
            for(int i = 0; i < 4; i++) {
                AbstractPlayer p = AbstractLabyrinth.players[i];
                if(p.isAlive()) pp.add(p);
            }
            AbstractPlayer[] pa = new AbstractPlayer[pp.size];
            for(int i = 0; i < pp.size; i++) {
                pa[i] = pp.get(i);
            }
            return pa;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (ExpButton advisorButton : exp) {
            advisorButton.dispose();
        }
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        if(selectedType == ExpReward.ExpType.SKILL_SLOT) {
            Labyrintale.addTempScreen(new SlotSelectScreen(player, this));
        } else if(selectedType == ExpReward.ExpType.HEAL) {
            player.heal(4);
            Labyrintale.removeTempScreen(this);
        } else if(selectedType == ExpReward.ExpType.MAX_HEALTH) {
            player.addMaxHealth(2);
            Labyrintale.removeTempScreen(this);
        } else {
            player.revive();
            Labyrintale.removeTempScreen(this);
        }
    }

    @Override
    public void expSelected(ExpReward.ExpType type) {
        selectedType = type;
        Labyrintale.addTempScreen(new PlayerSelectScreen(getPlayers(type), this));
    }

    @Override
    public void slotSelected(AbstractPlayer player, int index) {
        Labyrintale.removeTempScreen(this);
    }
}
