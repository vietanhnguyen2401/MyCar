package hanu.a2_1901040018.presentation.modules.main;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import hanu.a2_1901040018.data.product.ProductDto;
import hanu.a2_1901040018.presentation.base.BaseHolder;
import hanu.a2_1901040018.presentation.base.BaseRecyclerAdapter;
import hanu.a2_1901040018.utils.ImageLoader;
import hanu.a2_1901040018.R;
import hanu.a2_1901040018.databinding.ItemProductBinding;


public class ProductAdapter extends BaseRecyclerAdapter<ProductDto> {
    public ProductAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseHolder getHolder(ViewGroup parent, int viewType) {
        @NonNull ItemProductBinding binding = ItemProductBinding.inflate(getInflater(), parent, false);
        return new ProductHolder(binding);
    }


    public class ProductHolder extends BaseHolder<ItemProductBinding> {

        public static final String KEY_ADD_TO_CART = "KEY_ADD_PRODUCT";
        private ProductDto data;

        public ProductHolder(ItemProductBinding binding) {
            super(binding);
        }

        @Override
        public void initData() {
            this.data = listData.get(getAdapterPosition());
            binding.tvName.setText(data.getName());
            String priceString = String.format(context.getString(R.string.price), (int) data.getUnitPrice() + "");
            binding.tvPrice.setText(priceString);
            ImageLoader.loadImage(data.getThumbnail(), binding.ivProduct);
        }

        @Override
        public void initListener() {
            binding.ivAdd.setOnClickListener(view -> {
                listener.invoke(KEY_ADD_TO_CART, data);
            });
        }
    }
}
