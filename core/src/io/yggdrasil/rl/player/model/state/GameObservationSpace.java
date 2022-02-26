package io.yggdrasil.rl.player.model.state;

import org.deeplearning4j.rl4j.space.ObservationSpace;
import org.nd4j.linalg.api.ndarray.INDArray;

public class GameObservationSpace implements ObservationSpace<GameState> {

    @Override
    public String getName() {
        return "Game Observation Space";
    }

    @Override
    public int[] getShape() {
        return new int[]{20};
    }

    @Override
    public INDArray getLow() {
        return null;
    }

    @Override
    public INDArray getHigh() {
        return null;
    }
}
