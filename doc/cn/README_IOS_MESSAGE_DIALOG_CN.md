


# 代码示例

```java
    dialog = new IOSMessageDialog.Builder(this)
                .setCancelable(true) //设置是否能够取消
                .setTitle("tips title");//设置你的弹窗标题
                .setMessage("Your message") //设置你的提示消息
                .setTextColor(ContextCompat.getColor(this , R.color.txtColor)) //设置你的文字提示的颜色
                .setMessageTextSize(16) //设置提示信息的字体大小(sp值).
                .setOnButtonClickListener(new IOSMessageDialog.OnButtonClickListener() {
                    @Override
                    public void onConfirm() {
                        Toast.makeText(MainActivity.this, "点击确定", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "点击取消", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
```