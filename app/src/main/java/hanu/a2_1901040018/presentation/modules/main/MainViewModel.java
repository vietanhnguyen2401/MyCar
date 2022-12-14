package hanu.a2_1901040018.presentation.modules.main;

import androidx.lifecycle.MutableLiveData;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import hanu.a2_1901040018.data.product.ProductDto;
import hanu.a2_1901040018.data.product.ProductEntity;
import hanu.a2_1901040018.domain.ProductRepositories;
import hanu.a2_1901040018.presentation.base.BaseViewModel;
import hanu.a2_1901040018.utils.Constants;
import hanu.a2_1901040018.utils.Mapper;

public class MainViewModel extends BaseViewModel {
    private final ProductRepositories productRepositories;
    private final MutableLiveData<List<ProductDto>> productsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<ProductDto>> displayProductsLiveData = new MutableLiveData<>();

    public MainViewModel(ProductRepositories productRepositories) {
        this.productRepositories = productRepositories;
    }

    public void fetchProducts() {
        Constants.executor.execute(() -> {
            List<ProductDto> products = productRepositories.fetchProducts();
            productsLiveData.postValue(products);
            displayProductsLiveData.postValue(products);
        });
    }

    public void filterProducts(String keyword) {
        List<ProductDto> filteredProducts = Objects.requireNonNull(productsLiveData.getValue()).stream().filter(productDto -> productDto.getName().toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
        displayProductsLiveData.postValue(filteredProducts);
    }

    public MutableLiveData<List<ProductDto>> getDisplayLiveData() {
        return displayProductsLiveData;
    }


    public void addProductToCart(ProductDto productDto) {
        Constants.executor.execute(() -> {
            ProductEntity existedProductInCart = productRepositories.getExistedProductInCart(productDto.getId());
            if (existedProductInCart == null) {
                ProductEntity productEntity = Mapper.map(productDto, ProductEntity.class);
                assert productEntity != null;
                productEntity.setQuantity(1);
                productRepositories.addProductToCast(productEntity);
                return;
            }
            productRepositories.updateProductQuantity(productDto.getId(), existedProductInCart.getQuantity() + 1);
        });
    }
}
