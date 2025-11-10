<%@ page import="java.util.List" %>
<%@ page import="kr.java.utility.model.entity.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>기사 목록</title>
</head>
<body>
    <h1>기사 목록</h1>
    <ul>
        <%
            List<Article> list = (List) request.getAttribute("articles");
            if (list != null) {
                for (Article a : list) {
        %>
        <li>
            <a href="<%= request.getContextPath() + "/detail?id=%s".formatted(a.id()) %>">
                이동하기
            </a>
            <p><%= a.id() %></p>
            <p><%= a.title() %></p>
        </li>
        <%
                }
            }
        %>
    </ul>
    <h1>기사 추가</h1>
    <form action="<%= request.getContextPath() + "/add" %>" method="post">
        <input name="title" placeholder="제목">
        <input name="email" placeholder="이메일">
        <button>추가</button>
    </form>
</body>
</html>
