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

    public DeckViewScreen(AbstractPlayer player, ViewType type) {
        this.player = player;
        this.type = type;
        deck = getSkills();
        bs = new DeckSkillButton[deck.size];
        for(int i = 0; i < deck.size; i++) {
            DeckSkillButton b = new DeckSkillButton(deck.get(i));
            //TODO 스킬 위치 지정 추가, 마우스 스크롤 추가
            bs[i] = b;
        }
        info = new DeckSkillButton();
        info.setScale(2.5f);
        upInfo = new DeckSkillButton();
        upInfo.setScale(2.5f);
        nextDeckPageButton = new NextDeckPageButton();
        preDeckPageButton = new PreDeckPageButton();
        backToPreButton = new BackToPreButton();
        skillNameText = new SkillNameText();
        skillEffectText = new SkillEffectText();
        upNameText = new UpNameText();
        upEffectText = new UpEffectText();
        pageText = new PageText();
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
            default:
                return player.deck;
        }
    }

    @Override
    public void update() {
        showArrow = false;
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
                if(t > bs.length) break;
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

    }

    public enum InfoType {
        NORMAL, UPGRADED
    }

    public enum ViewType {
        NORMAL, UPGRADE, DRAW, DISCARD, DISPOSABLE
    }
}
