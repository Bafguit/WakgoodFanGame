package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.SophiaSkill;

public class Sophia extends AbstractAdvisor {

    public Sophia() {
        super(AdvisorClass.SOPHIA, new SophiaSkill());
    }
}
