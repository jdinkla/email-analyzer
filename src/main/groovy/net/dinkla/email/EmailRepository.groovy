package net.dinkla.email

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

/**
 * Created by Dinkla on 11.05.2016.
 */
interface EmailRepository extends ElasticsearchRepository<Email, Long>  {

    public List<Email> findBySubjectLike(String subject);

}
