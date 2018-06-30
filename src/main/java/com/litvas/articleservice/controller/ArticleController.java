package com.litvas.articleservice.controller;

import com.litvas.articleservice.service.ArticleHandler;
import com.litvas.articleservice.service.WordSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/words")
public class ArticleController {

    @Autowired
    private ArticleHandler articleHandler;

    @Autowired
    private WordSearcher wordSearcher;

    @PostMapping
    public int getAllCoincidence(@RequestBody String searchedReques) throws IOException {
        return wordSearcher.searchMathes(searchedReques);
    }
}
