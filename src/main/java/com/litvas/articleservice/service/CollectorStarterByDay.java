package com.litvas.articleservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CollectorStarterByDay implements CommandLineRunner {

    @Autowired
    private ArticleCollector articleCollector;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            articleCollector.collectArticle();
        }
    }
}