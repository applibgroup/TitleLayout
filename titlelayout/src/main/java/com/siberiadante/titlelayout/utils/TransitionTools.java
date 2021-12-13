package com.siberiadante.titlelayout.utils;


import ohos.agp.components.AttrHelper;
import ohos.app.Context;


/**
 * TransitionTools.
 */
public class TransitionTools {

    private TransitionTools() {
        super();
    }

    public static float getDensity(Context context) {
        return AttrHelper.getDensity(context);
    }

    /**
     * dip转为 px.
     *
     * @param context context
     * @param dipValue dipValue
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = AttrHelper.getDensity(context);
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px 转为 dip.
     *
     * @param context context
     * @param pxValue pxValue
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = AttrHelper.getDensity(context);

        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变.
     *
     * @param context context
     * @param pxValue pxValue
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = AttrHelper.getDensity(context);
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变.
     *
     * @param context context
     * @param spValue spValue
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = AttrHelper.getDensity(context);
        return (int) (spValue * fontScale + 0.5f);
    }
}
