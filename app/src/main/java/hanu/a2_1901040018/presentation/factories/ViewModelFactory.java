package hanu.a2_1901040018.presentation.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import hanu.a2_1901040018.presentation.modules.cart.CartViewModel;
import hanu.a2_1901040018.App;
import hanu.a2_1901040018.domain.ProductRepositories;
import hanu.a2_1901040018.presentation.modules.main.MainViewModel;


public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel viewModel = null;
        if (MainViewModel.class.equals(modelClass)) {
            ProductRepositories productRepositories = App.getInstance().getSingletonModules().getCartRepository();
            viewModel = new MainViewModel(productRepositories);
        } else if (CartViewModel.class.equals(modelClass)) {
            ProductRepositories productRepositories = App.getInstance().getSingletonModules().getCartRepository();
            viewModel = new CartViewModel(productRepositories);
        }
        return (T) viewModel;
    }
}
