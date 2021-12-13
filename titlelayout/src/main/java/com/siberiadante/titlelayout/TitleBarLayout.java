package com.siberiadante.titlelayout;

import static ohos.agp.components.DependentLayout.LayoutConfig.CENTER_IN_PARENT;
import ohos.aafwk.ability.Ability;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.PixelMapElement;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.text.Font;
import ohos.agp.utils.Color;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.PixelMap;
import com.siberiadante.titlelayout.utils.AttrUtils;
import com.siberiadante.titlelayout.utils.StringUtil;
import com.siberiadante.titlelayout.utils.TransitionTools;


/**
 * TitleBarLayout.
 */
public class TitleBarLayout extends DependentLayout implements Component.EstimateSizeListener {
    private int mLayoutBarHeight;

    private Element mLeftImage;
    private int mLeftImageWidth;
    private int mLeftImagePaddingStart;
    private int mLeftImagePaddingEnd;

    private String mLeftText = "";
    private int mLeftTextSize;
    private Color mLeftTextColor = Color.BLACK;
    private int mLeftTextPaddingStart;
    private int mLeftTextPaddingEnd;
    private String mLeftTextStyle = FontWeight.NORMAL;

    private String mTitle = "";
    private float mTitleSize;
    private Color mTitleColor = Color.BLACK;
    private String mTitleStyle = FontWeight.NORMAL;

    private String mSubTitle = "";
    private float mSubTitleSize;
    private Color mSubTitleColor = Color.GRAY;
    private String mSubTitleStyle = FontWeight.NORMAL;

    private Element mRightImage;
    private int mRightImageWidth;
    private int mRightImagePaddingEnd;
    private int mRightImagePaddingStart;

    private String mRightText = "";
    private float mRightTextSize;
    private Color mRightTextColor = Color.BLACK;
    private int mRightTextPaddingEnd;
    private int mRightTextPaddingStart;
    private String mRightTextStyle = FontWeight.NORMAL;

    private int mLineHeight = 1;

    private boolean mIsBackView = true;
    private boolean mIsHaveLine = true;
    private boolean mIsImmersiveStateBar = false;

    private Color mLineBackground = Color.BLACK;
    private Color mLayoutBackground = Color.BLACK;

    private Image mIvLeft;
    private Text mTvLeft;
    private Text mTvTitle;
    private Text mTvSubTitle;
    private Image mIvRight;
    private Text mTvRight;
    private Component mViewLine;
    private DependentLayout mRlLayout;
    private DependentLayout mRlLeft;
    private DependentLayout mRlRight;
    private DirectionalLayout mLlCenter;

    private int mStatusBarHeight = 0;
    private Context mContext;

    private static final class FontWeight {
        static final String NORMAL = "normal";
        static final String BOLD = "bold";
    }

    /**
    * All attributes for title bar layout.
    */
    public static final class TitleBarLayoutAttrs {
        private TitleBarLayoutAttrs() {
            super();
        }

        static final String D_LEFT_IMAGE = "d_left_image";
        static final String D_LEFT_IMAGE_WIDTH = "d_left_image_width";
        static final String D_LEFT_IMAGE_PADDING_START = "d_left_image_padding_start";
        static final String D_LEFT_IMAGE_PADDING_END = "d_left_image_padding_end";

        static final String D_LEFT_TEXT = "d_left_text";
        static final String D_LEFT_TEXT_SIZE = "d_left_text_size";
        static final String D_LEFT_TEXT_COLOR = "d_left_text_color";
        static final String D_LEFT_TEXT_STYLE = "d_left_text_style";
        static final String D_LEFT_TEXT_PADDING_START = "d_left_text_padding_start";
        static final String D_LEFT_TEXT_PADDING_END = "d_left_text_padding_end";

        static final String D_TITLE_TEXT = "d_title_text";
        static final String D_TITLE_SIZE = "d_title_size";
        static final String D_TITLE_COLOR = "d_title_color";
        static final String D_TITLE_STYLE = "d_title_style";

        static final String D_SUBTITLE_TEXT = "d_subtitle_text";
        static final String D_SUBTITLE_SIZE = "d_subtitle_size";
        static final String D_SUBTITLE_COLOR = "d_subtitle_color";
        static final String D_SUBTITLE_STYLE = "d_subtitle_style";

