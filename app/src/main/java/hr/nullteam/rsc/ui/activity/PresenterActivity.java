package hr.nullteam.rsc.ui.activity;

import android.os.Bundle;

import nucleus.presenter.Presenter;

public abstract class PresenterActivity<T extends Presenter> extends DaggerActivity<T> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpPresenter();
    }

    protected abstract void setUpPresenter();

}
