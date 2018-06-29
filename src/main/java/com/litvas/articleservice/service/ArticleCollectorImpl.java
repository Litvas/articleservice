package com.litvas.articleservice.service;

import com.litvas.articleservice.domain.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ArticleCollectorImpl implements ArticleCollector {

    @Override
    @Async
    public Article collectArticle() {

        return null;
    }
}
