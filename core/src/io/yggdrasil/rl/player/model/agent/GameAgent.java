package io.yggdrasil.rl.player.model.agent;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.screens.battle.BattleScreen;
import io.yggdrasil.rl.TurnState;
import io.yggdrasil.rl.player.model.environment.ActionResult;
import io.yggdrasil.rl.player.model.environment.GameEnvironment;
import io.yggdrasil.rl.player.model.state.GameObservationSpace;
import io.yggdrasil.rl.player.model.state.GameState;
import org.deeplearning4j.gym.StepReply;
import org.deeplearning4j.rl4j.mdp.MDP;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.space.ObservationSpace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameAgent implements MDP<GameState, Integer, DiscreteSpace> {

    private static final Logger logger = LoggerFactory.getLogger(GameAgent.class);
    private final ObservationSpace<GameState> observationSpace = new GameObservationSpace();
    //아군 4 명과 각각이 가지고 있는 스킬 갯수 4개로 총 16개가 필요하다. 타겟 지정 필요없음. 스킬 선택하면 자동으로 타겟 지정됨
    //전진 후퇴로 각 4명에게 스킬 2개를 추가했다
    private final DiscreteSpace actionSpace = new DiscreteSpace(16+2*4);
    private final List<ActionResult> stepHistory = new CopyOnWriteArrayList<>();

    private GameEnvironment environment = new GameEnvironment();

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public ObservationSpace<GameState> getObservationSpace() {
        return observationSpace;
    }

    @Override
    public DiscreteSpace getActionSpace() {
        return actionSpace;
    }

    @Override
    public GameState reset() {
      //  logger.info("--- new episode started. ---");
        if (Labyrintale.getCurScreen() instanceof BattleScreen)
            Labyrintale.battleScreen = new BattleScreen();
        environment = new GameEnvironment();

        return new GameState(environment.getAllies(), environment.getEnemies());
    }

    @Override
    public void close() {
        logger.info("--- training ended. ---");
    }

    @Override
    public StepReply<GameState> step(Integer integer) {
        try {
        //    logger.info("--- new step action:  " + integer + " ---");
            ActionResult result = environment.doAction(integer);

            double reward = result.getReward();

            GameState nextState = getNextState();

            StepReply<GameState> stepReply = new StepReply<>(nextState, reward, isDone(), null);

            recordHistory(result);


            TurnState.StateObject stateObject = TurnState.getInstance().getFirstPlayer();
            if (stateObject.incrementAndGet() > 4) {
                stateObject.resetActionCount();
                stateObject.setCompleted(true);

                synchronized (stateObject) {
                    stateObject.wait();
                }
            }

            return stepReply;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean isDone() {
        return environment.areAlliesDead() || environment.areEnemiesDead();
    }

    @Override
    public MDP<GameState, Integer, DiscreteSpace> newInstance() {
        return new GameAgent();
    }

    private GameState getNextState() {
        return new GameState(environment.getAllies(), environment.getEnemies());
    }

    private void recordHistory(ActionResult result) {
        //      stepHistory.add(result);
    }
}
