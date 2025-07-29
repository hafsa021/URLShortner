<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>URL Shortener</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-5">
    <h2 class="mb-4">URL Shortener</h2>

    <form method="post" action="/shorten">
        <div class="mb-3">
            <label for="url" class="form-label">Enter Long URL</label>
            <input type="text" class="form-control" id="url" name="url" required/>
        </div>
        <button type="submit" class="btn btn-primary">Shorten</button>
    </form>

    <c:if test="${not empty shortUrl}">
        <div class="alert alert-success mt-4">
            <strong>Short URL:</strong>
            <a href="${shortUrl}" target="_blank">${shortUrl}</a>
        </div>
    </c:if>
</body>
</html>
