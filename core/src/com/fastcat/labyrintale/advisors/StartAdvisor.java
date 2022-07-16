package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.StartAdvisorSkill;

public class StartAdvisor extends AbstractAdvisor {

    public StartAdvisor() {
        super(AdvisorClass.START, new StartAdvisorSkill());
    }
}
