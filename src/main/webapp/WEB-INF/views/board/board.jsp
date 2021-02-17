<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
    <a href="/board/saveForm" class="btn btn-outline-dark">글쓰기</a>
    <c:forEach var="board" items="${boards.content}">
        <div class="card m-2">
            <div class="card-body">
                <h4 class="card-title">${board.title}</h4>
                <a href="/board/${board.id}" class="btn btn-dark">상세</a>
            </div>
        </div>
    </c:forEach>

    <c:if test="${not empty boards.content}">
        <ul class="pagination justify-content-center">
            <li class="page-item ${boards.first ? 'disabled' : ''}"><a class="page-link" href="?page=${boards.number - 1}">Previous</a></li>
            <li class="page-item ${boards.last ? 'disabled' : ''}"><a class="page-link" href="?page=${boards.number + 1}">Next</a></li>
        </ul>
    </c:if>
</div>

<%@include file="../layout/footer.jsp" %>