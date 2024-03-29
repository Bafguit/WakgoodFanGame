package com.fastcat.labyrintale.actions;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.*;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.UpTextImgEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.battle.EnemyBattleView;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;
import java.util.HashMap;

public class MoveAction extends AbstractAction {

    private final HashMap<Integer, AbstractEntity> to = new HashMap<>();
    private final HashMap<Integer, Float> distance = new HashMap<>();
    private final HashMap<Integer, Float> position = new HashMap<>();
    private final MoveType fromType;
    private AbstractEntity from;
    private final AbstractEntity source;
    private MoveType type;

    private boolean alive;
    private boolean run = true;
    private boolean isLeft;
    private int toIndex;
    private float toDist;
    private float toPos;

    public MoveAction(AbstractEntity e, AbstractEntity source, boolean isLeft) {
        this(e, source, isLeft, 0.5f);
    }

    public MoveAction(AbstractEntity e, AbstractEntity source, boolean isLeft, float dur) {
        this(e, source, e.isPlayer ? (isLeft ? e.index + 1 : e.index - 1) : (isLeft ? e.index - 1 : e.index + 1), dur);
    }

    public MoveAction(AbstractEntity e, AbstractEntity source, int index) {
        this(e, source, index, 0.5f);
    }

    public MoveAction(AbstractEntity e, AbstractEntity source, int index, float dur) {
        super(null, dur);
        this.source = source;
        from = e;
        fromType = source.isPlayer ? MoveType.PLAYER : MoveType.ENEMY;
        type = e.isPlayer ? MoveType.PLAYER : MoveType.ENEMY;
        toIndex = index;
        alive = e.isAlive();
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (preAction != null) {
                from = preAction.target.get(0);
                type = from.isPlayer ? MoveType.PLAYER : MoveType.ENEMY;
                alive = from.isAlive();
            }
            if (toIndex < 0
                    || toIndex > 3
                    || (alive && from.movable > 0)
                    || alive != from.isAlive()
                    || from.index == toIndex) {
                isDone = true;
                run = false;
            } else if (fromType != type) {
                int a = publicRandom.random(0, 99);
                if (from.badLuck > 1) a = Math.max(a, publicRandom.random(0, 99));
                if (from.goodLuck > 1) a = Math.min(a, publicRandom.random(0, 99));
                if (a < AbstractEntity.EntityStat.cap(from.stat.moveRes)) {
                    EffectHandler.add(new UpTextImgEffect(
                            from.ui.x + from.ui.sWidth / 2,
                            from.ui.y + from.ui.sHeight * 0.5f,
                            FileHandler.getUi().get("TEXT_MOVE")));
                    isDone = true;
                    run = false;
                }
            }
            if (run) {
                if (type == MoveType.PLAYER) {
                    isLeft = from.index < toIndex;
                    if (isLeft) {
                        while ((!AbstractLabyrinth.players[toIndex].isAlive()
                                        || AbstractLabyrinth.players[toIndex].movable > 0)
                                && toIndex > from.index) {
                            toIndex--;
                        }
                    } else {
                        while ((!AbstractLabyrinth.players[toIndex].isAlive()
                                        || AbstractLabyrinth.players[toIndex].movable > 0)
                                && toIndex < from.index) {
                            toIndex++;
                        }
                    }
                    int i = Math.abs(from.index - toIndex);
                    if (i == 0) {
                        isDone = true;
                        run = false;
                    } else {
                        float w = Gdx.graphics.getWidth(), d = w * (isLeft ? 0.1f : -0.1f);
                        toDist = -d * i;
                        toPos = w * 0.425f - w * 0.1f * toIndex;
                        int v = 0;
                        if (isLeft) {
                            for (int j = from.index; j < toIndex; j++) {
                                AbstractPlayer p = AbstractLabyrinth.players[j + 1];
                                if (p.movable > 0) {
                                    v--;
                                } else {
                                    int jv = j + v;
                                    to.put(jv, p);
                                    distance.put(jv, d);
                                    position.put(jv, w * 0.425f - w * 0.1f * jv);
                                    if (v != 0) v++;
                                }
                            }
                        } else {
                            for (int j = from.index; j > toIndex; j--) {
                                AbstractPlayer p = AbstractLabyrinth.players[j - 1];
                                if (p.movable > 0) {
                                    v++;
                                } else {
                                    int jv = j + v;
                                    to.put(jv, p);
                                    distance.put(jv, d);
                                    position.put(jv, w * 0.425f - w * 0.1f * jv);
                                    if (v != 0) v--;
                                }
                            }
                        }
                    }
                } else {
                    isLeft = from.index > toIndex;
                    if (isLeft) {
                        while ((!currentFloor.currentRoom.enemies[toIndex].isAlive()
                                        || currentFloor.currentRoom.enemies[toIndex].movable > 0)
                                && toIndex < from.index) {
                            toIndex++;
                        }
                    } else {
                        while ((!currentFloor.currentRoom.enemies[toIndex].isAlive()
                                        || currentFloor.currentRoom.enemies[toIndex].movable > 0)
                                && toIndex > from.index) {
                            toIndex--;
                        }
                    }
                    int i = Math.abs(from.index - toIndex);
                    if (i == 0) {
                        isDone = true;
                        run = false;
                    } else {
                        float w = Gdx.graphics.getWidth(), d = w * (isLeft ? 0.1f : -0.1f);
                        toDist = -d * i;
                        toPos = w * 0.575f + w * 0.1f * toIndex;
                        int v = 0;
                        if (!isLeft) {
                            for (int j = from.index; j < toIndex; j++) {
                                AbstractEnemy e = currentFloor.currentRoom.enemies[j + 1];
                                if (e.movable > 0) {
                                    v--;
                                } else {
                                    int jv = j + v;
                                    to.put(jv, e);
                                    distance.put(jv, d);
                                    position.put(jv, w * 0.575f + w * 0.1f * jv);
                                    if (v != 0) v++;
                                }
                            }
                        } else {
                            for (int j = from.index; j > toIndex; j--) {
                                AbstractEnemy e = currentFloor.currentRoom.enemies[j - 1];
                                if (e.movable > 0) {
                                    v++;
                                } else {
                                    int jv = j + v;
                                    to.put(jv, e);
                                    distance.put(jv, d);
                                    position.put(jv, w * 0.575f + w * 0.1f * jv);
                                    if (v != 0) v--;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (run) {
            if (isDone) {
                if (type == MoveType.PLAYER) {
                    for (Integer i : to.keySet()) {
                        AbstractEntity e = to.get(i);
                        e.index = i;
                        e.animX = position.get(i);
                        AbstractLabyrinth.players[i] = (AbstractPlayer) e;
                        battleScreen.players[i].entity = e;
                    }
                    from.index = toIndex;
                    from.animX = toPos;
                    AbstractLabyrinth.players[toIndex] = (AbstractPlayer) from;
                    battleScreen.players[toIndex].entity = from;
                    for (PlayerBattleView pv : battleScreen.players) {
                        pv.entity.ui = pv;
                    }
                    cPanel.battlePanel.setEntity(from);
                } else {
                    for (Integer i : to.keySet()) {
                        AbstractEntity e = to.get(i);
                        e.index = i;
                        e.animX = position.get(i);
                        currentFloor.currentRoom.enemies[i] = (AbstractEnemy) e;
                        battleScreen.setEnemy(e, i);
                    }
                    from.index = toIndex;
                    from.animX = toPos;
                    currentFloor.currentRoom.enemies[toIndex] = (AbstractEnemy) from;
                    battleScreen.enemies[toIndex].entity = from;
                    for (EnemyBattleView pv : battleScreen.enemies) {
                        pv.entity.ui = pv;
                    }
                }
                for (int i = 0; i < 2; i++) {
                    AbstractItem t = from.item[i];
                    if (t != null) t.onMove(source);
                    for (AbstractEntity e : to.values()) {
                        t = e.item[i];
                        if (t != null) t.onMove(source);
                    }
                }
                if (from.passive != null) from.passive.onMove(source);
                for (AbstractEntity e : to.values()) {
                    if (e.passive != null) e.passive.onMove(source);
                }
            } else {
                float t = Labyrintale.tick * (1.0f / baseDuration);
                from.animX += toDist * t;
                if (isLeft && from.animX < toPos || !isLeft && from.animX > toPos) {
                    from.animX = toPos;
                }
                for (Integer i : to.keySet()) {
                    AbstractEntity e = to.get(i);
                    float dist = distance.get(i), max = position.get(i);
                    e.animX += dist * t;
                    if (isLeft && e.animX > max || !isLeft && e.animX < max) {
                        e.animX = max;
                    }
                }
            }
        }
    }

    public enum MoveType {
        PLAYER,
        ENEMY
    }
}
