package net.dinkla.email

import net.dinkla.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.data.elasticsearch.core.query.SearchQuery
import org.springframework.stereotype.Repository

import static org.elasticsearch.index.query.QueryBuilders.*

/**
 * Created by Dinkla on 12.05.2016.
 */
@Repository
class EmailRepositoryImpl implements EmailRepositoryCustom {

    // TODO implement

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Override
    List<Email> mySpecialFind(String subject) {

        println "mySpecialFind"

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(matchQuery("subject", "Hadoop"))
            .withIndices(Constants.EMAIL_INDEX)
            .withTypes(Constants.EMAIL_TYPE)
            .build();

        List<Email> es = elasticsearchTemplate.queryForList(searchQuery, Email.class)
        return es
    }

}
