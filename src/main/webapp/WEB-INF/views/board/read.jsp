<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/header.jsp" %>

<div class="container mt-2">
	<div class="row">
		<div class="col-10 offset-1">
			<div class="card">
				<div class="card-header">
					<div class="row">
						<div class="col-6">
							<span class="badge badge-primary">글번호 : ${board.bno}</span>
							${board.title}
						</div>
						<div class="col-6 text-right">
							<button type="button" class="btn btn-info btn-sm">
								${board.writer }
							</button>
							
							<button type="button" class="btn btn-dark btn-sm">
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm" 
									value="${board.regdate}"/>
							</button>
						</div>
					</div>
				</div>
				<div class="card-body">
					${board.content }
				</div>
			</div><!-- end of card -->
		</div><!--  end of col-10 -->
	</div><!--  end of row -->
	<div class="row">
		<form role="form" method="post">
			<input type="hidden" name="bno" value="${board.bno}">
		</form>
	</div>
	<div class="row mt-3">
		<div class="col-11 text-right">
			<c:if test="${login.uid == board.writer}">
				<a href="/board/modify/${board.bno}/${cri.page}/${cri.perPageNum}?searchType=${cri.searchType}&keyword=${cri.keyword}" class="btn btn-warning">수정</a>
				<a href="/board/remove/${board.bno}/${cri.page}/${cri.perPageNum}?searchType=${cri.searchType}&keyword=${cri.keyword}" class="btn btn-danger">삭제</a>
			</c:if>
			<a href="/board/list/${cri.page}/${cri.perPageNum}?searchType=${cri.searchType}&keyword=${cri.keyword}" class="btn btn-primary">목록보기</a>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp" %>