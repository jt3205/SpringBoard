<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/header.jsp" %>

<div class="container mt-2">
	<div class="row">
		<div class="col-10 offset-1">
			<form method="post">
				<div class="form-group row">
					<label for="writer" class="col-md-2 col-form-label" readonly>
						작성자
					</label>
					<div class="col-md-10">
						<input type="text"  class="form-control-plaintext" id="writer"
							 name="writer" value="${board.writer}">
					</div>
				</div>
				<div class="form-group row">
					<label for="title" class="col-md-2 col-form-label">
						글 제목
					</label>
					<div class="col-md-10">
						<input type="text"  class="form-control" id="title"
							 name="title" value="${board.title }">
					</div>
				</div>
				<div class="form-group">
					<label for="content">글 내용</label>
					<textarea class="form-control" id="content" name="content"
					rows="6" >${board.content }</textarea>
				</div>
				<div class="row mt-3">
					<div class="col-11 text-right">
						<button type="button" class="btn btn-primary" id="save">작성완료</button>
						<button type="button" class="btn btn-warning" id="cancel">취소</button>
					</div>
				</div>
			</form>
			
		</div><!--  end of col-10 -->
	</div><!--  end of row -->
</div>

<%@ include file="../include/footer.jsp" %>

<script>
	$("#cancle").on("click", function() {
		self.location = "/board/list";
	});
</script>