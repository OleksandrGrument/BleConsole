package com.grument.bleconsole.activity.base;


import android.content.Context;

public interface BaseView {

    void showWarning(int warningMessageResourceId);

    Context getContext();
}
