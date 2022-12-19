package io.github.singlerr.state;

import org.deeplearning4j.rl4j.space.ObservationSpace;
import org.nd4j.linalg.api.ndarray.INDArray;

public final class GameObservationSpace implements ObservationSpace<GameState> {

    //---------------------------------
    //Player
    //attack,spell,speed,critical,multiply,moveRes,debuRes,neutRes, 행동력, skill1-expected damage,expected heal / skill2-..../skill3...
    @Override
    public String getName() {

        return null;
    }

    @Override
    public int[] getShape() {
        return new int[0];
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
