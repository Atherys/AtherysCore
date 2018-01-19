package com.atherys.core.utils;

public class MathUtils {

    public static float clamp ( float value, float min, float max ) {
        return value < min ? min : value > max ? max : value;
    }

}
