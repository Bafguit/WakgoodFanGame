package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.HikiSkill;

public class HikiKing extends AbstractAdvisor {

    public HikiKing() {
        super(AdvisorClass.HIKI, new HikiSkill());
    }
}
