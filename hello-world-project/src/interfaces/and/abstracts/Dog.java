package interfaces.and.abstracts;

public class Dog extends AAnimal implements ISay {


    public Dog(String name) {
        super(name);
    }


    @Override
    public void sayHello() {
        System.out.println(getName() + ": Hello world");
    }


}
