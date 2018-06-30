package com.litvas.articleservice.service;

import com.litvas.articleservice.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class WordSearcherImpl implements WordSearcher {

    @Autowired
    private ArticleHandler articleHandler;

    @Autowired
    private ArticleHandlerUtil articleHandlerUtil;

    @Override
    public int searchMathes(String requestedString) {
        Article article = articleHandler.getOneArticle(articleHandlerUtil.getArticleTitle(requestedString));
        String requestedWord = articleHandlerUtil.getRequestedWord(requestedString);
        return StringUtils.countOccurrencesOf(article.getContent().toUpperCase(), requestedWord.toUpperCase());
    }
}
