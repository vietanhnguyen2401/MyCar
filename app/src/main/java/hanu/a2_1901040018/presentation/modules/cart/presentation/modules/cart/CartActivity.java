package hanu.a2_1901040018.presentation.modules.cart.presentation.modules.cart;

import android.view.LayoutInflater;

import androidx.recyclerview.widget.LinearLayoutManager;

import hanu.a2_1901040018.R;
import hanu.a2_1901040018.databinding.ActivityCartBinding;
import hanu.a2_1901040018.presentation.modules.cart.data.product.ProductEntity;
import hanu.a2_1901040018.presentation.modules.cart.presentation.base.BaseActivity;
import hanu.a2_1901040018.presentation.modules.cart.presentation.base.annotation.ViewModelClass;

@ViewModelClass(CartViewModel.class)
public class CartActivity extends BaseActivity<ActivityCartBinding, CartViewModel> {
    private CartAdapter cartAdapter;

    @Override
    protected ActivityCartBinding getViewBinding(LayoutInflater layoutInflater) {
        return ActivityCartBinding.inflate(layoutInflater);
    }

    @Override
    public void initView() {
        binding.rvCart.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
        viewmodel.getProductsInCart();
    }

    @Override
    public void initComponent() {
        cartAdapter = new CartAdapter(this);
        binding.rvCart.setAdapter(cartAdapter);
        cartAdapter.setListener((key, values) -> {
            ProductEntity product = (ProductEntity) values[0];
            viewmodel.updateProductQuantity(product.getId(), product.getQuantity());
        });
    }

    @Override
    public void initListener() {
        viewmodel.getProductsInCartLiveData().observe(this, productEntities -> {
            cartAdapter.submitList(productEntities);
            double total = 0;
            for (ProductEntity productEntity :
                    productEntities) {
                total += productEntity.getUnitPrice() * productEntity.getQuantity();
            }
            String totalPriceString = String.format(getString(R.string.price), (int) total + "");
            binding.tvTotalPrice.setText(totalPriceString);
        });
    }
}
