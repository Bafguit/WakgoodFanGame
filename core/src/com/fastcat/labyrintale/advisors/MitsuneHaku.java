package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.HakuSkill;

public class MitsuneHaku extends AbstractAdvisor {

    public MitsuneHaku() {
        super(AdvisorClass.HAKU, new HakuSkill());
    }
}
