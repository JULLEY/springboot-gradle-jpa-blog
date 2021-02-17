<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="layout/header.jsp" %>

<div class="container">
    <h1 class="display-4">ANDROID WEEKLY</h1>
    <c:forEach var="info" items="${crawlingList}">
        <div class="card m-2">
            <div class="card-body">
                <c:if test="${not empty info.imageUrl}">
                    <a href="${info.link}" target="_blank">
                        <img src="${info.imageUrl}" claa="img-thumbnail"
                            style="border: 1px solid #333333;
                            height: auto;
                            line-height: 100%;
                            outline: none;
                            text-decoration: none;
                            display: inline;"
                        />
                    </a>
                    <br/>
                </c:if>

                <a class="article-headline" target="_blank" style="color:#0099CC; text-decoration: none; font-weight: bold;" href="${info.link}">
                    ${info.title}
                </a>
                <p style="margin:0; line-height: 1.5em; font-size: 12px;">
                    ${info.content}
                </p>
            </div>

        </div>
    </c:forEach>
</div>

<%@include file="layout/footer.jsp" %>