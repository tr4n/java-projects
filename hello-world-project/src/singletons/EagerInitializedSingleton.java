package singletons;

public class EagerInitializedSingleton {
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    private EagerInitializedSingleton(){

    };
    private static EagerInitializedSingleton getInstance(){
        return instance;
    }
}

