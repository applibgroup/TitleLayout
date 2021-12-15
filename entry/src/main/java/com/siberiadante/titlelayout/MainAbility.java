package com.siberiadante.titlelayout;


import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.utils.Color;
import ohos.agp.window.dialog.ToastDialog;

/**
 * MainAbility.
 */
public class MainAbility extends Ability {


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_main_ability);



        final TitleBarLayout titleBarLayout = (TitleBarLayout) findComponentById(ResourceTable.Id_title_layout_four);
        titleBarLayout.setTitle("Edited Title");
        titleBarLayout.setTitleSize(50);
        titleBarLayout.setTitleColor(Color.WHITE.getValue());
        titleBarLayout.setTitleStyle("Edited Title", 50, Color.WHITE.getValue());
        titleBarLayout.setSubTitle("Edited Subtitle");
        titleBarLayout.setSubTitleSize(30);
        titleBarLayout.setSubTitleColor(Color.WHITE.getValue());
        titleBarLayout.setSubTitleStyle("Edited Subtitle", 30, Color.WHITE.getValue());
        titleBarLayout.setLeftText("previous");
        titleBarLayout.setLeftTextSize(30);
        titleBarLayout.setLeftTextColor(Color.WHITE.getValue());
        titleBarLayout.setLeftStyle("previous", 30, Color.WHITE.getValue());
        titleBarLayout.setRightText("done");
        titleBarLayout.setRightTextSize(30);
        titleBarLayout.setRightTextColor(Color.WHITE.getValue());
        titleBarLayout.setLeftImage(ResourceTable.Media_back_white);
        titleBarLayout.setRightImage(ResourceTable.Media_done_white);
        titleBarLayout.setIsLeftBackView(true);
        titleBarLayout.setIsHaveLine(true);
        titleBarLayout.setLeftBackViewVisible(Component.VISIBLE);
        titleBarLayout.setLeftTextViewVisible(Component.VISIBLE);
        titleBarLayout.setRightImageViewVisible(Component.VISIBLE);
        titleBarLayout.setRightTextViewVisible(Component.VISIBLE);
        titleBarLayout.setTitleVisible(Component.VISIBLE);
        titleBarLayout.setSubTitleVisible(Component.VISIBLE);
        titleBarLayout.setTitleLayoutBackground(Color.BLUE);

        titleBarLayout.setTitleClickListener(new OnMultiTouchListener(new TitleLayoutListener() {
            @Override
            public void onTitleClickListener() {
                new ToastDialog(MainAbility.this).setText("Title was clicked").show();
            }

            @Override
            public void onTitleDoubleClickListener() {
                new ToastDialog(MainAbility.this).setText("Title was double clicked").show();
            }
        }));
        titleBarLayout.setLeftClickListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                new ToastDialog(MainAbility.this).setText("The button on the left was clicked").show();
            }
        });
        titleBarLayout.setRightTextClickListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                new ToastDialog(MainAbility.this).setText("The text on the right was clicked").show();
            }
        });
        titleBarLayout.setRightImageClickListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component view) {
                new ToastDialog(MainAbility.this).setText("The picture on the right was clicked").show();
            }
        });

    }
}
