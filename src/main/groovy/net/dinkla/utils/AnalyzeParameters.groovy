package net.dinkla.utils

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by Dinkla on 13.05.2016.
 */
class AnalyzeParameters {

    @NotNull
    @Size(min=1, max=255)
    String topics

    List<String> split() {
        split(topics)
    }

    static List<String> split(String topics) {
        def ps = topics.split(/,/)
        def qs = ps.collect { it.trim() }
        def rs = qs.findAll { it.size() > 0 }
        rs
    }

}
