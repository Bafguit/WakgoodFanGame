package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;

import java.util.HashMap;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;

public class MoveAction extends AbstractAction {

    private final AbstractEntity from;
    private final HashMap<Integer, AbstractEntity> to = new HashMap<>();
    private final HashMap<Integer, Float> distance = new HashMap<>();
    private final HashMap<Integer, Float> position = new HashMap<>();
    private final MoveType type;

    private boolean run = true;
    private boolean isLeft;
    private int toIndex;
    private float toDist;
    private float toPos;

    public MoveAction(AbstractPlayer p, boolean isLeft) {
        this(p, isLeft, 0.5f);
    }

    public MoveAction(AbstractPlayer p, boolean isLeft, float dur) {
        this(p, isLeft ? p.tempIndex + 1 : p.tempIndex - 1, dur);
    }

    public MoveAction(AbstractPlayer p, int index) {
        this(p, index, 0.5f);
    }

    public MoveAction(AbstractPlayer p, int index, float dur) {
        super(null, dur);
        from = p;
        type = MoveType.PLAYER;
        toIndex = index;
    }

    public MoveAction(AbstractEnemy e, boolean isLeft) {
        this(e, isLeft, 0.5f);
    }

    public MoveAction(AbstractEnemy e, boolean isLeft, float dur) {
        this(e, isLeft ? e.tempIndex - 1 : e.tempIndex + 1, dur);
    }

    public MoveAction(AbstractEnemy e, int index) {
        this(e, index, 0.5f);
    }

    public MoveAction(AbstractEnemy e, int index, float dur) {
        super(null, dur);
        from = e;
        type = MoveType.ENEMY;
        toIndex = index;
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            if(toIndex < 0 || toIndex > 3) {
                isDone = true;
                run = false;
            } else {
                if(type == MoveType.PLAYER) {
                    isLeft = from.tempIndex < toIndex;
                    if (isLeft) {
                        while (!AbstractLabyrinth.players[toIndex].isAlive() && toIndex > from.tempIndex) {
                            toIndex--;
                        }
                    } else {
                        while (!AbstractLabyrinth.players[toIndex].isAlive() && toIndex < from.tempIndex) {
                            toIndex++;
                        }
                    }
                    int i = Math.abs(from.tempIndex - toIndex);
                    if (i == 0) {
                        isDone = true;
                        run = false;
                    } else {
                        float w = Gdx.graphics.getWidth(), d = w * (isLeft ? 0.1f : -0.1f);
                        toDist = -d * i;
                        toPos = w * 0.425f - w * 0.1f * toIndex;
                        if (isLeft) {
                            for (int j = from.tempIndex; j < toIndex; j++) {
                                to.put(j, AbstractLabyrinth.players[j + 1]);
                                distance.put(j, d);
                                position.put(j, w * 0.425f - w * 0.1f * j);
                            }
                        } else {
                            for (int j = from.tempIndex; j > toIndex; j--) {
                                to.put(j, AbstractLabyrinth.players[j - 1]);
                                distance.put(j, d);
                                position.put(j, w * 0.425f - w * 0.1f * j);
                            }
                        }
                    }
                } else {
                    isLeft = from.tempIndex > toIndex;
                    if (isLeft) {
                        while (!currentFloor.currentRoom.enemies[toIndex].isAlive() && toIndex < from.tempIndex) {
                            toIndex++;
                        }
                    } else {
                        while (!currentFloor.currentRoom.enemies[toIndex].isAlive() && toIndex > from.tempIndex) {
                            toIndex--;
                        }
                    }
                    int i = Math.abs(from.tempIndex - toIndex);
                    if (i == 0) {
                        isDone = true;
                        run = false;
                    } else {
                        float w = Gdx.graphics.getWidth(), d = w * (isLeft ? 0.1f : -0.1f);
                        toDist = -d * i;
                        toPos = w * 0.575f + w * 0.1f * toIndex;
                        if (!isLeft) {
                            for (int j = from.tempIndex; j < toIndex; j++) {
                                to.put(j, currentFloor.currentRoom.enemies[j + 1]);
                                distance.put(j, d);
                                position.put(j, w * 0.575f + w * 0.1f * j);
                            }
                        } else {
                            for (int j = from.tempIndex; j > toIndex; j--) {
                                to.put(j, currentFloor.currentRoom.enemies[j - 1]);
                                distance.put(j, d);
                                position.put(j, w * 0.575f + w * 0.1f * j);
                            }
                        }
                    }
                }
            }
        }
        if(run) {
            if (isDone) {
                if (type == MoveType.PLAYER) {
                    for (Integer i : to.keySet()) {
                        AbstractEntity e = to.get(i);
                        e.tempIndex = i;
                        e.animX = position.get(i);
                        AbstractLabyrinth.players[i] = (AbstractPlayer) e;
                        battleScreen.players[i].player = (AbstractPlayer) e;
                    }
                    from.tempIndex = toIndex;
                    from.animX = toPos;
                    AbstractLabyrinth.players[toIndex] = (AbstractPlayer) from;
                    battleScreen.players[toIndex].player = (AbstractPlayer) from;
                    for (PlayerView pv : battleScreen.players) {
                        pv.player.ui = pv;
                    }
                    cPanel.battlePanel.setPlayer((AbstractPlayer) from);
                } else {
                    for (Integer i : to.keySet()) {
                        AbstractEntity e = to.get(i);
                        e.tempIndex = i;
                        e.animX = position.get(i);
                        currentFloor.currentRoom.enemies[i] = (AbstractEnemy) e;
                        battleScreen.setEnemy((AbstractEnemy) e, i);
                    }
                    from.tempIndex = toIndex;
                    from.animX = toPos;
                    currentFloor.currentRoom.enemies[toIndex] = (AbstractEnemy) from;
                    battleScreen.enemies[toIndex].enemy = (AbstractEnemy) from;
                    for (EnemyView pv : battleScreen.enemies) {
                        pv.enemy.ui = pv;
                    }
                }
            } else {
                float t = Labyrintale.tick * (1.0f / baseDuration);
                from.animX += toDist * t;
                if(isLeft && from.animX < toPos || !isLeft && from.animX > toPos) {
                    from.animX = toPos;
                }
                for (Integer i : to.keySet()) {
                    AbstractEntity e = to.get(i);
                    float dist = distance.get(i), max = position.get(i);
                    e.animX += dist * t;
                    if(isLeft && e.animX > max || !isLeft && e.animX < max) {
                        e.animX = max;
                    }
                }
            }
        }
    }

    public enum MoveType {
        PLAYER, ENEMY
    }
}
