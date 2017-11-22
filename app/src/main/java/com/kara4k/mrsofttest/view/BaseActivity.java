package com.kara4k.mrsofttest.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.kara4k.mrsofttest.app.App;
import com.kara4k.mrsofttest.di.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getContentView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        injectDependencies();
        onViewReady();
    }

    protected void injectDependencies() {}

    protected void onViewReady() {}

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }
}
