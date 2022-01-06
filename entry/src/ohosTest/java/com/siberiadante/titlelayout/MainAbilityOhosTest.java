package com.siberiadante.titlelayout;

import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.components.Component;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;
import static ohos.agp.components.Component.HIDE;
import static ohos.agp.components.Component.VISIBLE;
import static org.junit.Assert.assertEquals;


public class MainAbilityOhosTest {
    TitleBarLayout titleBarLayout;
    Text  mTvTitle;
    Text mTvSubTitle;
    Text mTvLeft;
    Text mTvRight;
    Image mIvLeft;
    Image mIvRight;
    Component mViewLine;
    DependentLayout mRlLayout;


    @Before
    public void setUp() {
        Context context = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();

        titleBarLayout = new TitleBarLayout(context);
        mTvTitle = ((Text) titleBarLayout.findComponentById(ResourceTable.Id_sd_tv_title));
        mTvSubTitle = ((Text) titleBarLayout.findComponentById(ResourceTable.Id_sd_tv_sub_title));
        mTvLeft = ((Text) titleBarLayout.findComponentById(ResourceTable.Id_sd_tv_left));
        mTvRight = ((Text) titleBarLayout.findComponentById(ResourceTable.Id_sd_tv_right));
        mIvLeft = ((Image) titleBarLayout.findComponentById(ResourceTable.Id_sd_iv_left));
        mViewLine = titleBarLayout.findComponentById(ResourceTable.Id_sd_view_line);
        mIvRight = ((Image) titleBarLayout.findComponentById(ResourceTable.Id_sd_iv_right));
        mRlLayout = ((DependentLayout) titleBarLayout.findComponentById(ResourceTable.Id_sd_rl_title_bar_height));
    }

    @Test
    public void testSetTitle() {
        titleBarLayout.setTitle("Edited Title");
        assertEquals("Edited Title", mTvTitle.getText());
    }

    @Test
    public void testSetTitleSize() {
        titleBarLayout.setTitleSize(60);
        assertEquals(60, mTvTitle.getTextSize());
    }

    @Test
    public void testSetTitleColor() {
        titleBarLayout.setTitleColor(Color.WHITE.getValue());
        assertEquals(Color.WHITE, mTvTitle.getTextColor());
    }

    @Test
    public void testSetTitleStyle() {
        titleBarLayout.setTitleStyle("Edited Title", 50, Color.WHITE.getValue());
        assertEquals("Edited Title", mTvTitle.getText());
        assertEquals(50, mTvTitle.getTextSize());
        assertEquals(Color.WHITE, mTvTitle.getTextColor());
    }

    @Test
    public void testSetSubTitle() {
        titleBarLayout.setSubTitle("Edited Subtitle");
        assertEquals("Edited Subtitle", mTvSubTitle.getText());
    }

    @Test
    public void testSetSubTitleSize() {
        titleBarLayout.setSubTitleSize(30);
        assertEquals(30, mTvSubTitle.getTextSize());
    }

    @Test
    public void testSetSubTitleColor() {
        titleBarLayout.setSubTitleColor(Color.WHITE.getValue());
        assertEquals(Color.WHITE, mTvSubTitle.getTextColor());
    }

    @Test
    public void testSetSubTitleStyle() {
        titleBarLayout.setSubTitleStyle("Edited Subtitle", 30, Color.WHITE.getValue());
        assertEquals("Edited Subtitle", mTvSubTitle.getText());
        assertEquals(30, mTvSubTitle.getTextSize());
        assertEquals(Color.WHITE, mTvSubTitle.getTextColor());
    }

    @Test
    public void testSetLeftText() {
        titleBarLayout.setLeftText("previous");
        assertEquals("previous", mTvLeft.getText());
    }

    @Test
    public void testSetLeftTextSize() {
        titleBarLayout.setLeftTextSize(30);
        assertEquals(30, mTvLeft.getTextSize());
    }

    @Test
    public void testSetLeftTextColor() {
        titleBarLayout.setLeftTextColor(Color.WHITE.getValue());
        assertEquals(Color.WHITE, mTvLeft.getTextColor());
    }

    @Test
    public void testSetLeftStyle() {
        titleBarLayout.setLeftStyle("previous", 30, Color.WHITE.getValue());
        assertEquals("previous", mTvLeft.getText());
        assertEquals(30, mTvLeft.getTextSize());
        assertEquals(Color.WHITE, mTvLeft.getTextColor());
    }

    @Test
    public void testSetRightText() {
        titleBarLayout.setRightText("done");
        assertEquals("done", mTvRight.getText());
    }

    @Test
    public void testSetRightTextSize() {
        titleBarLayout.setRightTextSize(30);
        assertEquals(30, mTvRight.getTextSize());
    }

    @Test
    public void testSetRightTextColor() {
        titleBarLayout.setRightTextColor(Color.WHITE.getValue());
        assertEquals(Color.WHITE, mTvRight.getTextColor());
    }


    @Test
    public void testSetIsHaveLine() {
        titleBarLayout.setIsHaveLine(true);
        assertEquals(VISIBLE, mViewLine.getVisibility());
        titleBarLayout.setIsHaveLine(false);
        assertEquals(HIDE, mViewLine.getVisibility());
    }

    @Test
    public void testSetLeftBackViewVisible() {
        titleBarLayout.setLeftBackViewVisible(VISIBLE);
        assertEquals(VISIBLE, mIvLeft.getVisibility());
        titleBarLayout.setLeftBackViewVisible(HIDE);
        assertEquals(HIDE, mIvLeft.getVisibility());
    }

    @Test
    public void testSetLeftTextViewVisible() {
        titleBarLayout.setLeftTextViewVisible(VISIBLE);
        assertEquals(VISIBLE, mTvLeft.getVisibility());
        titleBarLayout.setLeftTextViewVisible(HIDE);
        assertEquals(HIDE, mTvLeft.getVisibility());
    }

    @Test
    public void testSetRightImageViewVisible() {
        titleBarLayout.setRightImageViewVisible(VISIBLE);
        assertEquals(VISIBLE, mIvRight.getVisibility());
        titleBarLayout.setRightImageViewVisible(HIDE);
        assertEquals(HIDE, mIvRight.getVisibility());
    }

    @Test
    public void testSetRightTextViewVisible() {
        titleBarLayout.setRightTextViewVisible(VISIBLE);
        assertEquals(VISIBLE, mTvRight.getVisibility());
        titleBarLayout.setRightTextViewVisible(HIDE);
        assertEquals(HIDE, mTvRight.getVisibility());
    }

    @Test
    public void testSetTitleVisible() {
        titleBarLayout.setTitleVisible(VISIBLE);
        assertEquals(VISIBLE, mTvTitle.getVisibility());
        titleBarLayout.setTitleVisible(HIDE);
        assertEquals(HIDE, mTvTitle.getVisibility());
    }

    @Test
    public void testSetSubTitleVisible() {
        titleBarLayout.setSubTitleVisible(VISIBLE);
        assertEquals(VISIBLE, mTvSubTitle.getVisibility());
        titleBarLayout.setSubTitleVisible(HIDE);
        assertEquals(HIDE, mTvSubTitle.getVisibility());
    }
}
