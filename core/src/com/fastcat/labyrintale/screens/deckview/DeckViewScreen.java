package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.InputHandler;

import static com.fastcat.labyrintale.handlers.InputHandler.*;

public class DeckViewScreen extends AbstractScreen {

    public static ShapeRenderer shr = new ShapeRenderer();

    public AbstractScreen preScreen;
    public Array<DeckSkillButton> buttons;
    public Array<AbstractSkill> skills;
    public DeckSkillButton info;
    public DeckSkillButton upInfo;
    public SkillNameText skillNameText;
    public SkillEffectText skillEffectText;
    public UpNameText upNameText;
    public UpEffectText upEffectText;
    public AbstractPlayer player;
    public ViewType type;
    public InfoType iType;
    public boolean showArrow = false;

    public DeckViewScreen(AbstractScreen pre, AbstractPlayer player, ViewType type) {
        preScreen = pre;
        this.player = player;
        this.type = type;
        skills = getSkills();
        for(AbstractSkill s : skills) {
            DeckSkillButton b = new DeckSkillButton(s);
            //TODO 스킬 위치 지정 추가, 마우스 스크롤 추가
            buttons.add(b);
        }
        info = new DeckSkillButton();
        info.setScale(2.5f);
        upInfo = new DeckSkillButton();
        upInfo.setScale(2.5f);
    }



    private Array<AbstractSkill> getSkills() {
        switch (type) {
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
        for(DeckSkillButton b : buttons) {
            b.update();
        }
        info.update();
        upInfo.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        if(showArrow) {
            sb.end();
            float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
            float sw = 50 * scale;
            shr.begin(ShapeRenderer.ShapeType.Filled);
            shr.triangle(w * 0.5f - sw, h * 0.15f - sw, w * 0.5f - sw, h * 0.15f + sw, w * 0.5f + sw, h * 0.15f);
            shr.end();
            sb.begin();
        }
        for(DeckSkillButton b : buttons) {
            b.render(sb);
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
        VIEW, UPGRADE
    }
}
