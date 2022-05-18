package com.rank.utils;

import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.util.StringUtils;

/**
 * @author keshawn
 * @date 2017/11/7
 */
public final class CastUtil {

    private CastUtil() {
    }

    public static String castString(Object object) {
        return castString(object, "");
    }

    public static String castString(Object object, String defaultValue) {
        return object != null
                ? String.valueOf(object)
                : defaultValue;
    }

    public static double castDouble(Object object) {
        return castDouble(object, 0);
    }

    public static double castDouble(Object object, double defaultValue) {
        double doubleValue = defaultValue;
        if (object != null) {
            String stringValue = castString(object);
            if (StringUtil.isNotEmpty(stringValue)) {
                try {
                    doubleValue = Double.parseDouble(stringValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    public static long castLong(Object object) {
        return castLong(object, 0);
    }

    public static long castLong(Object object, long defaultValue) {
        long longValue = defaultValue;
        if (object != null) {
            String stringValue = castString(object);
            if (StringUtil.isNotEmpty(stringValue)) {
                try {
                    longValue = Long.parseLong(stringValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    public static int castInt(Object object) {
        return castInt(object, 0);
    }

    public static int castInt(Object object, int defaultValue) {
        int intValue = defaultValue;
        if (object != null) {
            String stringValue = castString(object);
            if (StringUtil.isNotEmpty(stringValue)) {
                try {
                    intValue = Integer.parseInt(stringValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    public static boolean castBoolean(Object object) {
        return castBoolean(object, false);
    }

    public static boolean castBoolean(Object object, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (object != null) {
            booleanValue = Boolean.parseBoolean(castString(object));
        }
        return booleanValue;
    }

    public static float castFloat(Object object) {
        return castFloat(object, 0);
    }

    public static float castFloat(Object object, float defaultValue) {
        float floatValue = defaultValue;
        if (object != null) {
            String stringValue = castString(object);
            if (StringUtil.isNotEmpty(stringValue)) {
                try {
                    floatValue = Float.parseFloat(stringValue);
                } catch (NumberFormatException e) {
                    floatValue = defaultValue;
                }
            }
        }
        return floatValue;
    }

    public static <T> T castToTarget(Object source, Class<? extends T> target ) {
        return castToTarget(source, target, null);
    }

    public static <T> T castToTarget(Object source, Class<? extends T> target, T defaultValue) {
        var conversion = ApplicationConversionService.getSharedInstance();
        if (source == null || !conversion.canConvert(source.getClass(), target)) {
            return defaultValue;
        }
        return ApplicationConversionService.getSharedInstance().convert(source, target);
    }

    public static String castToString(Object source, String defaultValue) {
        if (source == null || !StringUtils.hasText(source.toString())) {
            return defaultValue;
        }
        return StringUtils.trimWhitespace(source.toString());
    }

}
