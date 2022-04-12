package com.fastcat.labyrintale.status;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.StatusSelfDamageAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.NONE;

public class InfectionStatus extends AbstractStatus {

    private static final String ID = "Infection";

    public InfectionStatus(int amount) {
        super(ID, NONE, StatusType.DEBUFF);
        setAmount(amount);
    }

    @Override
    public String getDesc() {
        return exDesc[0] + amount + exDesc[1];
    }

    @Override
    public void startOfTurn() {
        ActionHandler.top(new StatusSelfDamageAction(this, true));
    }
}
