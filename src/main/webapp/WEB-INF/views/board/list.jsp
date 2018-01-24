<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../include/header.jsp"%>

<div class="container mt-2">
	<div class="row">
		<h1>자유게시판</h1>
	</div>

	<div class="row mt-4">
		<table class="table table-striped table-hover">
			<tr>
				<th width="10%">번호</th>
				<th width="60%">제목</th>
				<th>글쓴이</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${list}" var="boardVO">
				<tr>
					<td>${boardVO.bno}</td>
					<td><a href="/board/read/${boardVO.bno}">${boardVO.title}</a></td>
					<td>${boardVO.writer}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
							value="${boardVO.regdate}" /></td>
					<td><span class="badge badge-dark">${boardVO.viewcnt }</span>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="row mt-2">
		<div class="col-12">
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev}">
					<li class="page-item"><a class="page-link" href="${pageMaker.startPage-1}"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>

				<c:forEach begin="${pageMaker.startPage}"
					end="${pageMaker.endPage }" var="idx">
					<li
						class="page-item <c:out value="${pageMaker.cri.page == idx ? 'active' : '' }"/>">
						<a class="page-link" href="/board/list/${idx}">${idx}</a>
					</li>
				</c:forEach>

				<c:if test="${pageMaker.next}">
					<li class="page-item"><a class="page-link" href="${pageMaker.endPage+1}"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>