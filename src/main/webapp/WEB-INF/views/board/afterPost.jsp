<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/header.jsp" %>
<div class="container mt-2">
	<div class="row mt-4">
		<div class="col-10 offset-1">
			<div class="card">
				<div class="card-header">
					글 작성중 에러가 발생했습니다.
				</div>
				<div class="card-body">
					<p>${errorMsg} </p>
					<a href="javascript:history.back()" class="btn btn-success">
						돌아가기
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="../include/footer.jsp" %>