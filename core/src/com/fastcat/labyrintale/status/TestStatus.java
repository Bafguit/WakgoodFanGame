package com.fastcat.labyrintale.status;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.handlers.FileHandler;

public class TestStatus extends AbstractStatus {

    private static final String ID = "TestStatus";

    public TestStatus() {
        super(ID, new Sprite(FileHandler.WAK_BASIC));
        name = "애옹";
        desc = "고양이는 귀엽다!";
    }
}
