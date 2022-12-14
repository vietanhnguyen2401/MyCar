package hanu.a2_1901040018.presentation.modules.cart.presentation.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import hanu.a2_1901040018.presentation.modules.cart.App;
import hanu.a2_1901040018.presentation.modules.cart.presentation.modules.cart.CartViewModel;
import hanu.a2_1901040018.presentation.modules.cart.presentation.modules.main.MainViewModel;
import hanu.a2_1901040018.presentation.modules.cart.domain.ProductRepository;


public class ViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel viewModel = null;
        if (MainViewModel.class.equals(modelClass)) {
            ProductRepository productRepository = App.getInstance().getSingletonModules().getCartRepository();
            viewModel = new MainViewModel(productRepository);
        } else if (CartViewModel.class.equals(modelClass)) {
            ProductRepository productRepository = App.getInstance().getSingletonModules().getCartRepository();
            viewModel = new CartViewModel(productRepository);
        }
        return (T) viewModel;
    }
}
