package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class MoveAction extends AbstractAction {

    private final boolean isLeft;
    private final float distance;
    private final AbstractEntity from;
    private final AbstractEntity to;
    private final MoveType type;
    private int index0 = 0;
    private int index1 = 0;

    public MoveAction(AbstractPlayer p, boolean isLeft) {
        this(p, isLeft, 0.5f);
    }

    public MoveAction(AbstractPlayer p, boolean isLeft, float dur) {
        super(p, dur);
        from = p;
        type = MoveType.PLAYER;
        this.isLeft = isLeft;
        distance = Gdx.graphics.getWidth() * 0.095f;
        index0 = from.tempIndex;
        if(this.isLeft) index1 = from.tempIndex + 1;
        else index1 = from.tempIndex - 1;
        to = AbstractLabyrinth.players[index1];
    }

    public MoveAction(AbstractEnemy e, boolean isLeft) {
        this(e, isLeft, 0.5f);
    }

    public MoveAction(AbstractEnemy e, boolean isLeft, float dur) {
        super(e, dur);
        from = e;
        type = MoveType.ENEMY;
        this.isLeft = isLeft;
        distance = Gdx.graphics.getWidth() * 0.095f;
        index0 = from.tempIndex;
        if(this.isLeft) index1 = from.tempIndex - 1;
        else index1 = from.tempIndex + 1;
        to = currentFloor.currentRoom.enemies[index1];
    }

    @Override
    protected void updateAction() {
        if(isLeft) {
            from.animX -= distance * Gdx.graphics.getDeltaTime() * (1 / baseDuration);
            to.animX += distance * Gdx.graphics.getDeltaTime() * (1 / baseDuration);
        } else {
            from.animX += distance * Gdx.graphics.getDeltaTime() * (1 / baseDuration);
            to.animX -= distance * Gdx.graphics.getDeltaTime() * (1 / baseDuration);
        }
        if(isDone) {
            float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
            if(type == MoveType.PLAYER) {
                from.tempIndex = index1;
                to.tempIndex = index0;
                from.setAnimXY(w * 0.425f - w * 0.1f * index1, h * 0.575f);
                to.setAnimXY(w * 0.425f - w * 0.1f * index0, h * 0.575f);
                AbstractLabyrinth.players[index0] = (AbstractPlayer) to;
                AbstractLabyrinth.players[index1] = (AbstractPlayer) from;
                battleScreen.players[index0].player = (AbstractPlayer) to;
                battleScreen.players[index0].player.ui = battleScreen.players[index0];
                battleScreen.players[index1].player = (AbstractPlayer) from;
                battleScreen.players[index1].player.ui = battleScreen.players[index1];
                cPanel.battlePanel.setPlayer((AbstractPlayer) from);
            } else {
                from.tempIndex = index1;
                to.tempIndex = index0;
                from.setAnimXY(w * 0.575f + w * 0.1f * index1, h * 0.575f);
                to.setAnimXY(w * 0.575f + w * 0.1f * index0, h * 0.575f);
                currentFloor.currentRoom.enemies[index0] = (AbstractEnemy) to;
                currentFloor.currentRoom.enemies[index1] = (AbstractEnemy) from;
                battleScreen.enemies[index0].enemy = (AbstractEnemy) to;
                battleScreen.enemies[index0].enemy.ui = battleScreen.enemies[index0];
                battleScreen.enemies[index1].enemy = (AbstractEnemy) from;
                battleScreen.enemies[index1].enemy.ui = battleScreen.enemies[index1];
            }
        }
    }

    public enum MoveType {
        PLAYER, ENEMY
    }
}
