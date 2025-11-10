<%@ page import="kr.java.utility.model.entity.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>기사 상세</title>
</head>
<body>
    <h1>기사 상세</h1>
    <%
        Article a = (Article) request.getAttribute("article");
        if (a != null) {
    %>
    <p>ID: <%= a.id() %></p>
    <p>제목: <%= a.title() %></p>
<%--    <p>작성자: <%= a.authorEmail()%></p>--%>
    <p>작성자: <%= request.getAttribute("maskedEmail")%></p>
    <%
        } else {
    %>
    <p>기사가 없습니다</p>
    <% }%>
    <a href="<%= request.getContextPath() %>/">뒤로 가기</a>
</body>
</html>
