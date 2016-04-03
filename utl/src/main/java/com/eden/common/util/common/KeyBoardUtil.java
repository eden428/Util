package com.eden.common.util.common;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.eden.common.util.ContextProvider;

/**
 * Created by Administrator on 2016/4/3.
 */
public class KeyBoardUtil {

    /**
     * 隐藏activity的键盘
     *
     * @param context
     */
    public static void hideKeyboard(Activity context) {
        View view = context.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void hideKeyboard(EditText mEditText) {
        InputMethodManager imm = (InputMethodManager) ContextProvider.getInstance().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    /**
     * 打卡软键盘
     */
    public static void openKeybord(EditText mEditText) {
        InputMethodManager imm = (InputMethodManager) ContextProvider.getInstance().getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
