package kr.java.utility.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.java.utility.model.entity.Article;
import kr.java.utility.service.ArticleService;
import kr.java.utility.util.MaskerUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class ArticleController {
    private final ArticleService service;
    private final MaskerUtil maskerUtil;

    public ArticleController(ArticleService service, MaskerUtil maskerUtil) {
        this.service = service;
        this.maskerUtil = maskerUtil;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articles", service.findAll());
        return "list";
    }

    @PostMapping("/add")
    public String add(@RequestParam("title") String title,
                      @RequestParam("email") String email,
                      HttpServletRequest request) {
//        service.add(title, email);
        String safeTitle = StringUtils.trimAllWhitespace(title);
        String trimEmail = StringUtils.trimAllWhitespace(email);
        String safeEmail = StringUtils.hasText(trimEmail) ? trimEmail
                : "unknown@example.com";
        service.add(safeTitle, safeEmail);
        // /detail?id=번호 -> 작성 시에 해당 링크로 이동
//        return "redirect:/"; // 메인화면으로..
//        String redirect = UriComponentsBuilder
//                .fromPath(request.getContextPath())
//                .path("/")
//                .build()
//                .toUriString(); // 메인화면
        String redirect = UriComponentsBuilder
                .fromPath(request.getContextPath())
                .path("/detail")
                .queryParam("id", service.findAll().size())  // ?
                .build()
                .toUriString(); // 메인화면
        return "redirect:" + redirect;
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("id") long id, Model model) {
//        model.addAttribute("article", service.findById(id));
        Article a = service.findById(id);
        if (a != null) {
            String masked = maskerUtil.maskEmail(a.authorEmail());
            model.addAttribute("article", a);
            model.addAttribute("maskedEmail", masked);
        }
        return "detail";
    }
}
