package interfaces.and.abstracts;

public interface ISay {
     void sayHello();

     default void sayGoodbye(){
          System.out.println("good byeeeeeeeee!");
     }
}
