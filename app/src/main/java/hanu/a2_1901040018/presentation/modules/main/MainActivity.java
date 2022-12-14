package hanu.a2_1901040018.presentation.modules.main;

import android.view.LayoutInflater;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;

import hanu.a2_1901040018.data.product.ProductDto;
import hanu.a2_1901040018.presentation.base.BaseActivity;
import hanu.a2_1901040018.presentation.base.annotation.ViewModelClass;
import hanu.a2_1901040018.presentation.modules.cart.CartActivity;
import hanu.a2_1901040018.utils.KeyboardManager;
import hanu.a2_1901040018.databinding.ActivityMainBinding;


@ViewModelClass(MainViewModel.class)
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    private ProductAdapter productAdapter;

    @Override
    protected ActivityMainBinding getViewBinding(LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    public void initView() {
        binding.rvProducts.setLayoutManager(new GridLayoutManager(this, 2));
    }

    @Override
    public void initData() {
        viewmodel.fetchProducts();
    }

    @Override
    public void initComponent() {
        productAdapter = new ProductAdapter(this);
        binding.rvProducts.setAdapter(productAdapter);
    }

    @Override
    public void initListener() {
        viewmodel.getDisplayLiveData().observe(this, productDtos -> {
            if (productDtos == null) return;
            productAdapter.submitList(productDtos);
        });
        binding.actionBar.ivCart.setOnClickListener(view -> {
            launchActivity(CartActivity.class);
        });
        productAdapter.setListener((key, values) -> {
            ProductDto productDto = (ProductDto) values[0];
            viewmodel.addProductToCart(productDto);
        });
        handleSearchProducts();
    }

    private void handleSearchProducts() {
        binding.ivSearch.setOnClickListener(view -> {
            filterProducts(binding.svProduct.getQuery().toString());
            KeyboardManager.hideKeyBoard(this, view);
        });
        binding.svProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterProducts(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterProducts(newText);
                return false;
            }
        });

    }

    public void filterProducts(String keyword) {
        viewmodel.filterProducts(keyword);
    }
}