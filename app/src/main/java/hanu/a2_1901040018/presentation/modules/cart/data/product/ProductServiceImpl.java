package hanu.a2_1901040018.presentation.modules.cart.data.product;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import hanu.a2_1901040018.presentation.modules.cart.data.BaseService;

public class ProductServiceImpl extends BaseService implements ProductService {
    private static ProductService productService;
    private final ProductMapper mapper;

    private ProductServiceImpl() {
        this.mapper = new ProductMapper();
    }

    public static ProductService create() {
        if (productService == null)
            productService = new ProductServiceImpl();
        return productService;
    }

    @Override
    public List<ProductDto> fetchProducts() {
        String json = httpGet("https://mpr-cart-api.herokuapp.com/products");
        List<ProductDto> products = null;
        try {
            JSONArray jsonArray = new JSONArray(json);
            products = mapper.jsonArrayToDtos(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return products;
    }
}
