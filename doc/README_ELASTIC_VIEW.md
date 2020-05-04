


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