package hanu.a2_1901040018.presentation.base;

public interface BaseView {
    default void initialize(){
        initView();
        initComponent();
        initListener();
        initData();
    }

    default void initView() {

    }

    default void initComponent() {

    }

    default void initListener() {

    }

    default void initData() {

    }


}
