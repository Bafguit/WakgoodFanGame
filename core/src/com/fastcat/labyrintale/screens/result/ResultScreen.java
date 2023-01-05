package com.fastcat.labyrintale.screens.result;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.achvCheck;
import static com.fastcat.labyrintale.handlers.AchieveHandler.achvs;
import static com.fastcat.labyrintale.handlers.AchieveHandler.check;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.interfaces.AtCartoonEnd;
import com.fastcat.labyrintale.screens.dead.DeadScreen;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoDeckIcon;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoIcon;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoItemIcon;
import com.fastcat.labyrintale.screens.result.moreinfo.MoreResultScreen;
import com.fastcat.labyrintale.uis.StatIcon;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import com.fastcat.labyrintale.utils.BuildInfo;
import java.util.HashMap;

public class ResultScreen extends AbstractScreen implements AtCartoonEnd {

    private final FontHandler.FontData fontData = INFO_NAME;
    private final FontHandler.FontData diffFont = SUB_NAME;
    private final float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    private final float tx, tw;

    public final MoreResultScreen moreScreen;

    private final Sprite backPanel;

    public ResultPlayerBigIcon[] pIcons = new ResultPlayerBigIcon[4];
    public MoreResultButton more;
    public ResultToMainButton back;
    public DeadScreen.ScreenType dType;
    public String diff;
    public String gold;
    public String time;
    public String score;

    @SuppressWarnings("NewApi")
    public ResultScreen(DeadScreen.ScreenType type) {
        setBg(FileHandler.getBg().get("BG_" + type));
        backPanel = FileHandler.getUi().get("RESULT_" + type);
        if (type == DeadScreen.ScreenType.WIN) {

            if (achvCheck.REFLECT >= 4) {
                AchieveHandler.Achievement ac = AchieveHandler.Achievement.REFLECT;
                int cur = achvs.get(ac);
                if (cur < 3) {
                    if (cur < 1 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.NORMAL) {
                        achvs.replace(ac, 1);
                    } else if (cur < 2 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.HARD) {
                        achvs.replace(ac, 2);
                    } else if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN) {
                        achvs.replace(ac, 3);
                    }
                }
            }

            if (achvCheck.IMMORTAL) {
                AchieveHandler.Achievement ac = AchieveHandler.Achievement.IMMORTAL;
                int cur = achvs.get(ac);
                if (cur < 3) {
                    if (cur < 1 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.NORMAL) {
                        achvs.replace(ac, 1);
                    } else if (cur < 2 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.HARD) {
                        achvs.replace(ac, 2);
                    } else if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN) {
                        achvs.replace(ac, 3);
                    }
                }
            }

