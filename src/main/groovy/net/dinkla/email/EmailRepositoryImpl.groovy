package net.dinkla.email

import net.dinkla.utils.Histogram
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.action.search.SearchType
import org.elasticsearch.search.aggregations.AggregationBuilders
import org.elasticsearch.search.aggregations.Aggregations
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogram
import org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram
import org.elasticsearch.search.aggregations.metrics.max.InternalMax
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.core.ResultsExtractor
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.data.elasticsearch.core.query.SearchQuery
import org.springframework.stereotype.Repository

import static org.elasticsearch.index.query.QueryBuilders.*

/**
 * Created by Dinkla on 12.05.2016.
 */
@Repository
class EmailRepositoryImpl implements EmailRepositoryCustom {

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    @Value('${emailanalyzer.index}')
    String emailIndex

    @Value('${emailanalyzer.type}')
    String emailType

    /*
     * Find the maximal id
     *
        GET /email/email/_search?search_type=count
        {
            "aggs": {
                "max_id": {
                    "max": {
                         "field" : "id"
                    }
                }
            }
        }
     */
    Long findMaximalId() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withSearchType(SearchType.COUNT)
                .withIndices(emailIndex)
                .withTypes(emailType)
                .addAggregation(AggregationBuilders.max("max_id").field("id"))
                .build();
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            Aggregations extract(SearchResponse response) {
                return response.getAggregations()
            }
        });
        Map a = aggregations.asMap()
        InternalMax max = a["max_id"]
        return max.value
    }

    /*

        SELECT dt, topic, COUNT(*) as num
        FROM table
        WHERE topic IN topiclist
        GROUP BY dt, topic


        dt  topic
        1   A
        1   B
        2   A
        3   C

        GET /email/email/_search?search_type=count
        {
            "query": {
                "match": {
                    "texts": "Spring"
                }
            },
            "aggs" : {
                "Spring" : {
                    "date_histogram" : {
                      "field" : "sentDate" ,
                      "interval": "week",
                      "format": "yyyy-MM-dd"
                    }
                }
            }
        }

    */

    Histogram<String, Integer> getWeeklyHistogram(String topic) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("texts", topic))
                .withSearchType(SearchType.COUNT)
                .withIndices(emailIndex)
                .withTypes(emailType)
                .addAggregation(
                    AggregationBuilders.dateHistogram(topic)
                        .field("sentDate")
                        .interval(DateHistogram.Interval.WEEK)
                        .format("yyyy-MM-dd"))
                .build();
        Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
            @Override
            Aggregations extract(SearchResponse response) {
                return response.getAggregations()
            }
        });
        Map a = aggregations.asMap()
        InternalDateHistogram hist = a[topic]
        // extract the histogram
        def result = new Histogram<String, Integer>(topic)
        for (def bucket : hist.buckets) {
            result.add(bucket.key, bucket.docCount)
        }
        return result;
    }

    void createIndexIfNotExists() {
        if (!elasticsearchTemplate.indexExists(emailIndex)) {
            elasticsearchTemplate.createIndex(emailIndex)
        }
    }

}
