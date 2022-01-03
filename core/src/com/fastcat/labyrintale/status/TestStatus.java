package com.fastcat.labyrintale.status;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;

public class TestStatus extends AbstractStatus {

    private static final String ID = "TestStatus";
    private static final AbstractSkill.CardTarget TARGET = AbstractSkill.CardTarget.E_ALL;

    public TestStatus(AbstractEntity o) {
        super(ID, new Sprite(FileHandler.SKILL_POISON), o, TARGET);
        name = "애옹";
        desc = "회복할 때마다 그만큼 모든 적에게 피해를 줍니다.";
        setAmount(3);
    }

    @Override
    public String getDesc() {
        return "회복할 때마다 그만큼 모든 적에게 피해를 줍니다.";
    }

    @Override
    public void onHeal(int heal) {
        ActionHandler.top(new AttackAction(null, TARGET, heal, null));
    }
}
