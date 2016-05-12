package net.dinkla.email

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

/**
 * Created by Dinkla on 11.05.2016.
 */
interface EmailRepositoryCustom {

    // TODO add queries here for the web app
    public List<Email> mySpecialFind(String subject);


}
