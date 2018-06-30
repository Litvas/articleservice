package com.litvas.articleservice.service;

import com.litvas.articleservice.domain.Article;

import java.util.Set;

public interface ArticleHandler {

    Set<String> getLinksForParsing();

    Article collectArticle(String link);

    Article getOneArticle(String articleTitle);

}
