package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.SecretSkill;

public class SecretGirl extends AbstractAdvisor {

    public SecretGirl() {
        super(AdvisorClass.SECRET, new SecretSkill());
    }
}
