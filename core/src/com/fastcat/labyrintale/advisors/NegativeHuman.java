package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.JkSkill;
import com.fastcat.labyrintale.skills.player.advisor.NegativeSkill;

public class NegativeHuman extends AbstractAdvisor {

    public NegativeHuman() {
        super(AdvisorClass.NEGATIVE, new NegativeSkill());
    }
}
