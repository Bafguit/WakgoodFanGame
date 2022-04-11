package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import com.fastcat.labyrintale.strings.SkillString;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FileHandler.skillImg;
import static com.fastcat.labyrintale.handlers.FontHandler.getHexColor;

public abstract class AbstractSkill implements Cloneable {

    public Sprite img;
    public Sprite imgBig;
    public SkillString.SkillData skillData;
    public AbstractEntity owner;
    public final SkillRarity rarity;
    public final SkillType type;
    public SkillTarget target;
    public AbstractSkill passive;
    public String id;
    public String name;
    public String desc;
    public boolean upgraded = false;
    public boolean removable = true;
    public boolean usedOnce = false;
    public boolean usedOnly = false;

    public boolean disposable = false;
    public boolean isTrick = false;
    public float uid;
    public int upgradeCount = 0;
    public int attack = -1;
    public int baseAttack = -1;
    public int upAttack = -1;
    public int spell = -1;
    public int baseSpell = -1;
    public int upSpell = -1;
    public int value = -1;
    public int baseValue = -1;
    public int upValue = -1;
    public int cooltime = 2;
    public int cooldown = 0;

    public AbstractSkill(AbstractEntity owner, String id, SkillType type, SkillRarity rarity, SkillTarget target) {
        uid = getUid();
        this.owner = owner;
        this.id = id;
        this.img = skillImg.get(this.id);
        this.imgBig = FileHandler.skillImgBig.get(this.id);
        this.skillData = StringHandler.skillString.get(this.id);
        this.name = this.skillData.NAME;
        this.desc = this.skillData.DESC;
        this.type = type;
        this.rarity = rarity;
        this.target = target;
    }

    public AbstractSkill(String id, SkillType type, SkillRarity rarity, SkillTarget target) {
        this(null, id, type, rarity, target);
    }

    protected final void bot(AbstractAction a) {
        ActionHandler.bot(a);
    }

    public void setBaseAttack(int i) {
        attack = i;
        baseAttack = attack;
    }

    public void setBaseSpell(int i) {
        spell = i;
        baseSpell = spell;
    }

    public void setBaseValue(int i) {
        value = i;
        baseValue = value;
    }

    public void setBaseAttack(int i, int up) {
        attack = i;
        baseAttack = attack;
        upAttack = up;
    }

    public void setBaseSpell(int i, int up) {
        spell = i;
        baseSpell = spell;
        upSpell = up;
    }

    public void setBaseValue(int i, int up) {
        value = i;
        baseValue = value;
        upValue = up;
    }

    public void update() {

    }

    public void render(SpriteBatch sb) {

    }

    public String getKeyValue(String key) {
        switch (key) {
            case "A":
                return Integer.toString(this.attack);
            case "S":
                return Integer.toString(this.spell);
            case "V":
                return Integer.toString(this.value);
            default:
                return "ERROR_UNIDENTIFIABLE";
        }
    }

    public String getKeyColor(String key) {
        switch (key) {
            case "A":
                return getHexColor(valueColor(attack, baseAttack));
            case "S":
                return getHexColor(valueColor(spell, baseSpell));
            case "V":
                return getHexColor(valueColor(value, baseValue));
            default:
                return getHexColor(Color.WHITE);
        }
    }

    private Color valueColor(int v, int base) {
        if(v < base) {
            return Color.SCARLET;
        } else if(v > base) {
            return Color.CHARTREUSE;
        } else {
            return Color.CYAN;
        }
    }

