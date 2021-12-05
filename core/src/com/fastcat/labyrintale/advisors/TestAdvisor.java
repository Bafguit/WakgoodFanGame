package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.Temp;
import com.fastcat.labyrintale.skills.player.Strike;

public class TestAdvisor extends AbstractAdvisor {

    public TestAdvisor() {
        super(AdvisorClass.BURGER, new Temp());
    }
}
