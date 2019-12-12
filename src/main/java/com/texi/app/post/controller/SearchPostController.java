package com.texi.app.post.controller;

import com.texi.app.domain.Post;
import com.texi.app.post.service.PostService;
import com.texi.app.post.service.impl.SearchPostIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchPostController {
    @Autowired
    private SearchPostIml searchservice;

    @Autowired
    private PostService cardservice;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String search(@RequestParam(value = "search", required = false) String q, Model model) {
        List<Post> searchResults = null;
        try {

            searchResults = searchservice.fuzzySearch(q);

        } catch (Exception ex) {

        }
        model.addAttribute("search", searchResults);
        return "index";
    }
}