package com.android.maziotest.ui.base;

import io.reactivex.disposables.Disposable;

public interface BasePresenter<V extends BaseView> {

    void addToSubscription(Disposable disposable);

    void onResume();

    boolean isViewPaused();

    void setViewPaused(boolean viewPaused);

    void onDestroyView();

    void onAttachView(V view);
}
