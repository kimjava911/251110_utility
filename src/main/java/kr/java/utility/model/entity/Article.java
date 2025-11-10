package kr.java.utility.model.entity;

//public class Article {
//    private final long id; // 데이터가 구분이 가능한 식별자
//    private final String title;
//    private final String authorEmail;
//
//    // 생성자
//
//    // Getter
//}

public record Article(
        long id,
        String title,
        String authorEmail
) {
}
