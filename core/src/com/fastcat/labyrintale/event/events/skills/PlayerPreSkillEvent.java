package com.fastcat.labyrintale.event.events.skills;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.event.HandlerList;
import com.fastcat.labyrintale.event.events.SkillEvent;

import java.util.List;

public class PlayerPreSkillEvent extends SkillEvent {
    private static final HandlerList handlers = new HandlerList();
    private final AbstractPlayer player;
    private final AbstractSkill skill;
    private List<AbstractEntity> targetsBefore;
    private final List<AbstractEntity> targets;

    public PlayerPreSkillEvent(AbstractPlayer player, AbstractSkill skill, List<AbstractEntity> targets) {
        this.player = player;
        this.skill = skill;
        this.targets = targets;
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

    @Override
    public HandlerList getHandlerList() {
        return handlers;
    }
}
