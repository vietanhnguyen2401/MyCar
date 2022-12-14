package hanu.a2_1901040018.presentation.modules.cart.presentation.base;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public abstract class BaseHolder<Binding extends ViewBinding> extends RecyclerView.ViewHolder implements BaseView {
    protected Binding binding;

    public BaseHolder(Binding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

}