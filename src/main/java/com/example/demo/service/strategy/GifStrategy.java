package com.example.demo.service.strategy;

import com.example.demo.service.ImageStrategy;

public class GifStrategy implements ImageStrategy {

    // region Singleton Pattern
    private static final GifStrategy INSTANCE = new GifStrategy();

    private GifStrategy() {
    }

    public static GifStrategy getInstance() {
        return INSTANCE;
    }
    // endregion

    public static final String TYPE = "gif";

    @Override
    public boolean typeMatch(String type) {
        return TYPE.equals(type.toLowerCase());
    }

}
