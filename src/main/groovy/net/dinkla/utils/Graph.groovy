package net.dinkla.utils

/**
 * Created by Dinkla on 17.05.2016.
 */
class Graph {

    // the first coordinate are the dates
    // "yyyy-mm-dd HH:MM:SS.ssssss"
    List<String> x

    // the second are counts
    List<Integer> y

    String type
    String mode
    String name

    Graph() {
    }

    Graph(final Histogram<String, Integer> hist) {
        assert(hist)
        name = hist.name
        x = []
        y = []
        for (Tuple2<String, Integer> p : hist.pairs) {
            x.add(p.first)
            y.add(p.second)
        }
    }

}
