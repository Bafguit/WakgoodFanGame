package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.items.starter.PlaceHolder;
import com.fastcat.labyrintale.skills.enemy.MoveLeftE;
import com.fastcat.labyrintale.skills.enemy.MoveRightE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;
import com.fastcat.labyrintale.strings.CharString;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.publicRandom;

public abstract class AbstractEnemy extends AbstractEntity {

    public EnemyType type;
    public Array<AbstractSkill> drawPile;
    public Array<AbstractSkill> discardPile;
    public Array<AbstractSkill> disposablePile;
    public boolean isRandom = true;
    public boolean hasChange = false;

    public AbstractEnemy(String id, EnemyType type, int maxHealth) {
        super(id, 1, maxHealth, FileHandler.getAtlas().get(id), FileHandler.getSkeleton().get(id), false);
        this.type = type;
        CharString.CharData temp = StringHandler.enemyString.get(id);
        name = temp.NAME;
        desc = temp.DESC;
        drawPile = new Array<>();
        discardPile = new Array<>();
        disposablePile = new Array<>();
        stat.debuRes = 5;
        stat.neutRes = 5;
        stat.critical = 5;
        stat.moveRes = 5;
        stat.multiply = 50;
        img = imgBig = FileHandler.getUi().get("E_IMAGE");
        imgPanel = FileHandler.getUi().get("E_PANEL");
    }

    @Override
    public void newDeck() {
        hand = new AbstractSkill[handSize];
        drawPile.clear();
        discardPile.clear();
        disposablePile.clear();
        drawPile = new Array<>(deck);
    }

    public final void shuffleHand() {
        for (int i = 0; i < handSize; i++) {
            AbstractSkill s = hand[i];
            if (s != null) {
                if (s.usedOnce) disposablePile.add(hand[i]);
                else discardPile.add(hand[i]);
            }
        }
        hand = new AbstractSkill[handSize];
        if (drawPile.size < handSize && discardPile.size > 0) {
            drawPile.addAll(discardPile);
            discardPile.clear();
        }
        if (isRandom) GroupHandler.SkillGroup.staticShuffle(drawPile, publicRandom);
        if(hasChange) {
            hand[0] = makeHand();
        } else {
            for (int i = 0; i < handSize; i++) {
                if (i < drawPile.size) {
                    AbstractSkill s = drawPile.removeIndex(0);
                    hand[i] = s;
                } else break;
            }
        }
    }

    protected AbstractSkill makeHand() {
        return new StrikeE(this);
    }

    public enum EnemyType {
        WEAK, NORMAL, ELITE, BOSS
    }
}
