package io.yggdrasil.rl;


public class TurnState extends Thread {

    private static TurnState instance;
    private final StateObject firstPlayer;
    private final StateObject secondPlayer;

    private TurnState() {
        firstPlayer = new StateObject();
        secondPlayer = new StateObject();
    }

    public static TurnState getInstance() {
        if (instance == null)
            return (instance = new TurnState());
        return instance;
    }

    public StateObject getFirstPlayer() {
        return firstPlayer;
    }

    public StateObject getSecondPlayer() {
        return secondPlayer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (firstPlayer.isCompleted()) {
                synchronized (secondPlayer) {
                    firstPlayer.setCompleted(false);
                    secondPlayer.notifyAll();
                }
            }
            if (secondPlayer.isCompleted()) {
                synchronized (firstPlayer) {
                    secondPlayer.setCompleted(false);
                    firstPlayer.notifyAll();
                }
            }

        }

    }


    public class StateObject {
        private boolean completed = false;

        private int actionCount = 0;

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public int getActionCount() {
            return actionCount;
        }

        public int incrementAndGet() {
            return ++actionCount;
        }

        public int getAndIncrement() {
            return actionCount++;
        }

        public void resetActionCount() {
            actionCount = 0;
        }
    }
}
