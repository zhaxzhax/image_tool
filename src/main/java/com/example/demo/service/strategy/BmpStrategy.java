package com.example.demo.service.strategy;

import com.example.demo.service.ImageStrategy;

public class BmpStrategy implements ImageStrategy {

    // region Singleton Pattern
    private static final BmpStrategy INSTANCE = new BmpStrategy();

    private BmpStrategy() {
    }

    public static BmpStrategy getInstance() {
        return INSTANCE;
    }
    // endregion

    public static final String TYPE = "bmp";

    @Override
    public boolean typeMatch(String type) {
        return TYPE.equals(type.toLowerCase());
    }


}
