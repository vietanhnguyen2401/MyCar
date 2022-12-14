package hanu.a2_1901040018.presentation.modules.cart.presentation.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseRecyclerAdapter<D> extends RecyclerView.Adapter<BaseHolder<ViewBinding>> implements DiffUtilCallBack.DiffOrSame<D> {

    protected List<D> listData = new ArrayList<>();
    protected Context context;
    protected OnListener listener;


    public BaseRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setListener(OnListener listener) {
        this.listener = listener;
    }

    public void invokeListener(String key, Object value) {
        if (listener != null)
            listener.invoke(key, value);
    }

    @Override
    public boolean areContentsTheSame(D oldItem, D newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areItemsTheSame(D oldItem, D newItem) {
        return oldItem.equals(newItem);
    }

    @NonNull
    @Override
    public final BaseHolder<ViewBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseHolder<ViewBinding> holder = getHolder(parent, viewType);
        return holder;
    }

    protected abstract BaseHolder getHolder(ViewGroup parent, int viewType);

    public void submitList(List<D> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilCallBack<>(listData, newList, this));
        listData = new ArrayList<>(newList);
        diffResult.dispatchUpdatesTo(this);
    }


    @SafeVarargs
    public final void insertAt(int position, D... insertedItems) {
        if (position >= 0 && position < listData.size()) {
            ArrayList<D> newList = new ArrayList<>(listData);
            newList.addAll(position, Arrays.asList(insertedItems));
            submitList(newList);
        }
    }

    @SafeVarargs
    public final void insert(D... insertedItems) {
        ArrayList<D> newList = new ArrayList<>(listData);
        newList.addAll(Arrays.asList(insertedItems));
        submitList(newList);
    }

    public void removeAt(int position) {
        if (position >= 0 && position < listData.size()) {
            ArrayList<D> newList = new ArrayList<>(listData);
            newList.remove(position);
            submitList(newList);
        }
    }


    @SafeVarargs
    public final void remove(D... item) {
        ArrayList<D> newList = new ArrayList<>(listData);
        newList.removeAll(Arrays.asList(item));
        submitList(newList);
    }

    protected LayoutInflater getInflater() {
        return LayoutInflater.from(context);
    }


    @Override
    public final void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        holder.initialize();
    }

    @Override
    public final int getItemCount() {
        return listData.size();
    }

    public List<D> getListData() {
        return listData;
    }
}
