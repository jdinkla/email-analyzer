package net.dinkla.utils

import groovy.transform.CompileStatic

/**
 * Created by Dinkla on 17.05.2016.
 */
@CompileStatic
class Graph<K, V> {

    // the first coordinate are the dates
    // "yyyy-mm-dd HH:MM:SS.ssssss"
    List<K> x

    // the second are counts
    List<V> y

    String name

    String type = 'scatter'
    String mode = 'lines+markers'

    Graph() {
    }

    Graph(final Histogram<K, V> hist) {
        assert(hist)
        name = hist.name
        x = []
        y = []
        for (Tuple2<K, V> p : hist.pairs) {
            x.add(p.first)
            y.add(p.second)
        }
    }

}
