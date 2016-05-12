package net.dinkla.email

import net.dinkla.utils.Histogram

/**
 * Created by Dinkla on 11.05.2016.
 */
interface EmailRepositoryCustom {

    // TODO add queries here for the web app
    public Long findMaximalId()

    public Histogram<String, Integer> getWeeklyHistogram(String topic)

}
