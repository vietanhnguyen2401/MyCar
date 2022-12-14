package hanu.a2_1901040018.presentation.modules.cart.presentation.modules.cart;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

import hanu.a2_1901040018.presentation.modules.cart.data.product.ProductEntity;
import hanu.a2_1901040018.presentation.modules.cart.domain.ProductRepository;
import hanu.a2_1901040018.presentation.modules.cart.presentation.base.BaseViewModel;
import hanu.a2_1901040018.presentation.modules.cart.utils.Constants;

public class CartViewModel extends BaseViewModel {
    private final ProductRepository productRepository;
    private final MutableLiveData<List<ProductEntity>> productsInCartLiveData = new MutableLiveData<>();

    public CartViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void getProductsInCart() {
        Constants.executor.execute(() -> {
            List<ProductEntity> products = productRepository.getProductsInCart();
            productsInCartLiveData.postValue(products);
        });
    }

    public void updateProductQuantity(int id, int quantity) {
        Constants.executor.execute(() -> {
            List<ProductEntity> updatedProducts = productsInCartLiveData.getValue();
            boolean isSuccess;
            if (quantity > 0) {
                isSuccess = productRepository.updateProductQuantity(id, quantity);
                if (isSuccess)
                    Objects.requireNonNull(updatedProducts).forEach(productEntity -> {
                        if (productEntity.getId() == id) productEntity.setQuantity(quantity);
                    });
            } else {
                isSuccess = productRepository.deleteProductInCart(id);
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
