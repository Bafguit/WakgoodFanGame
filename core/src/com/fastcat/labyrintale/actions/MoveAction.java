package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;

public class MoveAction extends AbstractAction {

    private final boolean isLeft;
    private final float distance;
    private final AbstractEntity from;
    private final AbstractEntity to;
    private final MoveType type;
    private int index0 = 0;
    private int index1 = 0;

    public MoveAction(AbstractPlayer p, boolean isLeft) {
        super(p, 0.5f);
        from = p;
        type = MoveType.PLAYER;
        this.isLeft = isLeft;
        distance = Gdx.graphics.getWidth() * 0.095f;
        AbstractPlayer[] temp = AbstractLabyrinth.players;
        for(int i = 0; i < 4; i++) {
            AbstractPlayer t = temp[i];
            if(t == from) {
                index0 = i;
                if(this.isLeft) index1 = i + 1;
                else index1 = i - 1;
            }
        }
        to = temp[index1];
    }

    public MoveAction(AbstractEnemy e, boolean isLeft) {
        super(e, 0.5f);
        from = e;
        type = MoveType.ENEMY;
        this.isLeft = isLeft;
        distance = Gdx.graphics.getWidth() * 0.095f;
        AbstractEnemy[] temp = AbstractLabyrinth.currentFloor.currentRoom.enemies;
        for(int i = 0; i < 4; i++) {
            AbstractEnemy t = temp[i];
            if(t == from) {
                index0 = i;
                if(this.isLeft) index1 = i - 1;
                else index1 = i + 1;
            }
        }
        to = temp[index1];
    }

    @Override
    protected void updateAction() {
        if(isLeft) {
            from.animX -= distance * Gdx.graphics.getDeltaTime() * 2;
            to.animX += distance * Gdx.graphics.getDeltaTime() * 2;
        } else {
            from.animX += distance * Gdx.graphics.getDeltaTime() * 2;
            to.animX -= distance * Gdx.graphics.getDeltaTime() * 2;
        }
        if(isDone) {
            float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
            if(type == MoveType.PLAYER) {
                from.defineIndex(index1);
                to.defineIndex(index0);
                from.setAnimXY(w * 0.425f - w * 0.1f * index1, h * 0.575f);
                to.setAnimXY(w * 0.425f - w * 0.1f * index0, h * 0.575f);
                AbstractPlayer temp = AbstractLabyrinth.players[index0];
                AbstractLabyrinth.players[index0] = AbstractLabyrinth.players[index1];
                AbstractLabyrinth.players[index1] = temp;
                Labyrintale.battleScreen.players[index0].player = AbstractLabyrinth.players[index0];
                Labyrintale.battleScreen.players[index0].player.ui = Labyrintale.battleScreen.players[index0];
                Labyrintale.battleScreen.players[index1].player = temp;
                Labyrintale.battleScreen.players[index1].player.ui = Labyrintale.battleScreen.players[index1];
                cPanel.battlePanel.setPlayer(temp);
            } else {
                from.defineIndex(index1);
                to.defineIndex(index0);
                from.setAnimXY(w * 0.575f + w * 0.1f * index1, h * 0.575f);
                to.setAnimXY(w * 0.575f + w * 0.1f * index0, h * 0.575f);
                AbstractEnemy temp = AbstractLabyrinth.currentFloor.currentRoom.enemies[index0];
                AbstractLabyrinth.currentFloor.currentRoom.enemies[index0] = AbstractLabyrinth.currentFloor.currentRoom.enemies[index1];
                AbstractLabyrinth.currentFloor.currentRoom.enemies[index1] = temp;
                Labyrintale.battleScreen.enemies[index0].enemy = AbstractLabyrinth.currentFloor.currentRoom.enemies[index0];
                Labyrintale.battleScreen.enemies[index0].enemy.ui = Labyrintale.battleScreen.enemies[index0];
                Labyrintale.battleScreen.enemies[index1].enemy = temp;
                Labyrintale.battleScreen.enemies[index1].enemy.ui = Labyrintale.battleScreen.enemies[index1];
            }
        }
    }

    public enum MoveType {
        PLAYER, ENEMY
    }
}
