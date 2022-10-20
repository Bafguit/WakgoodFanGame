package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.skills.player.advisor.ManduSkill;

public class KimchiMandu extends AbstractAdvisor {

  public KimchiMandu() {
    super(AdvisorClass.MANDU, new ManduSkill());
  }
}
