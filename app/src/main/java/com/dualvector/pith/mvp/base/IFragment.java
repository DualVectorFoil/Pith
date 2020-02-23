package com.dualvector.pith.mvp.base;

import android.os.Bundle;

public interface IFragment {

    int getLayout();

    void initView();

    void initData(Bundle savedInstanceState);
}
