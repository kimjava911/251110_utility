package kr.java.utility.controller;

import kr.java.utility.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ArticleController {
    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articles", service.findAll());
        return "list";
    }

    @PostMapping("/add")
    public String add(String title, String email) {
        service.add(title, email);
        return "redirect:/";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") long id, Model model) {
        model.addAttribute("article", service.findById(id));
        return "detail";
    }
}
