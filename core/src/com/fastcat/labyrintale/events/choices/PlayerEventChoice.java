package com.fastcat.labyrintale.events.choices;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.screens.playerselect.PlayerSelectScreen;

public class PlayerEventChoice extends AbstractEvent.EventChoice {

    public GetSelectedPlayer event;
    public SelectType onlyLive = SelectType.ALL;

    public PlayerEventChoice(String t, GetSelectedPlayer callback) {
        super(t);
        event = callback;
    }

    public PlayerEventChoice(String t, GetSelectedPlayer callback, SelectType onlyLive) {
        super(t);
        event = callback;
        this.onlyLive = onlyLive;
    }

    public PlayerEventChoice(
            String t, AbstractEvent.EventCondition condition, GetSelectedPlayer callback, SelectType onlyLive) {
        super(t, condition);
        event = callback;
        this.onlyLive = onlyLive;
    }

    @Override
    protected void onSelect() {
        if (onlyLive == SelectType.LIVE) {
            Array<AbstractPlayer> temp = new Array<>();
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                if (p.isAlive()) temp.add(p);
            }
            if (temp.size > 0) {
                AbstractPlayer[] t = new AbstractPlayer[temp.size];
                for (int i = 0; i < temp.size; i++) {
                    t[i] = temp.get(i);
                }
                Labyrintale.addTempScreen(new PlayerSelectScreen(t, event, null));
            }
        } else if (onlyLive == SelectType.DEAD) {
            Array<AbstractPlayer> temp = new Array<>();
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                if (!p.dummy && !p.isAlive()) temp.add(p);
            }
            if (temp.size > 0) {
                AbstractPlayer[] t = new AbstractPlayer[temp.size];
                for (int i = 0; i < temp.size; i++) {
                    t[i] = temp.get(i);
                }
                Labyrintale.addTempScreen(new PlayerSelectScreen(t, event, null));
            }
        } else Labyrintale.addTempScreen(new PlayerSelectScreen(event));
    }

    public enum SelectType {
        LIVE,
        DEAD,
        ALL
    }
}
