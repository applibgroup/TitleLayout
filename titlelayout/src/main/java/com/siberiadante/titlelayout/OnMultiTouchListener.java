package com.siberiadante.titlelayout;

import ohos.agp.components.Component;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.multimodalinput.event.TouchEvent;


/**
 * OnMultiTouchListener.
 */
public class OnMultiTouchListener implements Component.TouchEventListener {
    private int clickCount = 0;
    private TitleLayoutListener titleLayoutListener;
    private EventHandler mHandler;

    public OnMultiTouchListener(TitleLayoutListener titleLayoutListener) {
        this.titleLayoutListener = titleLayoutListener;
        mHandler = new EventHandler(EventRunner.current());
    }


    @Override
    public boolean onTouchEvent(Component component, TouchEvent touchEvent) {
        if (touchEvent.getAction() == TouchEvent.PRIMARY_POINT_DOWN) {
            clickCount++;
            mHandler.postTask(() -> {
                if (clickCount == 1) {
                    titleLayoutListener.onTitleClickListener();
                } else if (clickCount == 2) {
                    titleLayoutListener.onTitleDoubleClickListener();
                }
                mHandler.removeAllEvent();
                clickCount = 0;

            }, 500);
        }
        return false;
    }
}