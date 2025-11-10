package kr.java.utility.service;

import kr.java.utility.model.entity.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    private final List<Article> store = new ArrayList<>();

    public ArticleService() {
        store.add(new Article(1, "Hello Spring MVC", "apple@example.com"));
        store.add(new Article(2, "Utility 101", "bear@example.com"));
    }

    public List<Article> findAll() {
//        return store; // -> Service 바로 가져가기 때문에 편집...
        return new ArrayList<>(store); // 새로운 객체를 만들면서 연결관계 X.
    }

    public Article findById(long id) {
//        for (Article article : store) {
//            if (article.id() == id) {
//                return article;
//            }
//        }
//        return null;
        return store.stream().filter(row -> row.id() == id).findFirst()
                // optional -> null로부터 자유로운 대신에 Optional 처리 방식을 알려줘야함
//                .get();
//                .orElse(null);
                .orElseThrow();
    }

    public void add(String title, String email) {
        long size = store.size();
        store.add((new Article(size + 1, title, email)));
    }

    public boolean isEmpty() { return store.isEmpty(); }
}
