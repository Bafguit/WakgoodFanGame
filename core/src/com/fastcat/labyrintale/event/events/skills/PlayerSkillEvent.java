package com.fastcat.labyrintale.event.events.skills;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.event.HandlerList;
import com.fastcat.labyrintale.event.events.SkillEvent;
import com.fastcat.labyrintale.misc.SimpleEntity;

import java.util.List;

public class PlayerSkillEvent extends SkillEvent {

    private static final HandlerList handlers = new HandlerList();
    private final AbstractPlayer player;
    private final AbstractSkill skill;
    private final List<SimpleEntity> targetsBefore;
    private final List<AbstractEntity> targets;

    public PlayerSkillEvent(AbstractPlayer player, AbstractSkill skill, List<AbstractEntity> targets, List<SimpleEntity> targetsBefore) {
        this.player = player;
        this.skill = skill;
        this.targets = targets;
        this.targetsBefore = targetsBefore;
    }

    public static HandlerList getHandlers() {
        return handlers;
    }

    public AbstractPlayer getPlayer() {
        return player;
    }

    @Override
    public AbstractSkill getSkill() {
        return skill;
    }

    @Override
    public List<AbstractEntity> getTargets() {
        return targets;
    }

    public List<SimpleEntity> getTargetsBefore() {
        return targetsBefore;
    }

    @Override
    public HandlerList getHandlerList() {
        return handlers;
    }
}
