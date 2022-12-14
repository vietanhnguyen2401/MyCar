package hanu.a2_1901040018.domain;


import java.util.List;

import hanu.a2_1901040018.data.product.ProductDto;
import hanu.a2_1901040018.data.product.ProductEntity;

public interface ProductRepositories {
    ProductEntity getExistedProductInCart(int id);

    List<ProductDto> fetchProducts();

    long addProductToCast(ProductEntity productEntity);

    boolean updateProductQuantity(int id, int newQuantity);

    List<ProductEntity> getProductsInCart();

    boolean deleteProductInCart(int id);
}
