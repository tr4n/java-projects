package singletons;

public class Singleton {

    private static volatile Singleton sInstance;

    private Singleton(){

    }

    public static Singleton getInstance(){
        Singleton result = sInstance;
        if(result == null){
            synchronized (Singleton.class){
                result = sInstance;
                if(result == null){
                    sInstance = result = new Singleton();
                }
            }
        }
        return result;
    }

    public void sayHello(){
        System.out.println("Hello world!!");
    }
    public void sayGoodbye(){
        System.out.println("Byee!!, See you again.");
    }

}
