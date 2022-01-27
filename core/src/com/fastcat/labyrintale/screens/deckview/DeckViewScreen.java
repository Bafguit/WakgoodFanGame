package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import static com.fastcat.labyrintale.handlers.InputHandler.*;

public class DeckViewScreen extends AbstractScreen {

    public static ShapeRenderer shr = new ShapeRenderer();

    public BgImg bg = new BgImg();
    public DeckSkillButton[] bs;
    public Array<AbstractSkill> deck;
    public NextDeckPageButton nextDeckPageButton;
    public PreDeckPageButton preDeckPageButton;
    public BackToPreButton backToPreButton;
    public DeckSkillButton info;
    public DeckSkillButton upInfo;
    public SkillNameText skillNameText;
    public SkillEffectText skillEffectText;
    public UpNameText upNameText;
    public UpEffectText upEffectText;
    public PageText pageText;
    public AbstractPlayer player;
    public ViewType type;
    public InfoType iType;
    public int page = 0;
    public int max;
    public boolean showArrow = false;
    public boolean showInfo = false;

    public DeckViewScreen(AbstractPlayer player, ViewType type) {
        this.player = player;
        this.type = type;
        deck = getSkills();
        bs = new DeckSkillButton[deck.size];
        for(int i = 0; i < deck.size; i++) {
            DeckSkillButton b = new DeckSkillButton(deck.get(i));
            b.screen = this;
            bs[i] = b;
        }
        info = new DeckSkillButton();
        info.setScale(2.5f);
        upInfo = new DeckSkillButton();
        upInfo.setScale(2.5f);
        nextDeckPageButton = new NextDeckPageButton();
        nextDeckPageButton.screen = this;
        preDeckPageButton = new PreDeckPageButton();
        preDeckPageButton.screen = this;
        backToPreButton = new BackToPreButton();
        backToPreButton.screen = this;
        skillNameText = new SkillNameText();
        skillNameText.screen = this;
        skillEffectText = new SkillEffectText();
        skillEffectText.screen = this;
        upNameText = new UpNameText();
        upNameText.screen = this;
        upEffectText = new UpEffectText();
        upEffectText.screen = this;
        pageText = new PageText();
        pageText.screen = this;
        max = Math.max(deck.size - 1, 0) / 20;
    }

    private Array<AbstractSkill> getSkills() {
        switch (type) {
            case DISPOSABLE:
                return player.disposablePile;
            case DISCARD:
                return player.discardPile;
            case DRAW:
                return player.drawPile;
            case UPGRADE:
                Array<AbstractSkill> temp = new Array<>();
                for(AbstractSkill skill : player.deck) {
                    if(!skill.upgraded) temp.add(skill);
                }
                return temp;
            case REMOVE:
                Array<AbstractSkill> temp2 = new Array<>();
                for(AbstractSkill skill : player.deck) {
                    if(skill.removable) temp2.add(skill);
                }
                return temp2;
            default:
                return player.deck;
        }
    }

    @Override
    public void update() {
        showArrow = false;
        info.disable();
        upInfo.disable();
        int temp = page == max ? deck.size - page * 20 : 20;
        for(int i = 0; i < temp; i++) {
            bs[page * 20 + i].update();
        }
        pageText.update();
        info.update();
        upInfo.update();
        if(page == 0) preDeckPageButton.disable();
        else preDeckPageButton.enable();
        if(page == max) nextDeckPageButton.disable();
        else nextDeckPageButton.enable();
        nextDeckPageButton.update();
        preDeckPageButton.update();
        backToPreButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        if(showArrow) {
            sb.end();
            float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
            float sw = 50 * scale;
            shr.begin(ShapeRenderer.ShapeType.Filled);
            sb.setColor(Color.WHITE);
            shr.triangle(w * 0.7f - sw, h * 0.5f + sw / 2, w * 0.7f + sw, h * 0.5f + sw / 2, w * 0.7f, h * 0.5f - sw / 2);
            shr.end();
            sb.begin();
        }
        pageText.render(sb);
        nextDeckPageButton.render(sb);
        preDeckPageButton.render(sb);
        int temp = (page == max ? deck.size - page * 20 - 1 : 19) / 4 + 1;
        for(int j = 0; j < temp; j++) {
            for(int i = 0; i < 4; i++) {
                int t = page * 20 + j * 4 + i;
                if(t >= bs.length) break;
                else {
                    DeckSkillButton b = bs[t];
                    b.setPosition(Gdx.graphics.getWidth() * (0.15f + i * 0.075f) - b.sWidth / 2, Gdx.graphics.getHeight() * (0.8f - j * 0.12f) - b.sHeight / 2);
                    b.render(sb);
                }
            }
        }
        info.render(sb);
        upInfo.render(sb);
        if(info.enabled) {
            skillNameText.render(sb);
            skillEffectText.render(sb);
        }
        if(upInfo.enabled) {
            upNameText.render(sb);
            upEffectText.render(sb);
        }
        backToPreButton.render(sb);
    }

    public void adjustPage(int a) {
        page = MathUtils.clamp(page + a, 0, max);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        nextDeckPageButton.dispose();
        preDeckPageButton.dispose();
        backToPreButton.dispose();
        info.dispose();
        upInfo.dispose();
        skillNameText.dispose();
        skillEffectText.dispose();
        pageText.dispose();
        upNameText.dispose();
        upEffectText.dispose();
    }

    public enum InfoType {
        NORMAL, UPGRADED
    }

    public enum ViewType {
        NORMAL, REMOVE, UPGRADE, DRAW, DISCARD, DISPOSABLE
    }
}
