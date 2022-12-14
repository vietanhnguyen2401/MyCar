package hanu.a2_1901040018.utils;

import android.content.Context;

import hanu.a2_1901040018.data.DbHelper;
import hanu.a2_1901040018.data.product.ProductDao;
import hanu.a2_1901040018.data.product.ProductRepositoriesImpl;
import hanu.a2_1901040018.data.product.ProductService;
import hanu.a2_1901040018.data.product.ProductServiceImpl;
import hanu.a2_1901040018.domain.ProductRepositories;


public class SingletonModule {
    private ProductRepositories productRepositories;

    public SingletonModule(Context context) {
        initProductRepository(context);
    }

    private void initProductRepository(Context context) {
        ProductService productService = ProductServiceImpl.create();
        DbHelper dbHelper = new DbHelper(context);
        ProductDao productDAO = ProductDao.getInstance(dbHelper);
        productRepositories = ProductRepositoriesImpl.getInstance(productService, productDAO);
    }

    public ProductRepositories getCartRepository() {
        return productRepositories;
    }
}
