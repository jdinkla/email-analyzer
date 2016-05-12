package net.dinkla

/**
 * Created by Dinkla on 12.05.2016.
 */
class Histogram<K, V> {

    String name

    List<Tuple2<K, V>> pairs;

    Histogram(final String name) {
        this.name = name
        pairs = new LinkedList<>()
    }

    void add(K key, V value) {
        pairs.add(new Tuple2(key, value))
    }

    @Override
    public String toString() {
        return "Histogram{" +
                "name='" + name + '\'' +
                ", pairs=" + pairs +
                '}';
    }
}
