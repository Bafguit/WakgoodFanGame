package com.fastcat.labyrintale.screens.skillselect;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import java.util.HashMap;
import java.util.Objects;

public class SkillSelectScreen extends AbstractScreen {

    public SelectType type;
    public AbstractSkill[] skill = new AbstractSkill[3];

    public SkillSelectScreen(SelectType type) {
        this.type = type;
    }

    public SkillSelectScreen(AbstractSkill[] skill) {
        this.type = SelectType.SPECIFIC;
        this.skill = skill;
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

    public static AbstractSkill[] getRandomSkill(PlayerClass cls, HashMap<PlayerClass, Array<AbstractSkill>> map, boolean upgradable) {
        AbstractSkill[] temp = new AbstractSkill[3];
        Array<AbstractSkill> st = map.get(cls);
        Array<AbstractSkill> ss = new Array<>();
        for(AbstractSkill sk : st) {
            ss.add(sk);
        }
        for(int i = 0; i < 3; i++) {
            if(ss.size > 0) {
                AbstractSkill s = ss.random().clone();
                ss.removeValue(s, false);
                if(upgradable) {
                    int p = MathUtils.random(100);
                    if(p >= 10 && p < 20) {
                        Objects.requireNonNull(s).upgrade();
                    }
                }
                temp[i] = s;
            } else break;
        }

        return temp;
    }

    public enum SelectType {
        GENERIC, RARITY, SPECIFIC
    }
}
