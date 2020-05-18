


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



     dialog = new IOSMessageDialog.Builder(this)
                .setCancelable(true) //设置是否能够取消
                .setMessage("这是信息") //设置你的提示消息
                .setTextColor(ContextCompat.getColor(this, R.color.txtColor)) //设置你的文字提示的颜色
                .setPositiveButton("更新", new IOSMessageDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog) {
                        //设置你的确定按钮名称，并添加监听事件
                    }

                })
                .setNegativeButton("取消", new IOSMessageDialog.OnClickListener() {
                    @Override
                    public void onClick(Dialog dialog) {
                        //设置你的取消按钮名称，并添加监听事件
                    }
                })
                .create();
```