        static final String D_RIGHT_TEXT = "d_right_text";
        static final String D_RIGHT_TEXT_COLOR = "d_right_text_color";
        static final String D_RIGHT_TEXT_SIZE = "d_right_text_size";
        static final String D_RIGHT_TEXT_STYLE = "d_right_text_style";
        static final String D_RIGHT_TEXT_PADDING_END = "d_right_text_padding_end";
        static final String D_RIGHT_TEXT_PADDING_START = "d_right_text_padding_start";

        static final String D_RIGHT_IMAGE = "d_right_image";
        static final String D_RIGHT_IMAGE_WIDTH = "d_right_image_width";
        static final String D_RIGHT_IMAGE_PADDING_END = "d_right_image_padding_end";
        static final String D_RIGHT_IMAGE_PADDING_START = "d_right_image_padding_start";

        static final String D_LINE_HEIGHT = "d_line_height";
        static final String D_LINE_BACKGROUND = "d_line_background";

        static final String D_TITLE_LAYOUT_HEIGHT = "d_title_layout_height";
        static final String D_TITLE_LAYOUT_BACKGROUND = "d_title_layout_background";
        static final String D_HAVE_LINE = "d_have_line";

        static final String D_IS_BACK_VIEW = "d_is_back_view";
        static final String D_IS_IMMERSIVE_STATE_BAR = "d_is_immersive_state_bar";
    }


    private static final HiLogLabel LOG_LABEL = new HiLogLabel(0, 0, "attrutils");


    public TitleBarLayout(Context context) {
        this(context, null);
    }

    public TitleBarLayout(Context context, AttrSet attrs) {
        this(context, attrs, null);
    }


