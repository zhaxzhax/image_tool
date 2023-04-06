package com.example.demo.service.strategy;

import com.example.demo.service.ImageStrategy;

public class TifStrategy implements ImageStrategy {

    // region Singleton Pattern
    private static final TifStrategy INSTANCE = new TifStrategy();

    private TifStrategy() {
    }

    public static TifStrategy getInstance() {
        return INSTANCE;
    }
    // endregion

    public static final String TYPE = "tif";

    @Override
    public boolean typeMatch(String type) {
        return TYPE.equals(type.toLowerCase());
    }

}
