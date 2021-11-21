package com.fastcat.labyrintale.uis;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractImage;
import com.fastcat.labyrintale.handlers.ImageHandler;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;

public class CardPanel extends AbstractImage {

    private float realScale = 0.5f;

    public int index;

    public CardPanel(int index) {
        super(ImageHandler.CARD_PANEL);
        setScale(0.4f);
        this.index = index;
    }

    @Override
    public void updateButton() {
        /*AbstractSkill c = Labyrintale.mainMenuScreen.testCard;
        if(isOverlap(c.ui)) {
            //ggggg
            if(!c.ui.clicking) {
                c.ui.uiScale = this.uiScale;
                c.ui.setStaticPos(x, y);
                c.ui.isStatic = true;
                //currentFloor.currentRoom.cards[index] = c;
            } else {
                c.ui.isStatic = false;
                c.ui.uiScale = realScale;
                //currentFloor.currentRoom.cards[index] = null;
            }
        }
        for(AbstractSkill card : player.hand) {
            if(isOverlap(card.ui)) {
                //ggggg
                if(!card.ui.clicking) {
                    realScale = card.ui.uiScale;
                    card.ui.uiScale = this.uiScale;
                    card.ui.setStaticPos(x, y);
                } else {
                    card.ui.uiScale = realScale;
                }
            }
        }*/
    }
}