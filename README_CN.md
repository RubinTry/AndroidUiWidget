
![logo](https://rubintry.cn/icon.png)

## 介绍

[<font size="5">AndroidUiWidget</font>][readme] 是一个丰富的UI组件库, 它能够使你的开发效率大大提升。


## 特点
* 支持Androidx
* 简洁、美观、易扩展
* 支持下拉放大(ElasticView)


## 效果展示
|[IOSMessageDialog][readme_ios_message_dialog]|[IOSBottomListDialog][readme_ios_bottom_list_dialog]|
|:---:|:---:|
|![](https://rubintry.cn/IOS_MESSAGE_DIALOG.gif)|![](https://rubintry.cn/IOS_BOTTOM_LIST_DIALOG.gif)|

|[ElasticView][readme_elastic_view]|
|:---:|
|![](https://rubintry.cn/ELASTIC_VIEW.gif)|

## 开始使用

### Dialog
maven
```xml
   <dependency>
	<groupId>cn.rubintry.widget</groupId>
	<artifactId>dialog</artifactId>
	<version>1.0.5</version>
	<type>pom</type>
   </dependency>
```

gradle
```groovy
implementation 'cn.rubintry.widget:dialog:1.0.5'
```

### Widget
maven
```xml
   <dependency>
	<groupId>cn.rubintry.widget</groupId>
	<artifactId>widget</artifactId>
	<version>1.0.5</version>
	<type>pom</type>
   </dependency>
```

gradle
```groovy
implementation 'cn.rubintry.widget:widget:1.0.5'
```



## 如何使用？

### IOSMessageDialog

```java
   dialog = new IOSMessageDialog.Builder(this)
                .setCancelable(true)
                .setMessage("你要提示的消息")
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

### IOSBottomListDialog

```java
    List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        list.add("不显示");
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
                        Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                    }

                }).create();

        bottomDialog.show();
```

### ElasticView
```java
    elasticView.setHeader(imgHeader)//set your header
        .setOnReadyPullListener(new OnReadyPullListener() {
            @Override
            public boolean isReady() {
                //返回一个合适的下拉条件
                return nslContent.getScrollY() == 0;
            }
        });
```


#### Layout
```xml
    <?xml version="1.0" encoding="utf-8"?>
    <cn.rubintry.widget.elastic.ElasticView xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/esvContainer"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".view.elastic.ElasticActivity">
    
        <androidx.core.widget.NestedScrollView
            android:id="@+id/nslContent"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fillViewport="true">
    
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical">
                <ImageView
                    android:id="@+id/imgHeader"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:src="@mipmap/a_logo"
                    android:adjustViewBounds="true"></ImageView>
    
    
                <androidx.recyclerview.widget.RecyclerView
                    android:id="@+id/rvElasticData"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"></androidx.recyclerview.widget.RecyclerView>
            </LinearLayout>
        </androidx.core.widget.NestedScrollView>
    
    
    
    </cn.rubintry.widget.elastic.ElasticView>
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
[readme_ios_message_dialog]:https://github.com/RubinTry/AndroidUiWidget/blob/master/doc/cn/README_IOS_MESSAGE_DIALOG_CN.md
[readme_ios_bottom_list_dialog]:https://github.com/RubinTry/AndroidUiWidget/blob/master/doc/cn/README_IOS_BOTTOM_LIST_DIALOG_CN.md

[readme_elastic_view]:https://github.com/RubinTry/AndroidUiWidget/blob/master/doc/cn/README_ELASTIC_VIEW_CN.md
