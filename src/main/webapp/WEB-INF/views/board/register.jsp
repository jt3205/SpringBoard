<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="container mt-2">
	<div class="row">
		<div class="col-12">
			<h1>글 등록 페이지</h1>
		</div>
	</div>
	<div class="row mt-4">
		<div class="col-10 offset-1">
			<form method="POST">
				<div class="form-group row">
					<label for="writer" class="col-md-2 col-form-label"> 작성자 </label>

					<div class="col-md-10">
						<input type="text" class="form-control" id="writer" name="writer"
							placeholder="작성자 ID입력">
					</div>
				</div>
				<div class="form-group row">
					<label for="title" class="col-md-2 col-form-label"> 글 제목 </label>

					<div class="col-md-10">
						<input type="text" class="form-control" id="title" name="title"
							placeholder="글 제목을 입력하세요">
					</div>
				</div>
				<div class="form-group">
					<label for="content">글 내용</label>
					<textarea class="form-control" id="content" name="content" row="6"
						placeholder="글 내용 입력"></textarea>
				</div>

				<button type="submit" class="btn btn-primary">작성</button>
			</form>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>