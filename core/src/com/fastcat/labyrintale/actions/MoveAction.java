package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;

public class MoveAction extends AbstractAction {

    private final boolean isLeft;
    private final float distance;
    private final AbstractPlayer from;
    private final AbstractPlayer toPlayer;
    private int index0 = 0;
    private int index1 = 0;

    public MoveAction(AbstractPlayer p, boolean isLeft) {
        super(p, 0.5f);
        from = p;
        this.isLeft = isLeft;
        distance = Gdx.graphics.getWidth() * 0.095f;
        for(int i = 0; i < 4; i++) {
            AbstractPlayer t = AbstractLabyrinth.players[i];
            if(t == from) {
                index0 = i;
                if(this.isLeft) index1 = i + 1;
                else index1 = i - 1;
            }
        }
        toPlayer = AbstractLabyrinth.players[index1];
    }

    @Override
    protected void updateAction() {
        if(isLeft) {
            from.animX -= distance * Gdx.graphics.getDeltaTime() * 2;
            toPlayer.animX += distance * Gdx.graphics.getDeltaTime() * 2;
        } else {
            from.animX += distance * Gdx.graphics.getDeltaTime() * 2;
            toPlayer.animX -= distance * Gdx.graphics.getDeltaTime() * 2;
        }
        if(isDone) {
            float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
            from.setAnimXY(w * 0.425f - w * 0.1f * index1, h * 0.525f);
            toPlayer.setAnimXY(w * 0.425f - w * 0.1f * index0, h * 0.525f);
            AbstractPlayer temp = AbstractLabyrinth.players[index0];
            AbstractLabyrinth.players[index0] = AbstractLabyrinth.players[index1];
            AbstractLabyrinth.players[index1] = temp;
            Labyrintale.battleScreen.players[index0].player = AbstractLabyrinth.players[index0];
            Labyrintale.battleScreen.players[index0].player.ui = Labyrintale.battleScreen.players[index0];
            Labyrintale.battleScreen.players[index1].player = temp;
            Labyrintale.battleScreen.players[index1].player.ui = Labyrintale.battleScreen.players[index1];
            cPanel.battlePanel.setPlayer(temp);
        }
    }
}
