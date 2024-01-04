package com.univr.pump.insulinpump.utils;

public enum DIABETES_TYPE {
    TYPE_1,
    TYPE_2,
    GESTATIONAL,
    LADA,
    MONOGENIC,
    OTHER;

    public static boolean isValid(String test) {
        for (DIABETES_TYPE type : DIABETES_TYPE.values()) {
            if (type.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }
}


