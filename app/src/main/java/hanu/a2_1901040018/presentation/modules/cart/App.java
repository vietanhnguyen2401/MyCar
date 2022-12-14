package hanu.a2_1901040018.presentation.modules.cart;

import android.app.Application;

import hanu.a2_1901040018.presentation.modules.cart.utils.SingletonModules;


public class App extends Application {
    private SingletonModules singletonModules;
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initSingleton();
    }

    public static App getInstance() {
        return instance;
    }

    private void initSingleton() {
        singletonModules = new SingletonModules(getApplicationContext());
    }

    public SingletonModules getSingletonModules() {
        return singletonModules;
    }
}
