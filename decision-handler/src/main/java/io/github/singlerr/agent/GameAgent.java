package io.github.singlerr.agent;

import io.github.singlerr.state.GameState;
import org.deeplearning4j.gym.StepReply;
import org.deeplearning4j.rl4j.mdp.MDP;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.space.ObservationSpace;

/***
 * Game agent of rl
 */
public final class GameAgent implements MDP<GameState,Integer, DiscreteSpace> {

    @Override
    public ObservationSpace<GameState> getObservationSpace() {
        return null;
    }

    @Override
    public DiscreteSpace getActionSpace() {
        return null;
    }

    @Override
    public GameState reset() {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public StepReply<GameState> step(Integer integer) {
        return null;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public MDP<GameState, Integer, DiscreteSpace> newInstance() {
        return null;
    }
}
