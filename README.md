
## [中文文档][readme_cn]
## About

[<font size="5">AndroidUiWidget</font>][readme] is a rich UI widget library,it can greatly improve your development efficiency.

## Documentation

### Dialog
* [IOSMessageDialog][readme_ios_message_dialog]
* [IOSBottomListDialog][readme_ios_bottom_list_dialog]


<!--![](https://github.com/scwang90/SmartRefreshLayout/raw/master/art/gif_practive_weibo.gif)-->


## Get start

### Dialog
maven
```xml
   <dependency>
	<groupId>cn.rubintry.widget</groupId>
	<artifactId>dialog</artifactId>
	<version>1.0.2</version>
	<type>pom</type>
   </dependency>
```

gradle
```groovy
implementation 'cn.rubintry.widget:dialog:1.0.2'
```


## How to use?

```java
   dialog = new IOSMessageDialog.Builder(this)
                .setCancelable(true)
                .setMessage("Your message")
                .setTextColor(ContextCompat.getColor(this , R.color.txtColor))
                .setOnButtonClickListener(new IOSMessageDialog.OnButtonClickListener() {
                    @Override
                    public void onConfirm() {

                    }

                    @Override
                    public void onCancel() {

                    }
                })
                .create();

    dialog.show();
```

```java
    List<String> list = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
        list.add(i + "");
    }
    bottomDialog = new IOSBottomListDialog.Builder(this)
                .setCancelable(true)
                .setList(list)
                .setCancelListener(null)
                .setOnItemClickListener(new IOSBottomListDialog.OnItemClickListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onItemClick(String content , int position) {

                    }

                    @Override
                    public void onConfirm() {

                    }
                }).create();

        bottomDialog.show();
```


## License
```text
Copyright 2020 Sunzhong Lu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```


[readme]: https://github.com/Rubintry/AndroidUiWidget
[auc]: https://github.com/Rubintry/AndroidUiWidget
[result]: https://android-arsenal.com/result?level=14
[readme_cn]:https://github.com/RubinTry/AndroidUiWidget/blob/master/README_CN.md
[readme_ios_message_dialog]:https://github.com/RubinTry/AndroidUiWidget/blob/master/doc/README_IOS_MESSAGE_DIALOG.md
[readme_ios_bottom_list_dialog]:https://github.com/RubinTry/AndroidUiWidget/blob/master/doc/README_IOS_BOTTOM_LIST_DIALOG.md