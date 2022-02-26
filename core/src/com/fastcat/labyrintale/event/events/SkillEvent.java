package com.fastcat.labyrintale.event.events;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.event.Event;

import java.util.List;

public abstract class SkillEvent extends Event {
    public abstract AbstractSkill getSkill();

    public abstract List<AbstractEntity> getTargets();
}
