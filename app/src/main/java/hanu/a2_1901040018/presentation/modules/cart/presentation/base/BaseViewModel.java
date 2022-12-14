package hanu.a2_1901040018.presentation.modules.cart.presentation.base;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    @Override
    protected void onCleared() {
        Log.d(BaseViewModel.class.getName(), "onCleared...");
    }
}
