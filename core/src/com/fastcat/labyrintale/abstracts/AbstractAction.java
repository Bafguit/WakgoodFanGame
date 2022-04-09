package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractAction implements Cloneable {

    protected static final float DUR_DEFAULT = 1.0f;
    protected static final float DUR_FAST = 0.5f;

    public AbstractEntity actor;
    public Array<AbstractEntity> target;
    public boolean isDone = false;
    public float baseDuration = DUR_DEFAULT;
    public float duration = DUR_DEFAULT;

    public AbstractAction(AbstractEntity actor, float duration) {
        this.actor = actor;
        this.duration = duration;
        baseDuration = this.duration;
    }

    public AbstractAction(AbstractEntity actor, AbstractSkill.SkillTarget target, float duration) {
        this.actor = actor;
        if(target == AbstractSkill.SkillTarget.SELF) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(actor);
            this.target = temp;
        } else if(target == AbstractSkill.SkillTarget.S_R) {
            Array<AbstractEntity> temp = new Array<>();
            if(actor instanceof AbstractPlayer) {
                if(actor.tempIndex > 0) temp.add(AbstractLabyrinth.players[actor.tempIndex - 1]);
            } else {
                if(actor.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[actor.tempIndex + 1]);
            }
            this.target = temp;
        } else if(target == AbstractSkill.SkillTarget.S_L) {
            Array<AbstractEntity> temp = new Array<>();
            if(actor instanceof AbstractPlayer) {
                if(actor.tempIndex < 3) temp.add(AbstractLabyrinth.players[actor.tempIndex + 1]);
            } else {
                if(actor.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[actor.tempIndex - 1]);
            }
            this.target = temp;
        } else if(target == AbstractSkill.SkillTarget.S_B) {
            Array<AbstractEntity> temp = new Array<>();
            if(actor instanceof AbstractPlayer) {
                if(actor.tempIndex > 0) temp.add(AbstractLabyrinth.players[actor.tempIndex - 1]);
                if(actor.tempIndex < 3) temp.add(AbstractLabyrinth.players[actor.tempIndex + 1]);
            } else {
                if(actor.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[actor.tempIndex + 1]);
                if(actor.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[actor.tempIndex - 1]);
            }
            this.target = temp;
        } else if(target == AbstractSkill.SkillTarget.SS_R) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(actor);
            if(actor instanceof AbstractPlayer) {
                if(actor.tempIndex > 0) temp.add(AbstractLabyrinth.players[actor.tempIndex - 1]);
            } else {
                if(actor.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[actor.tempIndex + 1]);
            }
            this.target = temp;
        } else if(target == AbstractSkill.SkillTarget.SS_L) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(actor);
            if(actor instanceof AbstractPlayer) {
                if(actor.tempIndex < 3) temp.add(AbstractLabyrinth.players[actor.tempIndex + 1]);
            } else {
                if(actor.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[actor.tempIndex - 1]);
            }
            this.target = temp;
        } else if(target == AbstractSkill.SkillTarget.SS_B) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(actor);
            if(actor instanceof AbstractPlayer) {
                if(actor.tempIndex > 0) temp.add(AbstractLabyrinth.players[actor.tempIndex - 1]);
                if(actor.tempIndex < 3) temp.add(AbstractLabyrinth.players[actor.tempIndex + 1]);
            } else {
                if(actor.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[actor.tempIndex + 1]);
                if(actor.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[actor.tempIndex - 1]);
            }
            this.target = temp;
        } else this.target = AbstractSkill.getTargets(target);
        this.duration = duration;
        baseDuration = this.duration;
    }

    public final void update() {
        if(!isDone) {
            if(actor != null && !actor.isAlive()) {
                isDone = true;
                return;
            } else if (duration <= 0) {
                isDone = true;
            }
            updateAction();
            TickDuration();
        }
    }

    protected abstract void updateAction();

    protected void TickDuration() {
        if (duration > 0) {
            duration -= Gdx.graphics.getDeltaTime();
        }
    }
}
