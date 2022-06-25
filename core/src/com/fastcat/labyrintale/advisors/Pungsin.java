package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.PungSkill;
import com.fastcat.labyrintale.skills.player.advisor.SophiaSkill;

public class Pungsin extends AbstractAdvisor {

    public Pungsin() {
        super(AdvisorClass.PUNG, new PungSkill());
    }
}
