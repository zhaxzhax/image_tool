package com.example.demo.service;

import com.example.demo.service.strategy.*;

import java.util.ArrayList;
import java.util.List;

public class ImageStrategyFactory {

    private static final List<ImageStrategy> strategies = new ArrayList<>();

    static {
        // if app support more type, modify here
        strategies.add(BmpStrategy.getInstance());
        strategies.add(GifStrategy.getInstance());
        strategies.add(JpgStrategy.getInstance());
        strategies.add(PngStrategy.getInstance());
        strategies.add(TifStrategy.getInstance());
    }

    public static ImageStrategy getStrategy(String sourceType) {
        for (ImageStrategy strategy : strategies) {
            if (strategy.typeMatch(sourceType)) {
                return strategy;
            }
        }
        throw new RuntimeException("Type " + sourceType + " Not Support");
    }
}
