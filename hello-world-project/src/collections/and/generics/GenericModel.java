package collections.and.generics;

import java.util.Comparator;

public class GenericModel<T>   {
    private T object;

    public GenericModel(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return object.toString();
    }
}
