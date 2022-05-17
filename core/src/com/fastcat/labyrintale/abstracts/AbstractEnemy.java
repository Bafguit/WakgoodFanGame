package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.skills.enemy.MoveLeftE;
import com.fastcat.labyrintale.skills.enemy.MoveRightE;
import com.fastcat.labyrintale.skills.player.MoveLeft;
import com.fastcat.labyrintale.skills.player.MoveRight;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.publicRandom;

public abstract class AbstractEnemy extends AbstractEntity {

    public EnemyType type;
    public Array<AbstractSkill> drawPile;
    public Array<AbstractSkill> discardPile;
    public Array<AbstractSkill> disposablePile;
    protected boolean isRandom = true;

    public AbstractEnemy(String id, EnemyType type, int maxHealth) {
        super(id, 1, maxHealth, FileHandler.atlas.get(id), FileHandler.skeleton.get(id), false);
        this.type = type;
        //skeleton.setScaleX(-skeleton.getScaleX());
        drawPile = new Array<>();
        discardPile = new Array<>();
        disposablePile = new Array<>();
        mLeft = mLeftTemp = new MoveLeftE(this);
        mRight = mRightTemp = new MoveRightE(this);
    }

    @Override
    public void newDeck() {
        hand = new AbstractSkill[4];
        drawPile.clear();
        discardPile.clear();
        disposablePile.clear();
        drawPile = new Array<>(deck);
    }

    public void shuffleHand() {
        for(int i = 0; i < handSize; i++) {
            if(hand[i] != null) {
                discardPile.add(hand[i]);
            }
        }
        hand = new AbstractSkill[handSize];
        if(drawPile.size < handSize && discardPile.size > 0) {
            drawPile.addAll(discardPile);
            discardPile.clear();
        }
        if(isRandom) GroupHandler.SkillGroup.staticShuffle(drawPile, publicRandom);
        int ts = drawPile.size;
        for(int i = 0; i < handSize; i++) {
            if(i < ts) {
                AbstractSkill s = drawPile.get(0);
                hand[i] = s;
                drawPile.removeIndex(0);
            } else break;
        }
    }

    public enum EnemyType {
        WEAK, NORMAL, ELITE, BOSS
    }
}
