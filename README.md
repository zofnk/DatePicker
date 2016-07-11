#简单的时间选择器



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

    
