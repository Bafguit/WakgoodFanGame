package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.BusinessSkill;
import com.fastcat.labyrintale.skills.player.advisor.CarnarSkill;

public class CarnarYungter extends AbstractAdvisor {

    public CarnarYungter() {
        super(AdvisorClass.CARNAR, new CarnarSkill());
    }
}
