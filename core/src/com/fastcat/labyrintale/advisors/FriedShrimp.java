package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.HakuSkill;
import com.fastcat.labyrintale.skills.player.advisor.ShrimpSkill;

public class FriedShrimp extends AbstractAdvisor {

    public FriedShrimp() {
        super(AdvisorClass.SHRIMP, new ShrimpSkill());
    }
}
