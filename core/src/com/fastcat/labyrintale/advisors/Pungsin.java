package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.PungSkill;

public class Pungsin extends AbstractAdvisor {

    public Pungsin() {
        super(AdvisorClass.PUNG, new PungSkill());
    }
}
