package hanu.a2_1901040018.presentation.modules.cart.presentation.modules.main;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import hanu.a2_1901040018.presentation.modules.cart.data.product.ProductDto;
import hanu.a2_1901040018.presentation.modules.cart.data.product.ProductEntity;
import hanu.a2_1901040018.presentation.modules.cart.domain.ProductRepository;
import hanu.a2_1901040018.presentation.modules.cart.presentation.base.BaseViewModel;
import hanu.a2_1901040018.presentation.modules.cart.utils.Constants;
import hanu.a2_1901040018.presentation.modules.cart.utils.Mapper;

public class MainViewModel extends BaseViewModel {
    private final ProductRepository productRepository;
    private final MutableLiveData<List<ProductDto>> productsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<ProductDto>> displayProductsLiveData = new MutableLiveData<>();

    public MainViewModel(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void fetchProducts() {
        Constants.executor.execute(() -> {
            List<ProductDto> products = productRepository.fetchProducts();
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
            ProductEntity existedProductInCart = productRepository.getExistedProductInCart(productDto.getId());
            if (existedProductInCart == null) {
                ProductEntity productEntity = Mapper.map(productDto, ProductEntity.class);
                assert productEntity != null;
                productEntity.setQuantity(1);
                productRepository.addProductToCast(productEntity);
                return;
            }
            productRepository.updateProductQuantity(productDto.getId(), existedProductInCart.getQuantity() + 1);
        });
    }
}
