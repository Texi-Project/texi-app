package com.texi.app.configuration;

import com.texi.app.post.service.impl.SearchPostIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@EnableAutoConfiguration
@Configuration
public class SearchPostConfig {
    @Autowired
    private EntityManager bentityManager;

    @Bean
    SearchPostIml SearchService() {
        SearchPostIml hibernateSearchService = new SearchPostIml(bentityManager);
        hibernateSearchService.initializeHibernateSearch();
        return hibernateSearchService;
    }
}
