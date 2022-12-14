package hanu.a2_1901040018.presentation.modules.cart;

import androidx.lifecycle.MutableLiveData;


import java.util.List;
import java.util.Objects;

import hanu.a2_1901040018.data.product.ProductEntity;
import hanu.a2_1901040018.domain.ProductRepositories;
import hanu.a2_1901040018.presentation.base.BaseViewModel;
import hanu.a2_1901040018.utils.Constants;

public class CartViewModel extends BaseViewModel {
    private final ProductRepositories productRepositories;
    private final MutableLiveData<List<ProductEntity>> productsInCartLiveData = new MutableLiveData<>();

    public CartViewModel(ProductRepositories productRepositories) {
        this.productRepositories = productRepositories;
    }

    public void getProductsInCart() {
        Constants.executor.execute(() -> {
            List<ProductEntity> products = productRepositories.getProductsInCart();
            productsInCartLiveData.postValue(products);
        });
    }

    public void updateProductQuantity(int id, int quantity) {
        Constants.executor.execute(() -> {
            List<ProductEntity> updatedProducts = productsInCartLiveData.getValue();
            boolean isSuccess;
            if (quantity > 0) {
                isSuccess = productRepositories.updateProductQuantity(id, quantity);
                if (isSuccess)
                    Objects.requireNonNull(updatedProducts).forEach(productEntity -> {
                        if (productEntity.getId() == id) productEntity.setQuantity(quantity);
                    });
            } else {
                isSuccess = productRepositories.deleteProductInCart(id);
                if (isSuccess)
                    updatedProducts.removeIf(productEntity -> productEntity.getId() == id);
            }
            if (isSuccess)
                productsInCartLiveData.postValue(updatedProducts);
        });
    }

    public MutableLiveData<List<ProductEntity>> getProductsInCartLiveData() {
        return productsInCartLiveData;
    }
}