            if (achvCheck.NO_USE_GOLD) {
                AchieveHandler.Achievement ac = AchieveHandler.Achievement.NO_USE_GOLD;
                int cur = achvs.get(ac);
                if (cur < 3) {
                    if (cur < 1 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.NORMAL) {
                        achvs.replace(ac, 1);
                    } else if (cur < 2 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.HARD) {
                        achvs.replace(ac, 2);
                    } else if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN) {
                        achvs.replace(ac, 3);
                    }
                }
            }

            int purity = achvs.get(AchieveHandler.Achievement.PURITY);
            if (purity < 3) {
                for (AbstractPlayer p : AbstractLabyrinth.players) {
                    if (p.deck.get(0).rarity == AbstractSkill.SkillRarity.STARTER
                            && p.deck.get(1).rarity == AbstractSkill.SkillRarity.STARTER
                            && p.deck.get(2).rarity == AbstractSkill.SkillRarity.STARTER) {

                        if (purity < 1 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.NORMAL) {
                            achvs.replace(AchieveHandler.Achievement.PURITY, 1);
                        } else if (purity < 2 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.HARD) {
                            achvs.replace(AchieveHandler.Achievement.PURITY, 2);
                        } else if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN) {
                            achvs.replace(AchieveHandler.Achievement.PURITY, 3);
                        }
                        break;
                    }
                }
            }

            if (AbstractLabyrinth.minute < 25) {
                AchieveHandler.Achievement ac = AchieveHandler.Achievement.FASTEST;
                int cur = achvs.get(ac);
                if (cur < 3) {
                    if (cur < 1 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.NORMAL) {
                        achvs.replace(ac, 1);
                    } else if (cur < 2 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.HARD) {
                        achvs.replace(ac, 2);
                    } else if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN) {
                        achvs.replace(ac, 3);
                    }
                }
            }

            int aCount = 0;
            boolean noItem = true;
            for (AbstractPlayer p : AbstractLabyrinth.players) {
                AchieveHandler.Achievement ac = AchieveHandler.Achievement.valueOf(p.playerClass.toString());
                int cur = achvs.get(ac);
                if (cur < 3) {
                    if (cur < 1 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.NORMAL) {
                        achvs.replace(ac, 1);
                    } else if (cur < 2 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.HARD) {
                        achvs.replace(ac, 2);
                    } else if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN) {
                        achvs.replace(ac, 3);
                    }
                }
                if (noItem) noItem = p.item[0].id.equals("PlaceHolder") && p.item[1].id.equals("PlaceHolder");
                if (p.isAlive()) aCount++;
            }

            if (aCount == 1) {
                AchieveHandler.Achievement ac = AchieveHandler.Achievement.LAST_ONE;
                int cur = achvs.get(ac);
                if (cur < 1 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.NORMAL) {
                    achvs.replace(ac, 1);
                } else if (cur < 2 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.HARD) {
                    achvs.replace(ac, 2);
                } else if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN) {
                    achvs.replace(ac, 3);
                }
            }

            if (noItem) {
                AchieveHandler.Achievement ac = AchieveHandler.Achievement.NO_ITEM;
                int cur = achvs.get(ac);
                if (cur == 0) {
                    achvs.replace(ac, 3);
                }
            }

            if (AbstractLabyrinth.gold >= 2000) {
                AchieveHandler.Achievement ac = AchieveHandler.Achievement.GOLDEN;
                int cur = achvs.get(ac);
                if (cur < 3) {
                    if (cur < 1 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.NORMAL) {
                        achvs.replace(ac, 1);
                    } else if (cur < 2 && AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.HARD) {
                        achvs.replace(ac, 2);
                    } else if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN) {
                        achvs.replace(ac, 3);
                    }
                }
            }

            if (achvs.get(AchieveHandler.Achievement.ALL_CHAR) == 0) {
                int nCount = 0;
                for (AbstractPlayer.PlayerClass cls : AbstractPlayer.PlayerClass.values()) {
                    AchieveHandler.Achievement ac = AchieveHandler.Achievement.valueOf(cls.toString());
                    int clear = achvs.get(ac);
                    if (clear > 0) {
                        nCount++;
                    }
                }
                if (nCount == 8) {
                    achvs.replace(AchieveHandler.Achievement.ALL_CHAR, 3);
                }
            }

            int advCur = achvs.get(AchieveHandler.Achievement.ALL_ADV);
            if (advCur == 0) {
                int nCount = 0;
                for (int i = 0; i < AbstractAdvisor.AdvisorClass.values().length - 3; i++) {
                    AbstractAdvisor.AdvisorClass cls = AbstractAdvisor.AdvisorClass.values()[i];
                    boolean clear = check.ALL_ADV.get(cls);
                    if (clear) {
                        nCount++;
                    } else if (AbstractLabyrinth.advisor.id.equals(
                            cls.toString().toLowerCase())) {
                        check.ALL_ADV.replace(cls, true);
                    }
                }
                if (nCount == AbstractAdvisor.AdvisorClass.values().length) {
                    achvs.replace(AchieveHandler.Achievement.ALL_ADV, 3);
                }
            }

            if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN) {
                AchieveHandler.Achievement ac = AchieveHandler.Achievement.COFFIN;
                int cur = achvs.get(ac);
                if (cur == 0) {
                    achvs.replace(ac, 3);
                }
            }
        }
        dType = type;
        moreScreen = new MoreResultScreen(type);
        AbstractLabyrinth.result = type;
        for (int g = 0; g < 4; g++) {
            ResultPlayerBigIcon pc = new ResultPlayerBigIcon(AbstractLabyrinth.players[g]);
            pc.setScale(0.95f);
            pc.setPosition((968 + 208 * g) * scale - pc.sWidth / 2, 609 * scale);
            pIcons[g] = pc;
        }
        cType = ControlPanel.ControlType.HIDE;
        diff = "난이도: ";
        HashMap<String, Boolean> temp = UnlockHandler.achvs.get(UnlockHandler.Unlocks.DIFF);
        if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.NORMAL) {
            diff += "일반";
            if (type == DeadScreen.ScreenType.WIN && !temp.get("HARD")) {
                temp.replace("HARD", true);
                temp.replace("COFFIN", true);
                UnlockHandler.save();
            }
        } else if (AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.HARD) {
            diff += "어려움";
        } else diff += "관";
        gold = AbstractLabyrinth.gold + "G";
        time = AbstractLabyrinth.minute + "분 " + AbstractLabyrinth.second + "초";
        score = String.valueOf(AbstractLabyrinth.scoreHandle.score);
        back = new ResultToMainButton(this);
        more = new MoreResultButton(this);
        tx = 1240 * scale;
        tw = 400 * scale;

        for(int i = 0; i< 4;i++){
            Labyrintale.game.getGameAnalytics().submitDesignEvent("game:character:" + AbstractLabyrinth.players[i].id);
        }
    }

    @Override
    public void update() {
        cType = ControlPanel.ControlType.HIDE;
        for (int i = 0; i < 4; i++) {
            pIcons[i].update();
        }
        more.update();
        back.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(backPanel, 0, 0, w, h);

        more.render(sb);
        back.render(sb);

        sb.setColor(Color.WHITE);

        renderCenter(sb, diffFont, diff, 1197 * scale, 1054 * scale, 165 * scale, tw);

        renderLineRight(sb, fontData, gold, tx, 532 * scale, tw, tw);
        renderLineRight(sb, fontData, time, tx, 430 * scale, tw, tw);
        renderLineRight(sb, fontData, score, tx, 325 * scale, tw, tw);

        for (int i = 0; i < 4; i++) {
            pIcons[i].render(sb);
        }
    }

    @Override
    public void show() {
        SoundHandler.addMusic(dType == DeadScreen.ScreenType.WIN ? "WIN" : "LOSS", false, false);
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    @Override
    public void cartoonEnded() {
        Labyrintale.fadeOutAndChangeScreen(this);
    }
}
