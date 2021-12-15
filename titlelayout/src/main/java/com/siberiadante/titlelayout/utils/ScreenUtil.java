package com.siberiadante.titlelayout.utils;


import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.agp.components.AttrHelper;
import ohos.agp.utils.Color;
import ohos.agp.window.service.DisplayManager;
import ohos.agp.window.service.Window;
import ohos.agp.window.service.WindowManager;
import ohos.app.Context;
import ohos.bundle.AbilityInfo;
import ohos.global.configuration.Configuration;
import ohos.miscservices.screenlock.ScreenLockController;
import ohos.sysappcomponents.settings.SystemSettings;



/**
 * ScreenUtil.
 */
public class ScreenUtil {

    private ScreenUtil() {
        super();
    }


    public static float getDensity(Context context) {
        return AttrHelper.getDensity(context);
    }

    /**
     * Get the width of the screen.
     *
     * @param context context
     * @return 获取屏幕的宽 单位：px
     */
    public static int getScreenWidthPx(Context context) {
        return DisplayManager.getInstance().getDefaultDisplay(context).get().getAttributes().width;
    }

    /**
     * Get the height of the screen.
     *
     * @param context context
     * @return 获取屏幕的高 单位：px
     */
    public static int getScreenHeightPx(Context context) {
        return DisplayManager.getInstance().getDefaultDisplay(context).get().getAttributes().height;
    }

    /**
     * Get the width of the screen (unit: px).
     *
     * @param context context
     * @return 屏幕宽
     */
    public static int getScreenWidth(Context context) {
        return DisplayManager.getInstance().getDefaultDisplay(context).get().getAttributes().width;
    }

    /**
     * 获取屏幕的高度（单位：px）.
     *
     * @param context context
     * @return 屏幕高
     */
    public static int getScreenHeight(Context context) {
        return DisplayManager.getInstance().getDefaultDisplay(context).get().getAttributes().height;
    }

    /**
     * Get the width of the screen (unit: vp).
     *
     * @param context context
     * @return 获取屏幕的宽 单位：vp
     */
    public static int getScreenWidthVp(Context context) {
        return context.getResourceManager().getDeviceCapability().width;
    }

    /**
     * Get the height of the screen (unit: vp).
     *
     * @param context context
     * @return 获取屏幕的高 单位：vp
     */
    public static int getScreenHeightVp(Context context) {
        return context.getResourceManager().getDeviceCapability().height;
    }

    /**
     * 透明状态栏.
     * 需要设置的xml中增加属性：
     * android:clipToPadding="true"
     * android:fitsSystemWindows="true"
     *
     * @param activity activity
     */
    public static void setStatusTranslucent(Ability activity) {
        activity.getWindow().addFlags(WindowManager.LayoutConfig.MARK_TRANSLUCENT_STATUS);
    }

    /**
     * 透明导航栏.
     *
     * @param activity activity
     */
    public static void setNavigationTranslucent(Ability activity) {
        activity.getWindow().addFlags(WindowManager.LayoutConfig.MARK_TRANSLUCENT_NAVIGATION);
    }

    /**
     * 透明状态栏和透明导航栏.
     *
     * @param activity activity
     */
    public static void setTranslucent(Ability activity) {
        //透明状态栏
        activity.getWindow().addFlags(WindowManager.LayoutConfig.MARK_TRANSLUCENT_STATUS);
        //透明导航栏
        activity.getWindow().addFlags(WindowManager.LayoutConfig.MARK_TRANSLUCENT_NAVIGATION);
    }

    /**
     * 设置全屏.
     *
     * @param activity activity
     */
    public static void setFullScreen(Ability activity) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutConfig.MARK_TRANSLUCENT_STATUS
                | WindowManager.LayoutConfig.MARK_TRANSLUCENT_NAVIGATION);
        window.setStatusBarVisibility(WindowManager.LayoutConfig.MARK_FULL_SCREEN
                | WindowManager.LayoutConfig.SYSTEM_BAR_HIDE_NAVIGATION_AUTO
        );
        window.addFlags(WindowManager.LayoutConfig.MARK_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT.getValue());
        window.setNavigationBarColor(Color.TRANSPARENT.getValue());
    }




    /**
     * 设置屏幕为横屏.
     * <p>还有一种就是在Activity中加属性android:screenOrientation="landscape"</p>
     * <p>不设置Activity的android:configChanges时，切屏会重新调用各个生命周期，切横屏时会执行一次，切竖屏时会执行两次</p>
     * <p>设置Activity的android:configChanges="orientation"时，切屏还是会重新调用各个生命周期，切横、竖屏时只会执行一次</p>
     * <p>设置Activity的android:configChanges="orientation|keyboardHidden|screenSize"（4.0以上必须带最后一个参数）时
     * 切屏不会重新调用各个生命周期，只会执行onConfigurationChanged方法</p>
     *
     * @param activity activity
     */
    public static void setLandscape(Ability activity) {
        activity.setDisplayOrientation(AbilityInfo.DisplayOrientation.LANDSCAPE);
    }

    /**
     * 设置屏幕为竖屏.
     *
     * @param activity activity
     */
    public static void setPortrait(Ability activity) {
        activity.setDisplayOrientation(AbilityInfo.DisplayOrientation.PORTRAIT);
    }

    /**
     * 判断是否横屏.
     *
     * @param context context
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isLandscape(Context context) {
        return context.getResourceManager().getConfiguration().direction == Configuration.DIRECTION_HORIZONTAL;
    }

    /**
     * 判断是否竖屏.
     *
     * @param context context
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isPortrait(Context context) {
        return context.getResourceManager().getConfiguration().direction == Configuration.DIRECTION_VERTICAL;
    }

    /**
     * 获取屏幕旋转角度.
     *
     * @param context context context
     * @return 屏幕旋转角度
     */
    public static int getScreenRotation(Context context) {

        switch (DisplayManager.getInstance().getDefaultDisplay(context).get().getRotation()) {
            default:
            case 0:
                return 0;
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
        }
    }




    /**
     * 判断是否锁屏.
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isScreenLock() {
        return ScreenLockController.getInstance().isScreenLocked();
    }

    /**
     * 设置进入休眠时长.
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_SETTINGS" />}</p>
     *
     * @param context context
     * @param duration 时长
     */
    public static void setSleepDuration(Context context, int duration) {
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);
        SystemSettings.setValue(dataAbilityHelper,
                SystemSettings.Display.SCREEN_OFF_TIMEOUT,
                Integer.toString(duration));
    }

    /**
     * 获取进入休眠时长.
     *
     * @param context context
     * @return 进入休眠时长，报错返回-123
     */
    public static int getSleepDuration(Context context) {
        DataAbilityHelper dataAbilityHelper = DataAbilityHelper.creator(context);
        return Integer.parseInt(SystemSettings.getValue(dataAbilityHelper, SystemSettings.Display.SCREEN_OFF_TIMEOUT));
    }
}
