package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.interfaces.EventCallback;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ActionHandler {

  private static final Queue<AbstractAction> actionList = new Queue<>();
  @Getter private static final Array<EventCallback<AbstractAction>> listeners = new Array<>();
  @Getter private static boolean isRunning = false;
  /***
   * Instance of handler.
   * Initialized on getInstance()
   */
  private static ActionHandler instance;

  private AbstractAction current;

  /***
   * Returns instance of handler, if not exist, create new.
   * @return instance of handler
   */
  public static ActionHandler getInstance() {
    if (instance == null) return (instance = new ActionHandler());
    return instance;
  }

  public static void clear() {
    actionList.clear();
  }

  public static void bot(AbstractAction action) {
    actionList.addLast(action);
  }

  public static void top(AbstractAction action) {
    actionList.addFirst(action);
  }

  public void update() {
    if (actionList.size > 0 || current != null) {
      isRunning = true;
      if (current == null) {
        current = actionList.removeFirst();
      }
      if (!Labyrintale.fading) {
        current.update();
      }
      if (current.isDone) {
        current = null;
      }
    } else isRunning = false;
  }

  public void render(SpriteBatch sb) {}
}
