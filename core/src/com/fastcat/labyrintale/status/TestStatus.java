package com.fastcat.labyrintale.status;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;

public class TestStatus extends AbstractStatus {

    private static final String ID = "TestStatus";

    public TestStatus() {
        super(ID, new Sprite(FileHandler.WAK_BASIC));
        name = "애옹";
        desc = "회복할 때마다 그만큼 모든 적에게 피해를 줍니다.";
    }

    @Override
    public void onHeal(int heal) {
        Array<AbstractEntity> temp = new Array<>();
        for(int i = 0; i < 4; i++) {
            temp.add(Labyrintale.battleScreen.enemies[i].enemy);
        }
        ActionHandler.top(new AttackAction(null, temp, heal, null));
    }
}
