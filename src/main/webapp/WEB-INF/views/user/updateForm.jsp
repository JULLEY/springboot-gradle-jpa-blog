<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
    <form action="#" method="post">
        <input type="hidden" id="id" value="${principal.user.id}"/>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="email" name="username" class="form-control" placeholder="Enter Username" id="username" value="${principal.user.username}" readonly>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" name="email" class="form-control" placeholder="Enter email" id="email" value="${principal.user.email}">
        </div>
        <button type="button" id="btn-update" class="btn btn-primary">회원수정완료</button>
    </form>
</div>

<script src="/js/user.js"></script>
<%@include file="../layout/footer.jsp" %>