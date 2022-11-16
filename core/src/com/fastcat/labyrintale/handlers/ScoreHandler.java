package com.fastcat.labyrintale.handlers;

public class ScoreHandler {
    /***
     * Instance of handler.
     * Initialized on getInstance()
     */
    private static ScoreHandler instance;

    /***
     * Returns instance of handler, if not exist, create new.
     * @return instance of handler
     */
    public static ScoreHandler getInstance() {
        if (instance == null) return (instance = new ScoreHandler());
        return instance;
    }


}
