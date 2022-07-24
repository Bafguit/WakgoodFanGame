package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.DuksuSkill;

public class Duksu extends AbstractAdvisor {

    public Duksu() {
        super(AdvisorClass.DUKSU, new DuksuSkill());
    }
}
