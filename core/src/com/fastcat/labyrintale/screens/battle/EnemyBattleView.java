package com.fastcat.labyrintale.screens.battle;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class EnemyBattleView extends BattleView {


  public EnemyBattleView() {
    this(null);
  }

  public EnemyBattleView(AbstractEnemy enemy) {
    super(FileHandler.getUi().get("ENTITY_POINT"));
    this.entity = enemy;
    showImg = false;
    overable = false;
    pImg = FileHandler.getUi().get("PLAYER_POINT");
  }

  @Override
  protected void updateButton() {
    if (battleScreen.isSelecting) {
      overable = entity.isAlive() && isTarget;
      showImg = over && overable;
      if (showImg) battleScreen.looking.add(entity);
    } else {
      overable = false;
      AbstractEntity t = battleScreen.currentTurnEntity();
      showImg = isLooking || (t != null && t == entity);
    }
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    setPosition(entity.animX - sWidth / 2, entity.animY - Gdx.graphics.getHeight() * 0.025f);
    if (enabled && entity != null && !entity.isDead) {
      sb.setColor(Color.WHITE);
      if (showImg && battleScreen.cType == ControlPanel.ControlType.BATTLE)
        sb.draw(battleScreen.currentTurnEntity() == entity ? pImg : img, x, y, sWidth, sHeight);
      entity.render(sb);
    }
  }

  @Override
  protected void onClick() {
    battleScreen.gets.onTargetSelected(entity);
  }
}
