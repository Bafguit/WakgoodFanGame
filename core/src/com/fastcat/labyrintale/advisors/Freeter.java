package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.DuksuSkill;
import com.fastcat.labyrintale.skills.player.advisor.FreeterSkill;

public class Freeter extends AbstractAdvisor {

    public Freeter() {
        super(AdvisorClass.FREETER, new FreeterSkill());
    }
}
