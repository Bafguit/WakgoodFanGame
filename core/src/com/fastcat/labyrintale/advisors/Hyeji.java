package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.JkSkill;
import com.fastcat.labyrintale.skills.player.advisor.PungSkill;

public class Hyeji extends AbstractAdvisor {

    public Hyeji() {
        super(AdvisorClass.JK, new JkSkill());
    }
}
