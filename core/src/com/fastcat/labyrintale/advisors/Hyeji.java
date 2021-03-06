package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.skills.player.advisor.JkSkill;

public class Hyeji extends AbstractAdvisor {

    public Hyeji() {
        super(AdvisorClass.JK, new JkSkill());
    }

    @Override
    public void onHire() {
        AbstractLabyrinth.maxSlotUp++;
    }

    @Override
    public void onFire() {
        AbstractLabyrinth.maxSlotUp--;
    }
}
