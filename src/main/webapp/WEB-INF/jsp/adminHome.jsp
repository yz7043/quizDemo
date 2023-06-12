<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        body, html {
            height: 100%;
        }
        .main-content {
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .card{
            width: 100%;
        }
        .btn {
            margin-bottom: 1.5rem;
            font-size: 1.25rem;
            padding: 1rem 2rem;
            width: 100%;
        }
        .card-body {
            padding: 2rem;
        }
    </style>
</head>
<body>
<%@ include file="navbar.jsp"%>
<div class="container main-content">
    <div class="row justify-content-center">
        <div class="col-xl-12 center" style="margin-top: 20px;">
            <div class="card">
                <div class="card-body text-center">
                    <h2 class="card-title">Admin Page</h2>
                    <a href="/adminUserMgmt" class="btn btn-primary mb-2 d-block">User Management</a>
                    <a href="/adminQuizResultMgmt" class="btn btn-primary mb-2 d-block">Quiz Result Management</a>
                    <a href="/adminQuestionMgmt" class="btn btn-primary mb-2 d-block">Quiz Management</a>
                    <a href="/adminContactManagement" class="btn btn-primary mb-2 d-block">Contact Management</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
