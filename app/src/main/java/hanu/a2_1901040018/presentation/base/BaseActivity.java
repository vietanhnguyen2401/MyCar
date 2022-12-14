package hanu.a2_1901040018.presentation.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import hanu.a2_1901040018.presentation.base.annotation.ViewModelClass;
import hanu.a2_1901040018.presentation.factories.ViewModelFactory;


public abstract class BaseActivity<T extends ViewBinding, VM extends BaseViewModel> extends AppCompatActivity implements BaseView {
    public static final String KEY_DATA = "DATA";
    protected T binding;
    protected VM viewmodel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewBinding(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        initialize();
    }

    protected abstract T getViewBinding(LayoutInflater layoutInflater);

    @Override
    public final void initialize() {
        Class<VM> viewModelName = (Class<VM>) getClass().getAnnotation(ViewModelClass.class).value();
        viewmodel = new ViewModelProvider(this, new ViewModelFactory()).get(viewModelName);
        BaseView.super.initialize();
    }

    protected void launchActivity(Class<? extends Activity> clazz) {
        Intent i = new Intent();
        i.setClass(this, clazz);
        startActivity(i);
    }

    protected void launchActivity(Class<? extends Activity> clazz, Bundle bundle) {
        Intent i = new Intent();
        i.setClass(this, clazz);
        i.putExtra(KEY_DATA, bundle);
        startActivity(i);
    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    protected float getDimen(@DimenRes int id) {
        return getResources().getDimension(id);
    }

    protected int getOrientation() {
        return getResources().getConfiguration().orientation;
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }

    protected String stringOf(@StringRes int res) {
        return getResources().getString(res);
    }

}