    /**
     * Initialization.
     *
     * @param context context
     * @param attrs attrs
     * @param defStyleAttr defStyleAttr
     */
    public TitleBarLayout(Context context, AttrSet attrs, String defStyleAttr) {

        super(context, attrs, defStyleAttr);
        try {
            mLayoutBarHeight = TransitionTools.dip2px(context, 45);
            mLeftImageWidth = TransitionTools.dip2px(context, 30);
            mLeftImagePaddingStart = TransitionTools.dip2px(context, 10);
            mLeftImagePaddingEnd = TransitionTools.dip2px(context, 10);
            mLeftTextSize = TransitionTools.dip2px(context, 16);
            mLeftTextPaddingStart = TransitionTools.dip2px(context, 10);
            mLeftTextPaddingEnd = TransitionTools.dip2px(context, 10);
            mTitleSize = TransitionTools.dip2px(context, 18);
            mSubTitleSize = TransitionTools.dip2px(context, 12);
            mRightImageWidth = TransitionTools.dip2px(context, 30);
            mRightImagePaddingEnd = TransitionTools.dip2px(context, 10);
            mRightImagePaddingStart = TransitionTools.dip2px(context, 10);
            mRightTextSize = TransitionTools.dip2px(context, 16);
            mRightTextPaddingEnd = TransitionTools.dip2px(context, 10);
            mRightTextPaddingStart = TransitionTools.dip2px(context, 10);

            mContext = context;
            Component inflate = LayoutScatter.getInstance(context)
                    .parse(ResourceTable.Layout_title_bar_layout, null, false);
            addComponent(inflate);

            AttrUtils attrUtils = new AttrUtils(attrs);

            mLayoutBarHeight = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_TITLE_LAYOUT_HEIGHT, 0);
            mLayoutBackground = attrUtils.getColorFromAttr(TitleBarLayoutAttrs.D_TITLE_LAYOUT_BACKGROUND, Color.WHITE);

            /*
            左侧图片、图片大小、图片左边距
             */
            mLeftImage = attrUtils.getElementFromAttr(TitleBarLayoutAttrs.D_LEFT_IMAGE);
            mLeftImageWidth = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_LEFT_IMAGE_WIDTH, 0);
            mLeftImagePaddingStart = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_LEFT_IMAGE_PADDING_START, 0);
            mLeftImagePaddingEnd = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_LEFT_IMAGE_PADDING_END, 0);

            /*
            左侧文字、字体大小、字体颜色、字体左边距
            */
            mLeftText = attrUtils.getStringFromAttr(TitleBarLayoutAttrs.D_LEFT_TEXT, "");
            mLeftTextSize = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_LEFT_TEXT_SIZE, 0);
            mLeftTextPaddingStart = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_LEFT_TEXT_PADDING_START, 0);
            mLeftTextPaddingEnd = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_LEFT_TEXT_PADDING_END, 0);
            mLeftTextColor = attrUtils.getColorFromAttr(TitleBarLayoutAttrs.D_LEFT_TEXT_COLOR, Color.BLACK);
            mLeftTextStyle = attrUtils.getStringFromAttr(TitleBarLayoutAttrs.D_LEFT_TEXT_STYLE, mLeftTextStyle);

            /*
            标题文字、字体大小、字体颜色
             */
            mTitle = attrUtils.getStringFromAttr(TitleBarLayoutAttrs.D_TITLE_TEXT, "");
            mTitleSize = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_TITLE_SIZE, 80);
            mTitleColor = attrUtils.getColorFromAttr(TitleBarLayoutAttrs.D_TITLE_COLOR, Color.RED);
            mTitleStyle = attrUtils.getStringFromAttr(TitleBarLayoutAttrs.D_TITLE_STYLE, mTitleStyle);

            /*
            副标题
            */
            mSubTitle = attrUtils.getStringFromAttr(TitleBarLayoutAttrs.D_SUBTITLE_TEXT, "");
            mSubTitleSize = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_SUBTITLE_SIZE, 0);
            mSubTitleColor = attrUtils.getColorFromAttr(TitleBarLayoutAttrs.D_SUBTITLE_COLOR, Color.BLACK);
            mSubTitleStyle = attrUtils.getStringFromAttr(TitleBarLayoutAttrs.D_SUBTITLE_STYLE, mSubTitleStyle);

            /*
            右侧图片、图片大小、图片左边距
             */
            mRightImage = attrUtils.getElementFromAttr(TitleBarLayoutAttrs.D_RIGHT_IMAGE);
            mRightImageWidth = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_RIGHT_IMAGE_WIDTH, 0);
            mRightImagePaddingEnd = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_RIGHT_IMAGE_PADDING_END, 0);
            mRightImagePaddingStart = attrUtils
                    .getDimensionFromAttr(TitleBarLayoutAttrs.D_RIGHT_IMAGE_PADDING_START, 0);

            /*
            右侧文字、字体大小、字体颜色、字体左边距
             */
            mRightText = attrUtils.getStringFromAttr(TitleBarLayoutAttrs.D_RIGHT_TEXT, "");
            mRightTextSize = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_RIGHT_TEXT_SIZE, 0);
            mRightTextPaddingEnd = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_RIGHT_TEXT_PADDING_END, 0);
            mRightTextPaddingStart = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_RIGHT_TEXT_PADDING_START, 0);
            mRightTextColor = attrUtils.getColorFromAttr(TitleBarLayoutAttrs.D_RIGHT_TEXT_COLOR, Color.BLACK);
            mRightTextStyle = attrUtils.getStringFromAttr(TitleBarLayoutAttrs.D_RIGHT_TEXT_STYLE, mRightTextStyle);

            /*
            底部横线背景、高度
             */
            mLineBackground = attrUtils.getColorFromAttr(TitleBarLayoutAttrs.D_LINE_BACKGROUND, Color.BLACK);
            mLineHeight = attrUtils.getDimensionFromAttr(TitleBarLayoutAttrs.D_LINE_HEIGHT, 0);
            mIsHaveLine = attrUtils.getBooleanFromAttr(TitleBarLayoutAttrs.D_HAVE_LINE, true);

            /*
            左侧图标和文字是否为返回键
             */
            mIsBackView = attrUtils.getBooleanFromAttr(TitleBarLayoutAttrs.D_IS_BACK_VIEW, true);
            /*
            是否沉浸式状态栏
             */
            mIsImmersiveStateBar = attrUtils.getBooleanFromAttr(TitleBarLayoutAttrs.D_IS_IMMERSIVE_STATE_BAR, false);
            initView(inflate);
            initData();
        } catch (Exception ex) {
            HiLog.debug(LOG_LABEL, "" + ex);
            for (StackTraceElement st : ex.getStackTrace()) {
                HiLog.debug(LOG_LABEL, "" + st.toString());

            }
        }
    }

    private void initView(Component inflate) {
        mRlLayout = ((DependentLayout) inflate.findComponentById(ResourceTable.Id_sd_rl_title_bar_height));
        mRlLeft = ((DependentLayout) inflate.findComponentById(ResourceTable.Id_sd_rl_left));
        mRlRight = ((DependentLayout) inflate.findComponentById(ResourceTable.Id_sd_rl_right));
        mLlCenter = ((DirectionalLayout) inflate.findComponentById(ResourceTable.Id_sd_ll_center));
        mIvLeft = ((Image) inflate.findComponentById(ResourceTable.Id_sd_iv_left));
        mTvLeft = ((Text) inflate.findComponentById(ResourceTable.Id_sd_tv_left));
        mTvTitle = ((Text) inflate.findComponentById(ResourceTable.Id_sd_tv_title));
        mTvSubTitle = ((Text) inflate.findComponentById(ResourceTable.Id_sd_tv_sub_title));
        mIvRight = ((Image) inflate.findComponentById(ResourceTable.Id_sd_iv_right));
        mTvRight = ((Text) inflate.findComponentById(ResourceTable.Id_sd_tv_right));
        mViewLine = inflate.findComponentById(ResourceTable.Id_sd_view_line);
    }

    private void initData() {
        initLayoutHeight();
        ShapeElement shapeElement = new ShapeElement();
        shapeElement.setRgbColor(RgbColor.fromArgbInt(mLayoutBackground.getValue()));
        mRlLayout.setBackground(shapeElement);
        //左边图标
        settingLeftImage();

        //左边文字
        settingLeftText();

        //标题
        settingTitle();
        //副标题
        settingSubTitle();

        //右边图标
        settingRightImage();

        //右边文字
        settingRightText();

        // 如果是返回键，则点击实现页面返回，否则获取点击事件
        settingBackView();

        //横线
        settingLine();
    }

    private void initLayoutHeight() {
        if (mIsImmersiveStateBar) {
            int layoutHeight = mLayoutBarHeight + mStatusBarHeight;
            LayoutConfig rlLayoutLayoutParams =
                    new LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT, layoutHeight);
            rlLayoutLayoutParams.addRule(CENTER_IN_PARENT);
            mRlLayout.setLayoutConfig(rlLayoutLayoutParams);
            mRlLayout.setPadding(0, mStatusBarHeight, 0, 0);
        } else {
            LayoutConfig rlLayoutLayoutParams =
                    new LayoutConfig(ComponentContainer.LayoutConfig.MATCH_PARENT, mLayoutBarHeight);
            rlLayoutLayoutParams.addRule(CENTER_IN_PARENT);
            mRlLayout.setLayoutConfig(rlLayoutLayoutParams);
        }
    }

    private void settingLeftImage() {
        if (mLeftImage != null) {
            mIvLeft.setVisibility(VISIBLE);
            PixelMap pixelMap = ((PixelMapElement) mLeftImage).getPixelMap();
            mIvLeft.setPixelMap(pixelMap);

            //image size
            ComponentContainer.LayoutConfig ivLeftLayoutParams = mIvLeft.getLayoutConfig();
            ivLeftLayoutParams.width = mLeftImageWidth + mLeftImagePaddingStart + mLeftImagePaddingEnd;
            mIvLeft.setPadding(mLeftImagePaddingStart, 0, mLeftImagePaddingEnd, 0);
            mIvLeft.setLayoutConfig(ivLeftLayoutParams);
        } else {
            mIvLeft.setVisibility(HIDE);
        }

    }

    private void settingLeftText() {
        if (StringUtil.isEmpty(mLeftText)) {
            mTvLeft.setPadding(0, 0, 100, 0);
            mTvLeft.setVisibility(HIDE);
        } else {
            mTvLeft.setVisibility(VISIBLE);
            mTvLeft.setText(mLeftText);
            mTvLeft.setTextSize(TransitionTools.px2sp(mContext, mLeftTextSize));
            mTvLeft.setTextColor(mLeftTextColor);
            mTvLeft.setPadding(mLeftTextPaddingStart, 0, mLeftTextPaddingEnd, 0);
            switch (mLeftTextStyle) {
                case FontWeight.BOLD:
                    mTvLeft.setFont(Font.DEFAULT_BOLD);
                    break;
                case FontWeight.NORMAL:
                default:
                    mTvLeft.setFont(Font.DEFAULT);
                    break;
            }
        }
    }

    private void settingTitle() {
        if (StringUtil.isEmpty(mTitle)) {
            mTvTitle.setVisibility(INVISIBLE);
        } else {
            mTvTitle.setVisibility(VISIBLE);
            mTvTitle.setText(mTitle);
            mTvTitle.setTextSize(TransitionTools.px2sp(mContext, mTitleSize));
            mTvTitle.setTextColor(mTitleColor);
            switch (mTitleStyle) {
                case FontWeight.BOLD:
                    mTvTitle.setFont(Font.DEFAULT_BOLD);
                    break;
                case FontWeight.NORMAL:
                default:
                    mTvTitle.setFont(Font.DEFAULT);
                    break;
            }
        }
    }

    private void settingSubTitle() {
        if (StringUtil.isEmpty(mSubTitle)) {
            mTvSubTitle.setVisibility(HIDE);
        } else {
            mTvSubTitle.setVisibility(VISIBLE);
            mTvSubTitle.setText(mSubTitle);
            mTvSubTitle.setTextSize(TransitionTools.px2sp(mContext, mSubTitleSize));
            mTvSubTitle.setTextColor(mSubTitleColor);
            switch (mSubTitleStyle) {
                case FontWeight.BOLD:
                    mTvSubTitle.setFont(Font.DEFAULT_BOLD);
                    break;
                case FontWeight.NORMAL:
                default:
                    mTvSubTitle.setFont(Font.DEFAULT);
                    break;
            }
        }
    }

    private void settingRightImage() {
        if (mRightImage != null) {
            mIvRight.setVisibility(VISIBLE);
            PixelMap pixelMap = ((PixelMapElement) mRightImage).getPixelMap();
            mIvRight.setPixelMap(pixelMap);

            ComponentContainer.LayoutConfig ivRightLayoutParams = mIvRight.getLayoutConfig();
            ivRightLayoutParams.width = mRightImageWidth + mRightImagePaddingEnd + mRightImagePaddingStart;
            mIvRight.setPadding(mRightImagePaddingStart, 0, mRightImagePaddingEnd, 0);
            mIvRight.setLayoutConfig(ivRightLayoutParams);
        } else {
            mIvRight.setVisibility(HIDE);
        }
    }




    private void settingRightText() {
        if (StringUtil.isEmpty(mRightText)) {
            mTvRight.setVisibility(HIDE);
        } else {
            mTvRight.setVisibility(VISIBLE);
            mTvRight.setText(mRightText);
            mTvRight.setTextSize(TransitionTools.px2sp(mContext, mRightTextSize));
            mTvRight.setTextColor(mRightTextColor);
            mTvRight.setPadding(mRightTextPaddingStart, 0, mRightTextPaddingEnd, 0);
            switch (mRightTextStyle) {
                case FontWeight.BOLD:
                    mTvRight.setFont(Font.DEFAULT_BOLD);
                    break;
                case FontWeight.NORMAL:
                default:
                    mTvRight.setFont(Font.DEFAULT);
                    break;
            }
        }
    }

    private void settingBackView() {
        if (mIsBackView) {
            mIvLeft.setClickedListener(new ClickedListener() {
                @Override
                public void onClick(Component component) {
                    ((Ability) mContext).terminateAbility();
                }
            });

            mIvRight.setClickedListener(new ClickedListener() {
                @Override
                public void onClick(Component component) {
                    ((Ability) mContext).terminateAbility();
                }
            });
        }

    }

    private void settingLine() {
        if (!mIsHaveLine) {
            mViewLine.setVisibility(HIDE);
            return;
        }
        ShapeElement shapeElement = new ShapeElement();
        shapeElement.setRgbColor(RgbColor.fromArgbInt(mLineBackground.getValue()));
        mViewLine.setBackground(shapeElement);
        //横线高度
        if (mLineHeight != 0) {
            ComponentContainer.LayoutConfig viewLineLayoutParams = mViewLine.getLayoutConfig();
            viewLineLayoutParams.height = mLineHeight;
            mViewLine.setLayoutConfig(viewLineLayoutParams);
        }
    }

    /**
     * set the color of layout background.
     *
     * @param colorRes colorRes
     */
    public void setTitleLayoutBackground(Color colorRes) {
        ShapeElement shapeElement = new ShapeElement();
        shapeElement.setRgbColor(RgbColor.fromArgbInt(colorRes.getValue()));
        mRlLayout.setBackground(shapeElement);
    }

    /**
     * 设置title.
     *
     * @param title title
     */
    public void setTitle(String title) {
        if (!StringUtil.isEmpty(title)) {
            this.mTitle = title;
            settingTitle();
        }
    }

    /**
     * 设置标题大小.
     *
     * @param titleSize titleSize
     */
    public void setTitleSize(int titleSize) {
        if (titleSize != 0) {
            mTvTitle.setTextSize(titleSize);
        }
    }

    /**
     * 设置标题颜色.
     *
     * @param titleColor titleColor
     */
    public void setTitleColor(int titleColor) {
        if (titleColor != 0) {
            ShapeElement shapeElement = new ShapeElement();
            shapeElement.setRgbColor(RgbColor.fromArgbInt(titleColor));
            mTvTitle.setBackground(shapeElement);
        }
    }

    /**
     * 一键设置标题样式、资源等.
     *
     * @param title title
     * @param titleSize titleSize
     * @param titleColor titleColor
     */
    public void setTitleStyle(String title, int titleSize, int titleColor) {
        this.mTitle = title;
        this.mTitleSize = TransitionTools.dip2px(mContext, titleSize);
        Color color = new Color(titleColor);
        this.mTitleColor = color;
        settingTitle();
    }

    /**
     * 设置副title.
     *
     * @param subTitle subTitle
     */
    public void setSubTitle(String subTitle) {
        if (!StringUtil.isEmpty(subTitle)) {
            this.mSubTitle = subTitle;
            settingSubTitle();
        }
    }

    /**
     * 设置副标题大小.
     *
     * @param subTitleSize subTitleSize
     */
    public void setSubTitleSize(int subTitleSize) {
        if (subTitleSize != 0) {
            mTvSubTitle.setVisibility(VISIBLE);
            mTvSubTitle.setTextSize(subTitleSize);
        }
    }

    /**
     * 设置副标题颜色.
     *
     * @param subTitleColor subTitleColor
     */
    public void setSubTitleColor(int subTitleColor) {
        if (subTitleColor != 0) {
            mTvSubTitle.setVisibility(VISIBLE);
            Color color = new Color(subTitleColor);
            mTvSubTitle.setTextColor(color);
        }
    }

    /**
     * 一键设置副标题样式、资源等.
     *
     * @param subTitle subTitle
     * @param subTitleSize subTitleSize
     * @param subTitleColor subTitleColor
     */
    public void setSubTitleStyle(String subTitle, int subTitleSize, int subTitleColor) {
        this.mSubTitle = subTitle;
        this.mSubTitleSize = TransitionTools.dip2px(mContext, subTitleSize);
        Color color = new Color(subTitleColor);
        this.mSubTitleColor = color;

        if (!StringUtil.isEmpty(subTitle) && subTitleSize != 0 && subTitleColor != 0) {
            settingSubTitle();
        }
    }

    /**
     * 设置左边文字内容.
     *
     * @param leftText leftText
     */
    public void setLeftText(String leftText) {
        if (!StringUtil.isEmpty(leftText)) {
            this.mLeftText = leftText;
            settingLeftText();
        }
    }

    /**
     * 设置左边文字大小.
     *
     * @param leftTextSize leftTextSize
     */
    public void setLeftTextSize(int leftTextSize) {
        if (leftTextSize != 0) {
            mTvLeft.setTextSize(leftTextSize);
        }
    }

    /**
     * 设置左边文字颜色.
     *
     * @param leftTextColor leftTextColor
     */
    public void setLeftTextColor(int leftTextColor) {
        if (leftTextColor != 0) {
            Color color = new Color(leftTextColor);
            mTvLeft.setTextColor(color);
        }
    }

    /**
     * 一键设置左侧文字样式、资源等.
     *
     * @param leftText leftText
     * @param leftTextSize leftTextSize
     * @param leftTextColor leftTextColor
     */
    public void setLeftStyle(String leftText, int leftTextSize, int leftTextColor) {

        if (!StringUtil.isEmpty(leftText) && leftTextSize != 0 && leftTextColor != 0) {
            this.mLeftText = leftText;
            this.mLeftTextSize = TransitionTools.dip2px(mContext, leftTextSize);
            Color color = new Color(leftTextColor);
            this.mLeftTextColor = color;
            settingLeftText();
        }
    }

    /**
     * 设置右侧文字.
     *
     * @param rightText rightText
     */
    public void setRightText(String rightText) {
        if (!StringUtil.isEmpty(rightText)) {
            this.mRightText = rightText;
            settingRightText();
        }
    }

    /**
     * 设置右侧文字颜色.
     *
     * @param rightText rightText
     * @param rightTextColor rightTextColor
     */
    public void setRightText(String rightText, int rightTextColor) {
        if (!StringUtil.isEmpty(rightText) && rightTextColor != 0) {
            this.mRightText = rightText;
            Color color = new Color(rightTextColor);
            this.mRightTextColor = color;
            settingRightText();
        }
    }

    /**
     * 设置右侧文字大小.
     *
     * @param rightTextSize rightTextSize
     */
    public void setRightTextSize(int rightTextSize) {
        if (rightTextSize != 0) {
            mTvRight.setTextSize(rightTextSize);
        }
    }

    /**
     * 设置右侧文字颜色.
     *
     * @param rightTextColor rightTextColor
     */
    public void setRightTextColor(int rightTextColor) {
        if (rightTextColor != 0) {
            Color color = new Color(rightTextColor);
            mTvRight.setTextColor(color);
        }
    }

    /**
     * 设置右侧文字.
     *
     * @param rightText rightText
     * @param rightTextSize rightTextSize
     * @param rightTextColor rightTextColor
     */
    public void setRightTextStyle(String rightText, int rightTextSize, int rightTextColor) {

        if (!StringUtil.isEmpty(rightText) && rightTextSize != 0 && rightTextColor != 0) {
            this.mRightText = rightText;
            this.mRightTextSize = TransitionTools.dip2px(mContext, rightTextSize);
            Color color = new Color(rightTextColor);
            this.mRightTextColor = color;
            settingRightText();
        }
    }

    /**
     * 设置左边图片资源.
     *
     * @param leftImageRes leftImageRes
     */
    public void setLeftImage(int leftImageRes) {
        mIvLeft.setVisibility(VISIBLE);
        mIvLeft.setPixelMap(leftImageRes);

        //image size
        ComponentContainer.LayoutConfig ivLeftLayoutParams = mIvLeft.getLayoutConfig();
        ivLeftLayoutParams.width = mLeftImageWidth + mLeftImagePaddingStart + mLeftImagePaddingEnd;
        mIvLeft.setPadding(mLeftImagePaddingStart, 0, mLeftImagePaddingEnd, 0);
        mIvLeft.setLayoutConfig(ivLeftLayoutParams);
    }

    /**
     * 设置右边图片资源.
     *
     * @param rightImageRes rightImageRes
     */
    public void setRightImage(int rightImageRes) {
        mIvRight.setVisibility(VISIBLE);
        mIvRight.setPixelMap(rightImageRes);

        ComponentContainer.LayoutConfig ivRightLayoutParams = mIvRight.getLayoutConfig();
        ivRightLayoutParams.width = mRightImageWidth + mRightImagePaddingEnd + mRightImagePaddingStart;
        mIvRight.setPadding(mRightImagePaddingStart, 0, mRightImagePaddingEnd, 0);
        mIvRight.setLayoutConfig(ivRightLayoutParams);
    }

    /**
     * 1.0.1之后增加动态设置是否为沉浸式状态栏.
     *
     * @param isImmersiveStateBar isImmersiveStateBar
     */
    public void setIsImmersiveStateBar(boolean isImmersiveStateBar) {
        mIsImmersiveStateBar = isImmersiveStateBar;
        initLayoutHeight();
    }

    /**
     * 设置是否底部有横线.
     *
     * @param haveLine haveLine
     */
    public void setIsHaveLine(boolean haveLine) {
        this.mIsHaveLine = haveLine;
        settingLine();
    }

    /**
     * 标题点击事件.
     *
     * @param listener listner
     */
    public void setTitleClickListener(ClickedListener listener) {
        mTvTitle.setClickedListener(listener);
    }

    /**
     * 标题增加单击双击事件.
     *
     * @param onMultiTouchListener onMultiTouchListener
     */
    public void setTitleClickListener(OnMultiTouchListener onMultiTouchListener) {
        mTvTitle.setTouchEventListener(onMultiTouchListener);
    }

    /**
     * 副标题点击事件.
     *
     * @param listener listner
     */
    public void setSubTitleClickListener(ClickedListener listener) {
        mTvSubTitle.setClickedListener(listener);
    }

    /**
     * 左边按钮点击事件.
     *
     * @param listener listner
     */
    public void setLeftClickListener(ClickedListener listener) {
        mIvLeft.setClickedListener(listener);
        mTvLeft.setClickedListener(listener);
    }

    /**
     * 右边文字按钮点击事件.
     *
     * @param listener listner
     */
    public void setRightTextClickListener(ClickedListener listener) {
        mTvRight.setClickedListener(listener);
    }

    /**
     * 右边图片按钮点击事件.
     *
     * @param listener listner
     */
    public void setRightImageClickListener(ClickedListener listener) {
        mIvRight.setClickedListener(listener);
    }

    /**
     * 设置左侧文字和按钮是否为返回按钮.
     *
     * @param isLeftBackView isLeftBackView
     */
    public void setIsLeftBackView(boolean isLeftBackView) {
        mIsBackView = isLeftBackView;
        if (!mIsBackView) {
            return;
        }
        if (mLeftImage != null) {
            mIvLeft.setClickedListener(new ClickedListener() {
                @Override
                public void onClick(Component component) {
                    ((Ability) mContext).terminateAbility();
                }
            });
        }
        if (StringUtil.isEmpty(mLeftText)) {
            mIvLeft.setClickedListener(new ClickedListener() {
                @Override
                public void onClick(Component component) {
                    ((Ability) mContext).terminateAbility();
                }
            });
        }
    }

    @Override
    public boolean onEstimateSize(int widthMeasureSpec, int heightMeasureSpec) {
        int rlLeftMeasuredWidth = mRlLeft.getEstimatedWidth();
        int rlRightMeasuredWidth = mRlRight.getEstimatedWidth();
        if (rlLeftMeasuredWidth > rlRightMeasuredWidth) {
            mLlCenter.setPadding(rlLeftMeasuredWidth, 0, rlLeftMeasuredWidth, 0);
        } else {
            mLlCenter.setPadding(rlRightMeasuredWidth, 0, rlRightMeasuredWidth, 0);
        }
        return true;
    }


    /**
     * 左侧返回按钮隐藏.
     *
     * @param visibility visibility
     */
    public void setLeftBackViewVisible(int visibility) {
        mIvLeft.setVisibility(visibility);
    }

    /**
     * 设置左侧文字显示隐藏.
     *
     * @param visibility visibility
     */
    public void setLeftTextViewVisible(int visibility) {
        mTvLeft.setVisibility(visibility);
    }

    /**
     * 右侧文字设置显示隐藏.
     *
     * @param visible visible
     */
    public void setRightTextViewVisible(int visible) {
        mTvRight.setVisibility(visible);
    }

    /**
     * 右侧图标设置显示隐藏.
     *
     * @param visible visible
     */
    public void setRightImageViewVisible(int visible) {
        mIvRight.setVisibility(visible);
    }

    /**
     * 设置标题显示隐藏.
     *
     * @param visible visible
     */
    public void setTitleVisible(int visible) {
        mTvTitle.setVisibility(visible);
    }

    /**
     * 设置副标题显示隐藏.
     *
     * @param visible visible
     */
    public void setSubTitleVisible(int visible) {
        mTvSubTitle.setVisibility(visible);
    }

    /**
     * 设置左侧文字是否可以点击.
     *
     * @param isClickable isClickable
     */
    public void setLeftTextClickable(boolean isClickable) {
        mTvLeft.setClickable(isClickable);
        mTvLeft.setFocusable(isClickable ? FOCUS_ENABLE : FOCUS_DISABLE);
    }

    /**
     * 设置左侧图片是否可以点击.
     *
     * @param isClickable isClickable
     */
    public void setLeftImageClickable(boolean isClickable) {
        mIvLeft.setClickable(isClickable);
        mIvLeft.setFocusable(isClickable ? FOCUS_ENABLE : FOCUS_DISABLE);
    }

    /**
     * 设置右侧文字是否可以点击.
     *
     * @param isClickable isClickable
     */
    public void setRightTextClickable(boolean isClickable) {
        mTvRight.setClickable(isClickable);
        mTvRight.setFocusable(isClickable ? FOCUS_ENABLE : FOCUS_DISABLE);
    }

    /**
     * 设置右侧图片是否可以点击.
     *
     * @param isClickable isClickable
     */
    public void setRightImageClickable(boolean isClickable) {
        mIvRight.setClickable(isClickable);
        mIvRight.setFocusable(isClickable ? FOCUS_ENABLE : FOCUS_DISABLE);
    }

}
