package com.dualvector.pith.mvp.base;

import android.os.Bundle;

public interface IActivity {

    int getLayout();

    void initView();

    void initData(Bundle savedInstanceState);
}
