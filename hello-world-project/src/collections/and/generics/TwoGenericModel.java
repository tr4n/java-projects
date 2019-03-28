package collections.and.generics;

public class TwoGenericModel<T, V> {
    private T key;
    private V value;

    public TwoGenericModel(T key, V value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{key = " + key.toString()
                   + ", value = " + value.toString()
                +"}";
    }
}
