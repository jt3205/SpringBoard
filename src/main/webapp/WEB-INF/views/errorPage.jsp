<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-10 offset-1">
			<div class="card">
				<div class="card-header">
					${title}
				</div>
				<div class="card-body">
					${msg}
				</div>
				<div class="card-footer">
					<a href="javascript:history.back()" class="btn btn-success">
						돌아가기
					</a>
				</div>
			</div>
		</div>
	</div>
</div>


<%@ include file="include/footer.jsp" %>