    public static Array<AbstractEntity> getTargets(AbstractSkill s) {
        if(s.target == SkillTarget.SELF) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(s.owner);
            return temp;
        } else if(s.target == SkillTarget.S_R) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.players[e.tempIndex - 1]);
            } else {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex + 1]);
            }
            return temp;
        } else if(s.target == SkillTarget.S_L) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.players[e.tempIndex + 1]);
            } else {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex - 1]);
            }
            return temp;
        } else if(s.target == SkillTarget.S_B) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.players[e.tempIndex - 1]);
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.players[e.tempIndex + 1]);
            } else {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex + 1]);
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex - 1]);
            }
            return temp;
        } else if(s.target == SkillTarget.SS_R) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            temp.add(e);
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.players[e.tempIndex - 1]);
            } else {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex + 1]);
            }
            return temp;
        } else if(s.target == SkillTarget.SS_L) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            temp.add(e);
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.players[e.tempIndex + 1]);
            } else {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex - 1]);
            }
            return temp;
        } else if(s.target == SkillTarget.SS_B) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            temp.add(e);
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.players[e.tempIndex - 1]);
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.players[e.tempIndex + 1]);
            } else {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex + 1]);
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex - 1]);
            }
            return temp;
        } else return getTargets(s.target);
    }

    public static Array<AbstractEntity> getTargets(AbstractStatus s) {
        if(s.target == SkillTarget.SELF) {
            Array<AbstractEntity> temp = new Array<>();
            temp.add(s.owner);
            return temp;
        } else if(s.target == SkillTarget.S_R) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.players[e.tempIndex - 1]);
            } else {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex + 1]);
            }
            return temp;
        } else if(s.target == SkillTarget.S_L) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.players[e.tempIndex + 1]);
            } else {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex - 1]);
            }
            return temp;
        } else if(s.target == SkillTarget.S_B) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.players[e.tempIndex - 1]);
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.players[e.tempIndex + 1]);
            } else {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex + 1]);
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex - 1]);
            }
            return temp;
        } else if(s.target == SkillTarget.SS_R) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            temp.add(e);
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.players[e.tempIndex - 1]);
            } else {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex + 1]);
            }
            return temp;
        } else if(s.target == SkillTarget.SS_L) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            temp.add(e);
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.players[e.tempIndex + 1]);
            } else {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex - 1]);
            }
            return temp;
        } else if(s.target == SkillTarget.SS_B) {
            AbstractEntity e = s.owner;
            Array<AbstractEntity> temp = new Array<>();
            temp.add(e);
            if(e instanceof AbstractPlayer) {
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.players[e.tempIndex - 1]);
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.players[e.tempIndex + 1]);
            } else {
                if(e.tempIndex < 3) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex + 1]);
                if(e.tempIndex > 0) temp.add(AbstractLabyrinth.currentFloor.currentRoom.enemies[e.tempIndex - 1]);
            }
            return temp;
        } else return getTargets(s.target);
    }

    public static Array<AbstractEntity> getTargets(SkillTarget target) {
        Array<AbstractEntity> temp = new Array<>();
        PlayerView[] tp = battleScreen.players;
        EnemyView[] te = battleScreen.enemies;
        switch(target) {
            case NONE:
                break;
            case P_F:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) {
                        temp.add(p);
                        break;
                    }
                }
                break;
            case E_F:
                for(int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) {
                        temp.add(e);
                        break;
                    }
                }
                break;
            case P_L:
                for(int i = 3; i >= 0; i--) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) {
                        temp.add(p);
                        break;
                    }
                }
                break;
            case E_L:
                for(int i = 3; i >= 0; i--) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) {
                        temp.add(e);
                        break;
                    }
                }
                break;
            case P_DF:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) temp.add(p);
                    if(temp.size == 2) break;
                }
                break;
            case E_DF:
                for(int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) temp.add(e);
                    if(temp.size == 2) break;
                }
                break;
            case P_DL:
                for(int i = 3; i >= 0; i--) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) temp.add(p);
                    if(temp.size == 2) break;
                }
                break;
            case E_DL:
                for(int i = 3; i >= 0; i--) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) temp.add(e);
                    if(temp.size == 2) break;
                }
                break;
            case P_ALL:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    if(p.isAlive()) temp.add(p);
                }
                break;
            case E_ALL:
                for(int i = 0; i < 4; i++) {
                    AbstractEnemy e = te[i].enemy;
                    if(e.isAlive()) temp.add(e);
                }
                break;
            case ALL:
                for(int i = 0; i < 4; i++) {
                    AbstractPlayer p = tp[i].player;
                    AbstractEnemy e = te[i].enemy;
                    if(p.isAlive()) temp.add(p);
                    if(e.isAlive()) temp.add(e);
                }
                break;
        }
        return temp;
    }

    public String getTargetString() {
        switch(target) {
            case S_R:
                return "▶";
            case S_L:
                return "◀";
            case S_B:
                return "◀　▶";
            case SS_R:
                return "●▶";
            case SS_L:
                return "◀●";
            case SS_B:
                return "◀●▶";
            case SELF:
                return "●";
            case P_F:
                return "○○○●　○○○○";
            case E_F:
                return "○○○○　●○○○";
            case P_L:
                return "●○○○　○○○○";
            case E_L:
                return "○○○○　○○○●";
            case P_DF:
                return "○○●●　○○○○";
            case E_DF:
                return "○○○○　●●○○";
            case P_DL:
                return "●●○○　○○○○";
            case E_DL:
                return "○○○○　○○●●";
            case P_ALL:
                return "●●●●　○○○○";
            case E_ALL:
                return "○○○○　●●●●";
            case ALL:
                return "●●●●　●●●●";
            default:
                return "";
        }
    }

    public static String getTargetString(AbstractSkill.SkillTarget target) {
        switch(target) {
            case S_R:
                return "▶";
            case S_L:
                return "◀";
            case S_B:
                return "◀　▶";
            case SS_R:
                return "●▶";
            case SS_L:
                return "◀●";
            case SS_B:
                return "◀●▶";
            case SELF:
                return "●";
            case P_F:
                return "○○○●　○○○○";
            case E_F:
                return "○○○○　●○○○";
            case P_L:
                return "●○○○　○○○○";
            case E_L:
                return "○○○○　○○○●";
            case P_DF:
                return "○○●●　○○○○";
            case E_DF:
                return "○○○○　●●○○";
            case P_DL:
                return "●●○○　○○○○";
            case E_DL:
                return "○○○○　○○●●";
            case P_ALL:
                return "●●●●　○○○○";
            case E_ALL:
                return "○○○○　●●●●";
            case ALL:
                return "●●●●　●●●●";
            default:
                return "";
        }
    }

    public final void useCard() {
        if(owner != null) {
            for (AbstractStatus s : owner.status) {
                if (s != null) s.onUseCard(this);
            }
        }
        use();
        if(!isTrick && AbstractLabyrinth.energy > 0) AbstractLabyrinth.energy--;
        if(disposable) usedOnce = true;
        else cooldown = cooltime;
    }

    public final boolean canUse() {
        return cooldown == 0 && !usedOnce && !usedOnly && AbstractLabyrinth.energy > 0 && available();
    }

    protected boolean available() {
        return true;
    }

    protected abstract void use();

    public void upgrade() {
        upgradeCard();
        if(upAttack != -1) {
            baseAttack = attack + upAttack;
            attack = baseAttack;
        }
        if(upSpell != -1) {
            baseSpell = spell + upSpell;
            spell = baseSpell;
        }
        if(upValue != -1) {
            baseValue = value + upValue;
            value = baseValue;
        }
        upgraded = true;
        upgradeCount++;
        name = skillData.NAME + "+" + upgradeCount;
    }

    protected abstract void upgradeCard();

    @Override
    public final AbstractSkill clone() {
        try {
            return (AbstractSkill) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public enum SkillType {
        ATTACK, DEFENCE, SCHEME
    }

    public enum SkillRarity {
        STARTER, BRONZE, SILVER, GOLD, SPECIAL, TOKEN, ADVISOR
    }

    public enum SkillTarget {
        NONE, SELF, S_R, S_L, S_B, SS_R, SS_L, SS_B, P_F, E_F, P_L, E_L, P_DF, E_DF, P_DL, E_DL, P_ALL, E_ALL, ALL
    }
}
