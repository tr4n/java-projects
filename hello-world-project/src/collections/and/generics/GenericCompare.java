package collections.and.generics;

public class GenericCompare<T extends Comparable<T>> implements Comparable<GenericCompare> {
    private T value;

    public GenericCompare(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public int compareTo(GenericCompare o) {
        return value.compareTo((T) o.value);
    }
}
