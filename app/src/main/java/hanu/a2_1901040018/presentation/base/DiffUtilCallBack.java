package hanu.a2_1901040018.presentation.base;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class DiffUtilCallBack<Data> extends DiffUtil.Callback {
    private final List<Data> oldList;
    private final List<Data> newList;
    private final DiffOrSame<Data> diffOrSame;

    public DiffUtilCallBack(List<Data> oldList, List<Data> newList, DiffOrSame<Data> diffOrSame) {
        this.oldList = oldList;
        this.newList = newList;
        this.diffOrSame = diffOrSame;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return diffOrSame.areItemsTheSame(oldList.get(oldItemPosition), newList.get(newItemPosition));
    }


    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return diffOrSame.areContentsTheSame(oldList.get(oldItemPosition), newList.get(newItemPosition));
    }


    interface DiffOrSame<Data> {
        boolean areItemsTheSame(Data oldItem, Data newItem);
        boolean areContentsTheSame(Data oldItem, Data newItem);
    }
}
