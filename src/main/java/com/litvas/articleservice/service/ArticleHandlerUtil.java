package com.litvas.articleservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.litvas.articleservice.domain.Article;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
class ArticleHandlerUtil {

    private static final String URL_OF_ALL_ARTICLES = "https://habr.com/all/all/";

    Article parseArticle(String link) {
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

    void linkExtractor(Set<String> linkSet) {
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

    String getArticleTitle(String searchedRequest) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(searchedRequest);
        } catch (IOException e) {
            System.out.println("Can`t parse JSON");
        }
        return jsonNode.get("articleTitle").asText();
    }

    String getRequestedWord(String searchedRequest) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(searchedRequest);
        } catch (IOException e) {
            System.out.println("Can`t parse JSON");
        }
        return jsonNode.get("word").asText();
    }
}
