package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.actions.FlashAction;
import com.fastcat.labyrintale.effects.UpIconEffect;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.items.starter.PlaceHolder;
import com.fastcat.labyrintale.strings.ItemString;
import com.fastcat.labyrintale.strings.KeyString;
import com.fastcat.labyrintale.strings.SkillString;

public class AbstractItem implements Cloneable {

  public SkillString.SkillData data;
  public Sprite img;
  public Sprite tImg;
  public String id;
  public String name;
  public String desc;
  public String sub;
  public AbstractPlayer owner;
  public ItemRarity rarity;
  public Array<AbstractUI.SubText> key = new Array<>();

  public AbstractItem(String id, AbstractPlayer owner, ItemRarity rarity) {
    this.id = id;
    if(rarity == ItemRarity.ADVISOR) {
      img = FileHandler.getAdvImg().get(AbstractAdvisor.AdvisorClass.valueOf(this.id.toUpperCase()));
      tImg = img;
      data = StringHandler.skillString.get(this.id);
    } else {
      img = FileHandler.getItemImg().get(this.id);
      tImg = FileHandler.getItemImgTrans().get(this.id);
      data = StringHandler.itemString.get(this.id);
    }
    name = data.NAME;
    desc = data.DESC;
    if (data.SUB != null) sub = data.SUB;
    if (data.KEY != null) {
      for (String k : data.KEY) {
        KeyString.KeyData kd = StringHandler.keyString.get(k);
        key.add(new AbstractUI.SubText(kd.NAME, kd.DESC));
      }
    }
    this.rarity = rarity;
    this.owner = owner;
  }

  public final void setOwner(AbstractPlayer owner) {
    this.owner = owner;
  }

  public final void flash() {
    flash(owner);
  }

  public final void flash(AbstractEntity e) {
    ActionHandler.bot(new FlashAction(e, img));
  }

  public final void flashWithoutAction() {
    flashWithoutAction(owner);
  }

  public final void flashWithoutAction(AbstractEntity e) {
    EffectHandler.add(new UpIconEffect(e.animX, e.animY + Gdx.graphics.getHeight() * 0.2f, img));
  }

  public void startOfTurn() {}

  public void endOfTurn() {}

  public void startOfRound() {}

  public void endOfRound() {}

  public void onLoseBlock(int block) {}

  public int onGainBlock(int block) {
    return block;
  }

  public void atBattleStart() {}

  public void atBattleEnd() {}

  public void onDamage(AbstractEntity target, int damage, AbstractEntity.DamageType type) {}

  public int onDamaged(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
    return damage;
  }

  public void onAttack(AbstractEntity target, int damage, AbstractEntity.DamageType type) {}

  public int onAttacked(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
    return damage;
  }

  public float onAttackedMultiply(
      AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
    return 1.0f;
  }

  public float attackMultiply(int base) {
    return 1.0f;
  }

  public float spellMultiply(int base) {
    return 1.0f;
  }

  public int showAttack(int base) {
    return base;
  }

  public int showSpell(int base) {
    return base;
  }

  public void onHeal(int heal) {}

  public void onDeath(AbstractEntity murder) {}

  public void onApplyStatus(AbstractStatus s, Array<AbstractEntity> target) {}

  public void onGain() {}

  public void onRemove() {}

  public int onGainGold(int amount) {
    return amount;
  }

  public void onMove(AbstractEntity source) {}

  protected final void consume() {
    for(int i = 0; i < 2; i++) {
      if(owner.item[i] == this) {
        owner.gainItem(new PlaceHolder(owner), i);
        break;
      }
    }
  }

  protected final void top(AbstractAction a) {
    ActionHandler.top(a);
  }

  protected final void bot(AbstractAction a) {
    ActionHandler.bot(a);
  }

  @Override
  public final AbstractItem clone() {
    try {
      return (AbstractItem) super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return null;
  }

  //TODO 임시 희귀도 구분
  public Color getRarityColor() {
    if(rarity != null) {
      if (rarity == ItemRarity.BRONZE) {
        return Color.BROWN;
      } else if (rarity == ItemRarity.SILVER) {
        return Color.SKY;
      } else if (rarity == ItemRarity.GOLD) {
        return Color.GOLD;
      } else if (rarity == ItemRarity.BOSS) {
        return Color.RED;
      } else if (rarity == ItemRarity.SHOP) {
        return Color.CHARTREUSE;
      } else if (rarity == ItemRarity.SPECIAL) {
        return Color.PURPLE;
      }
    }
    return Color.WHITE;
  }
  public static Color getRarityColor(ItemRarity rarity) {
    if(rarity != null) {
      if (rarity == ItemRarity.BRONZE) {
        return Color.BROWN;
      } else if (rarity == ItemRarity.SILVER) {
        return Color.SKY;
      } else if (rarity == ItemRarity.GOLD) {
        return Color.GOLD;
      } else if (rarity == ItemRarity.BOSS) {
        return Color.RED;
      } else if (rarity == ItemRarity.SHOP) {
        return Color.CHARTREUSE;
      } else if (rarity == ItemRarity.SPECIAL) {
        return Color.PURPLE;
      }
    }
    return Color.WHITE;
  }

  public enum ItemRarity {
    EMPTY,
    STARTER,
    BRONZE,
    SILVER,
    GOLD,
    BOSS,
    SHOP,
    SPECIAL,
    ADVISOR
  }
}
