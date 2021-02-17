<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@include file="../layout/header.jsp" %>

<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>

        <button id="btn-login" class="btn btn-primary">로그인</button>
        <br/>
        <br/>
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=5de05d1f67e121108feaf32a28716d20&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_button.png" /></a>
    </form>
    <br/>
    <a href="/oauth2/authorization/google" >
        <img src="/image/btn_google_signin.png" alt="google" width="14%">
    </a>
    <br />
    <a href="/oauth2/authorization/facebook">
        <img src="https://pngimage.net/wp-content/uploads/2018/06/login-with-facebook-button-png-transparent-1.png" alt="facebook" width="14%">
    </a>
    <br />
    <a href="/oauth2/authorization/naver">
        <img src="/image/naver_login_button.PNG" alt="naver" width="14%">
    </a>
</div>

<%--<script src="/js/user.js"></script>--%>
<%@ include file="../layout/footer.jsp"%>