package org.tsinghua.todoapp;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utils {
    public static void HideSoftKey(Context context, View view) {
        InputMethodManager methodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (methodManager.isActive()) {
            methodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void ShowSoftKey(Context context, View view) {
        view.requestFocus();
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
    }
}
