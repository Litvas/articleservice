package com.litvas.articleservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CollectorStarter implements CommandLineRunner {

    @Autowired
    private ArticleCollector articleCollector;

    @Override
    public void run(String... args) throws Exception {
        Set<String> linksForParsing = articleCollector.getLinksForParsing();
        for (String link : linksForParsing) {
            articleCollector.collectArticle(link);
        }
    }
}