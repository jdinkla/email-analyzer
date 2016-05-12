package net.dinkla.email

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

/**
 * Created by Dinkla on 11.05.2016.
 *
 * This interface also extends EmailRepositoryCustom.
 */
interface EmailRepository extends ElasticsearchRepository<Email, Long>, EmailRepositoryCustom  {

    public List<Email> findBySubjectLike(String subject);

    // TODO declare a method using @Query

    // TODO add queries here for the web app

}
