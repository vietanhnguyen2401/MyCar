package hanu.a2_1901040018;

import android.app.Application;

import hanu.a2_1901040018.utils.SingletonModule;


public class App extends Application {
    private SingletonModule singletonModule;
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
        singletonModule = new SingletonModule(getApplicationContext());
    }

    public SingletonModule getSingletonModules() {
        return singletonModule;
    }
}
