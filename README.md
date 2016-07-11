#简单的时间选择器

![](http://imgsrc.baidu.com/forum/w%3D580/sign=5cfd6e96ac0f4bfb8cd09e5c334e788f/d3b8fedde71190efa83993d6c61b9d16fcfa6002.jpg)

###代码构建对象
    final DatePickerFragment dateDialog = new DatePickerFragment();
                Calendar calendar = Calendar.getInstance();
                dateDialog.setCurrentDate(calendar);
                dateDialog.show(this.getFragmentManager());
                

###监听事件

    dateDialog.setOnDateSelectedListener(new DatePickerFragment.OnDateSelectedListener() {
                    @Override
                    public void onDateSelected(int year, int monthOfYear, int dayOfMonth) {
                        tvDate.setText(year+" - "+monthOfYear+" - "+dayOfMonth);
                        dateDialog.dismiss();
                    }
                });

    
