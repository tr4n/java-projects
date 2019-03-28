package interfaces.and.abstracts;

public class Cat extends AAnimal implements ISay {


    public Cat(String name) {
        super(name);
    }

    @Override
    public void sayHello() {
        System.out.println(getName() + " Helloworld");
    }


}
