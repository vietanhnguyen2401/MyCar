package hanu.a2_1901040018.presentation.modules.cart;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import hanu.a2_1901040018.R;
import hanu.a2_1901040018.data.product.ProductEntity;
import hanu.a2_1901040018.databinding.ItemCartProductBinding;
import hanu.a2_1901040018.presentation.base.BaseHolder;
import hanu.a2_1901040018.presentation.base.BaseRecyclerAdapter;
import hanu.a2_1901040018.utils.ImageLoader;


public class CartAdapter extends BaseRecyclerAdapter<ProductEntity> {
    private static final String KEY_UPDATE_QUANTITY = "KEY_UPDATE_QUANTITY";

    public CartAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseHolder getHolder(ViewGroup parent, int viewType) {
        @NonNull ItemCartProductBinding binding = ItemCartProductBinding.inflate(getInflater(), parent, false);
        return new ProductHolder(binding);
    }


    public class ProductHolder extends BaseHolder<ItemCartProductBinding> {

        private ProductEntity data;

        public ProductHolder(ItemCartProductBinding binding) {
            super(binding);
        }

        @Override
        public void initData() {
            this.data = listData.get(getAdapterPosition());
            binding.tvName.setText(data.getName());
            String priceString = String.format(context.getString(R.string.price), (int) data.getUnitPrice() + "");
            binding.tvPrice.setText(priceString);
            ImageLoader.loadImage(data.getThumbnail(), binding.ivProduct);
            updateQuantityView();
        }

        private void updateQuantityView() {
            binding.tvQuantity.setText(String.valueOf(data.getQuantity()));
            String totalPriceString = String.format(context.getString(R.string.price), (int) data.getUnitPrice() * data.getQuantity() + "");
            binding.tvTotalPrice.setText(totalPriceString);
        }

        @Override
        public void initListener() {
            binding.ivIncrease.setOnClickListener(view -> {
                updateQuantity(1);
            });
            binding.ivDecrease.setOnClickListener(view -> {
                updateQuantity(-1);
            });
        }

        private void updateQuantity(int change) {
            data.setQuantity(data.getQuantity() + change);
            if (data.getQuantity() > 0)
                updateQuantityView();
            else {
                notifyItemRemoved(listData.indexOf(data));
                listData.remove(data);
            }
            listener.invoke(KEY_UPDATE_QUANTITY, data);
        }

    }
}
