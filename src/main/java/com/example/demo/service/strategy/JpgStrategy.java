package com.example.demo.service.strategy;

import com.example.demo.service.ImageStrategy;

public class JpgStrategy implements ImageStrategy {

    // region Singleton Pattern
    private static final JpgStrategy INSTANCE = new JpgStrategy();

    private JpgStrategy() {
    }

    public static JpgStrategy getInstance() {
        return INSTANCE;
    }
    // endregion

    public static final String TYPE = "jpg";

    @Override
    public boolean typeMatch(String type) {
        return TYPE.equals(type.toLowerCase());
    }


}
