package com.litvas.articleservice.service;

import com.litvas.articleservice.domain.Article;
import com.litvas.articleservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ArticleHandlerImpl implements ArticleHandler {

    @Autowired
    private ArticleHandlerUtil articleHandlerUtil;

    @Autowired
    private ArticleRepository articleRepository;

    public Set<String> getLinksForParsing() {
        Set<String> linkSet = new HashSet<>(5);
        articleHandlerUtil.linkExtractor(linkSet);
        return linkSet;
    }

    @Override
    @Async
    public Article collectArticle(String link) {
        Article article = articleHandlerUtil.parseArticle(link);
        articleRepository.save(article);
        return article;
    }

    @Override
    public Article getOneArticle(String articleTitle) {
        return articleRepository.findByTitle(articleTitle);
    }
}
