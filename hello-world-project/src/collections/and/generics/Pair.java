package collections.and.generics;

public class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public int compareTo(Pair o) {
        return key.compareTo((K) o.key) == 0 ? value.compareTo((V) o.value) : key.compareTo((K) o.key);
    }

    @Override
    public String toString() {
        return "{"+key +",\""+value+"\"}";
    }
}
