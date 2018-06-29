package com.litvas.articleservice.service;

import com.litvas.articleservice.domain.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ArticleCollectorImpl implements ArticleCollector {

    private static final String URL_OF_ALL_ARTICLES = "https://habr.com/all/all/";

    public Set<String> getLinksForParsing() {
        Set<String> linkSet = new HashSet<>(5);
        linkExtractor(linkSet);
        return linkSet;
    }

    @Override
    @Async
    public Article collectArticle(String link) {
        return parseArticle(link);
    }

    private Article parseArticle(String link) {
        Article article = null;
        try {
            Document articleForParsing = Jsoup.connect(link).get();
            article = new Article();
            article.setTitle(articleForParsing.
                    getElementsByClass("post__title-text").
                    text());
            article.setContent(articleForParsing.
                    getElementsByClass("post__body post__body_full").
                    text());
        } catch (IOException e) {
            System.out.println("Can`t found page '" + link + "'");
        }
        return article;
    }

    private void linkExtractor(Set<String> linkSet) {
        try {
            Document pageWithLinks = Jsoup.connect(URL_OF_ALL_ARTICLES).get();
            Elements listWithArticles = pageWithLinks.getElementsByClass("post__title_link");
            for (int i = 0; i < 5; i++) {
                Element element = listWithArticles.get(i);
                String link = element.attr("href");
                linkSet.add(link);
            }
        } catch (IOException e) {
            System.out.println("Can`t found page '" + URL_OF_ALL_ARTICLES + "'");
        }
    }
}
