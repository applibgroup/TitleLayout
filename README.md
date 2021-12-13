# TitleLayout

Multi-functional, universal, can be implemented in the layout or using Java code to achieve the title bar;  support the left back button (no need to manually implement page return), the left side supports pictures + text, pictures, text; the right side Support pictures, text, etc.



![picture](/images/titleLayout.png)


### Source
---
This library has been inspired by [SiberiaDante/TitleLayout](https://github.com/SiberiaDante/TitleLayout)



### Integration
---

**From Source**
1. For using TitleLayout module in sample app, include the source code and add the below dependencies in entry/build.gradle to generate hap/support.har.
    ```groovy
    implementation project(path: ':titlelayout')
    ```
2. For using TitleLayout module in separate application using har file, add the har file in the entry/libs folder and add the dependencies in entry/build.gradle file.
    ```groovy
   implementation fileTree(dir: 'libs', include: ['*.har'])
   ```

## Use in layout：

```xml
<com.siberiadante.titlelayoutlib.TitleBarLayout
        ohos:id="$+id:title_layout_one"
        ohos:layout_width="match_parent"
        ohos:layout_height="match_content"
        dante:d_is_back_view="true"
        dante:d_left_image="$media:back_gray"
        dante:d_left_image_padding_start="10vp"
        dante:d_left_image_width="30vp"
        dante:d_left_text="back"
        dante:d_left_text_style="normal"
        dante:d_left_text_color="$color:black"
        dante:d_left_text_padding_start="10vp"
        dante:d_left_text_size="45fp"
        dante:d_line_height="1px"
        dante:d_right_text="submit"
        dante:d_right_text_size="45fp"
        dante:d_right_text_style="normal"
        dante:d_right_text_color="$color:white"
        dante:d_title_size="50vp"
        dante:d_title_style="bold"
        dante:d_title_text="Here is title"
        dante:d_title_color="$color:black"
        dante:d_subtitle_size="11fp"
        dante:d_subtitle_style="normal"
        dante:d_subtitle_text="subtitle"
        dante:d_subtitle_color="$color:gray"
        dante:d_title_layout_background="$color:red"
        dante:d_title_layout_height="70vp"/>
```

## Use Java code：

```java
 	final TitleBarLayout titleBarLayout = (TitleBarLayout) findComponentById(ResourceTable.Id_title_layout_one);
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
```

## Key note：

* If the left text or button is the return key, just use the following code in the layout：

  ```
  dante:d_is_back_view="true"
  ```
* Or in Java code:

  ```
  titleLayout.setIsLeftBackView(true)
  ```
* There is no need to set onClickListener, this.finish, etc.; if you have special needs, you can set the attribute to false to implement the method：

  ```
   setLeftClickListener(OnClickListener listener)
  ```

#### Other attribute description

#### Attributes in xml：

<table>
        <tr>
            <td>xml Attributes</td>
            <td>Property description</td>
        </tr>
        <tr>
            <td>d_left_image</td>
            <td>Left image resources</td>
        </tr>
        <tr>
            <td>d_left_image_width</td>
            <td>Left picture widte (high to match the father layout)</td>
        </tr>
        <tr>
            <td>d_left_image_padding_start</td>
            <td>Left picture left margins</td>
        </tr>
        <tr>
            <td>d_left_image_padding_end</td>
            <td>Left picture right margins</td>
        </tr>
        <tr>
            <td>d_left_text</td>
            <td>Left text resources</td>
        </tr>
        <tr>
            <td>d_left_text_size</td>
            <td>Left text size</td>
        </tr>
        <tr>
            <td>d_left_text_color</td>
            <td>Left text color (default black)</td>
        </tr>
        <tr>
            <td>d_left_text_style</td>
            <td>Set text style (Normal | Bold)</td>
        </tr>
        <tr>
            <td>d_left_text_padding_start</td>
            <td>Left word left margins</td>
        </tr>
        <tr>
            <td>d_left_text_padding_end</td>
            <td>Left word right margins</td>
        </tr>
        <tr>
            <td>d_title_text</td>
            <td>Intermediate header text resources</td>
        </tr>
        <tr>
            <td>d_title_size</td>
            <td>Intermediate title text size</td>
        </tr>
        <tr>
            <td>d_title_color</td>
            <td>Intermediate header text color (default black)</td>
        </tr>
        <tr>
            <td>d_title_style</td>
            <td>Set text style (normal | bold)</td>
        </tr>
        <tr>
            <td>d_subtitle_text</td>
            <td>Intermediate subtitle text resources</td>
        </tr>
        <tr>
            <td>d_subtitle_size</td>
            <td>Intermediate subtitle text size</td>
        </tr>
        <tr>
            <td>d_subtitle_color</td>
            <td>Intermediate subtitle text color (default black)</td>
        </tr>
        <tr>
            <td>d_subtitle_style</td>
            <td>Set text style (normal | bold)</td>
        </tr>
        <tr>
            <td>d_right_text</td>
            <td>Right text resources</td>
        </tr>
        <tr>
            <td>d_right_text_color</td>
            <td>Right text color</td>
        </tr>
        <tr>
            <td>d_right_text_size</td>
            <td>Right text size</td>
        </tr>
        <tr>
            <td>d_right_text_style</td>
            <td>Set text style (bormal | bold)</td>
        </tr>
        <tr>
            <td>d_right_text_padding_end</td>
            <td>Right text distance from the right</td>
        </tr>
        <tr>
            <td>d_right_text_padding_start</td>
            <td>Right text distance from the left</td>
        </tr>
        <tr>
            <td>d_right_image</td>
            <td>Right image resources</td>
        </tr>
        <tr>
            <td>d_right_image_width</td>
            <td>Width of the right image</td>
        </tr>
        <tr>
            <td>d_right_image_padding_end</td>
            <td>Right image distance from the right</td>
        </tr>
        <tr>
            <td>d_right_image_padding_start</td>
            <td>Right image distance from the left</td>
        </tr>
        <tr>
            <td>d_line_height</td>
            <td>The bottom horizontal line height of the title column (recommended units using PX)</td>
        </tr>
        <tr>
            <td>d_line_background</td>
            <td>Title bar bottom horizontal line background color (default black)</td>
        </tr>
        <tr>
            <td>d_title_layout_height</td>
            <td>Title bar overall height</td>
        </tr>
        <tr>
            <td>d_title_layout_background</td>
            <td>Title bar overall background color</td>
        </tr>
        <tr>
            <td>d_is_back_view</td>
            <td>Whether the left text and the image is a return button, if it is returned, come back to the previous page function</td>
        </tr>
</table>

---

#### Java code

<table>
        <tr>
            <td>Java code method</td>
            <td>Java code method description</td>
        </tr>
        <tr>
            <td>setTitle(CharSequence title)</td>
            <td>Set the title resource</td>
        </tr>
        <tr>
            <td>setTitleSize(int titleSize)</td>
            <td>Set the title font size</td>
        </tr>
        <tr>
            <td>setTitleColor(int titleColor)</td>
            <td>Set the title font color</td>
        </tr>
        <tr>
            <td>setTitleStyle(String title, int titleSize, int titleColor)</td>
            <td>Set the title resource, text size, text color</td>
        </tr>
        <tr>
            <td>setSubTitle(CharSequence title)</td>
            <td>Set the subtitle resource</td>
        </tr>
        <tr>
            <td>setSubTitleSize(int titleSize)</td>
            <td>Set the subtitle font size</td>
        </tr>
        <tr>
            <td>setSubTitleColor(int titleColor)</td>
            <td>Set the subtitle font color</td>
        </tr>
        <tr>
            <td>setSubTitleStyle(String title, int titleSize, int titleColor)</td>
            <td>Set the subtitle resource, text size, text color</td>
        </tr>
        <tr>
            <td>setLeftText(String leftText)</td>
            <td>Set the left text</td>
        </tr>
        <tr>
            <td>setLeftTextSize(int leftTextSize)</td>
            <td>Set the size of the word left</td>
        </tr>
        <tr>
            <td>setLeftTextColor(int leftTextColor)</td>
            <td>Set the left text color</td>
        </tr>
        <tr>
            <td>setLeftStyle(String leftText, int leftTextSize, int leftTextColor)</td>
            <td>Set the left text, text size, text color</td>
        </tr>
        <tr>
            <td>setRightText(String rightText)</td>
            <td>Set the right text</td>
        </tr>
        <tr>
            <td>setRightTextSize(int rightTextSize)</td>
            <td>Set the right text size</td>
        </tr>
        <tr>
            <td>setRightTextColor(int rightTextColor)</td>
            <td>Set the right text color</td>
        </tr>
        <tr>
            <td>setTitleClickListener(OnClickListener listener)</td>
            <td>Title Click to listen</td>
        </tr>
        <tr>
            <td>setTitleClickListener(OnMultiTouchListener listener)</td>
            <td>Set the title Click / Double-click Event</td>
        </tr>
        <tr>
            <td>setLeftClickListener(OnClickListener listener)</td>
            <td>On the left and buttons, click on the monitor</td>
        </tr>
        <tr>
            <td>setRightTextClickListener(OnClickListener listener)</td>
            <td>Right side text Click to listen</td>
        </tr>
        <tr>
            <td>setRightImageClickListener(OnClickListener listener)</td>
            <td>Right button Click to listen</td>
        </tr>
        <tr>
            <td>setIsLeftBackView(boolean isLeftBackView)</td>
            <td>Set whether the left text and buttons are returned button</td>
        </tr>
        <tr>
            <td>setIsHaveLine(boolean haveLine)</td>
            <td>Set whether there is a split line at the bottom of the title bar</td>
        </tr>
        <tr>
            <td>setLeftBackViewVisible</td>
            <td>Set the left icon to display or hidden</td>
        </tr>
        <tr>
            <td>setLeftTextViewVisible</td>
            <td>Set the left text display or hidden</td>
        </tr>
        <tr>
            <td>setRightTextViewVisible</td>
            <td>Set the right side display or hidden</td>
        </tr>
        <tr>
            <td>setRightImageViewVisible</td>
            <td>Set the right icon to display or hidden</td>
        </tr>
        <tr>
            <td>setTitleVisible</td>
            <td>Set the title display or hidden</td>
        </tr>
        <tr>
            <td>setSubTitleVisible</td>
            <td>Set the subtitle display or hidden</td>
        </tr>
        <tr>
            <td>setTitleLayoutBackground</td>
            <td>Set the title bar background color</td>
        </tr>
</table>



## License

TitleLayout is released under the [Apache License Version 2.0](LICENSE).