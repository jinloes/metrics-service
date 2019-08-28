package com.jinloes.metrics_api.data;

import com.jinloes.metrics_api.model.UserAction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionsRepoistory extends ElasticsearchRepository<UserAction, String> {

}
