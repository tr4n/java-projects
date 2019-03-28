package collections.and.generics;

public class ExtendGenericModel<T extends Number> implements Comparable<ExtendGenericModel> {
    private T value;

    public ExtendGenericModel(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }


    @Override
    public int compareTo(ExtendGenericModel o) {
        return (value.doubleValue() - o.value.doubleValue()) > 0 ? 1
                : (value.doubleValue() - o.value.doubleValue()) < 0 ? -1
                : 0;
    }
}
