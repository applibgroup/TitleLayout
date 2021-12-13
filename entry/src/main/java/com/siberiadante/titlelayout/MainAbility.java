package com.siberiadante.titlelayout;


import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.utils.Color;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLogLabel;

/**
 * MainAbility.
 */
public class MainAbility extends Ability {


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_main_ability);


        final TitleBarLayout titleBarLayout2 = (TitleBarLayout) findComponentById(ResourceTable.Id_title_layout_two);

        titleBarLayout2.setTitle("Java code to set a new title");


        final TitleBarLayout titleBarLayout = (TitleBarLayout) findComponentById(ResourceTable.Id_title_layout_four);
        titleBarLayout.setIsLeftBackView(true);
        titleBarLayout.setTitleLayoutBackground(Color.BLUE);


        titleBarLayout.setRightImageClickListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component view) {
                new ToastDialog(MainAbility.this).setText("The picture on the right was clicked").show();
            }
        });


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
    }
}
