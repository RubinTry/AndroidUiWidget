


# Code Example

```java
    dialog = new IOSMessageDialog.Builder(this)
                .setCancelable(true) //Set can cancel
                .setTitle("tips title");//Set your dialog title
                .setMessage("Your message") //Set your tip message
                .setTextColor(ContextCompat.getColor(this , R.color.txtColor)) //Set your tip words' color
                .setMessageTextSize(16) //Set your message's text size(spValue).
                .setOnButtonClickListener(new IOSMessageDialog.OnButtonClickListener() {
                    @Override
                    public void onConfirm() {
                        Toast.makeText(MainActivity.this, "click the confirm", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "click the cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
```