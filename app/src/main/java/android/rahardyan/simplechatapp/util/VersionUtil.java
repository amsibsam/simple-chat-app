package android.rahardyan.simplechatapp.util;

import android.os.Build;

/**
 * Created by rahardyan on 16/05/17.
 */

/*
 * VersionUtil: OS Version utilities, to get os version of the current device.
 */
public final class VersionUtil {
    private VersionUtil() {

    }

    /**
     * Check if the OS version is ICS or higher
     *
     */
    public static boolean isIceCreamOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * Check if the OS version is JB or higher
     *
     */
    public static boolean isJellybeanOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    /**
     * Check if the OS version is JBMR1 or higher
     *
     */
    public static boolean isJellybeanMR1OrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    /**
     * Check if the OS version is JBMR2 or higher
     *
     */
    public static boolean isJellyBeanMr2OrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    /**
     * Check if the OS version is KitKat or higher
     *
     */
    public static boolean isKitkatOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    /**
     * Check if the OS version is KitKatWatch or higher
     *
     */
    public static boolean isKitkatWatchOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH;
    }

    /**
     * Check if the OS version is below LolliPop
     *
     */
    public static boolean isBelowLollipop() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Check if the OS version is LOLLIPOP or higher
     *
     */
    public static boolean isLollipopOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Check if the OS version is LOLLIPOP MR1 or higher
     *
     */
    public static boolean isLollipopMr1OrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    /**
     * Check if the OS version is MARSHMALLOW or higher
     *
     */
    public static boolean isMarshmallowOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * Check if the OS version is MARSHMALLOW or lower
     *
     */
    public static boolean isMarshmallowOrLower() {
        return Build.VERSION.SDK_INT <= Build.VERSION_CODES.M;
    }

    /**
     * Check if the OS version is NOUGHAT or higher
     *
     */
    public static boolean isNougatOrHigher() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }
}
