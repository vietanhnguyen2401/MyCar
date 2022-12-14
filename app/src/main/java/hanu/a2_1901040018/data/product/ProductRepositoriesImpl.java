package hanu.a2_1901040018.data.product;


import java.util.List;

import hanu.a2_1901040018.domain.ProductRepositories;

public class ProductRepositoriesImpl implements ProductRepositories {
    private static ProductRepositories instance;
    private final ProductService productService;
    private final ProductDao productDAO;

    private ProductRepositoriesImpl(ProductService productService, ProductDao productDAO) {
        this.productService = productService;
        this.productDAO = productDAO;
    }

    public static ProductRepositories getInstance(ProductService productService, ProductDao productDAO) {
        if (instance == null)
            instance = new ProductRepositoriesImpl(productService, productDAO);
        return instance;
    }

    @Override
    public List<ProductDto> fetchProducts() {
        return productService.fetchProducts();
    }

    @Override
    public ProductEntity getExistedProductInCart(int id) {
        return productDAO.getById(id);
    }

    @Override
    public long addProductToCast(ProductEntity productEntity) {
        return productDAO.insert(productEntity);
    }

    @Override
    public boolean updateProductQuantity(int id, int newQuantity) {
        if (newQuantity < 0) return false;
        return productDAO.updateQuantity(id, newQuantity);
    }

    @Override
    public List<ProductEntity> getProductsInCart() {
        return productDAO.getAll();
    }

    @Override
    public boolean deleteProductInCart(int id) {
        return productDAO.deleteProductInCart(id);
    }
}
