


# Code Example

```java
    elasticView.setHeader(imgHeader)//set your header
        .setOnReadyPullListener(new OnReadyPullListener() {
            @Override
            public boolean isReady() {
                //return a suitable conditions can be pulled down
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