<%@ page import="java.util.List" %>
<%@ page import="kr.java.utility.model.entity.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>기사 목록</title>
</head>
<body>
    <%
        List l = (List) request.getAttribute("articles");
        if (l != null) {
            for (Article a : (List<Article>) request.getAttribute("articles")) {
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
</body>
</html>
