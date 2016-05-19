package net.dinkla.email

import net.dinkla.utils.Histogram

/**
 * A custom repository for special queries.
 *
 * Created by Dinkla on 11.05.2016.
 */
interface EmailRepositoryCustom {

    Long findMaximalId()

    Histogram<String, Integer> getWeeklyHistogram(String topic)

}
