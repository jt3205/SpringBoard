<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/header.jsp" %>

<div class="container mt-2">
	<div class="row">
		<h1>자유게시판</h1>
	</div>
	<div class="row mt-4">
		<div class="col-8 text-left">
			<form class="form-inline" id="sForm">
				<div class="form-group mr-3">
					<label for="searchType">검색종류</label>
					<select name="searchType" id="searchType" class="form-control">
						<option value="n" <c:out value="${cri.searchType == null ? 'selected' : ''}"/>>
							검색종류
						</option>
						<option value="t" <c:out value="${cri.searchType == 't' ? 'selected' : ''}"/>>
							제목
						</option>
						<option value="c" <c:out value="${cri.searchType == 'c' ? 'selected' : ''}"/>>
							내용
						</option>
						<option value="w" <c:out value="${cri.searchType == 'w' ? 'selected' : ''}"/>>
							작성자
						</option>
						<option value="tc" <c:out value="${cri.searchType == 'tc' ? 'selected' : ''}"/>>
							제목 + 내용
						</option>
						
						<option value="cw" <c:out value="${cri.searchType == 'cw' ? 'selected' : ''}"/>>
							내용 + 작성자
						</option>
						
						<option value="tcw" <c:out value="${cri.searchType == 'tcw' ? 'selected' : ''}"/>>
							제목 + 내용 + 작성자
						</option>
					</select>
				</div>
				<div class="form-group mr-3">
					<label for="keywordInput">검색어</label>
					<input type="text" class="form-control" id="keywordInput" name="keywordInput"
						placeholder="검색어를 입력하세요" value="${cri.keyword}">
				</div>
				<button type="button" id="searchBtn" class="btn btn-success">검색</button>
			</form>
		</div>
		<div class="col-4 text-right">
			<div class="btn-group">
				<button type="button" class="btn btn-primary dropdown-toggle" 
					data-toggle="dropdown">
					게시글 개수 조정
				</button>
				<div class="dropdown-menu">					
					<a class="dropdown-item" href="/board/list/1/10">10개씩</a>
					<a class="dropdown-item" href="/board/list/1/15">15개씩</a>
					<a class="dropdown-item" href="/board/list/1/20">20개씩</a>
				</div>
			</div>
		</div>
	</div>
	<div class="row mt-2">
		<table class="table table-striped table-hover">
			<tr>
				<th width="10%">번호</th>
				<th width="60%">제목</th>
				<th>글쓴이</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${list}" var ="boardVO">
				<tr>
					<td>${boardVO.bno}</td>
					<td><a href="/board/read/${boardVO.bno}${pageMaker.ms(pageMaker.cri.page)}">${boardVO.title}</a></td>
					<td>${boardVO.writer}</td>
					<td>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm" 
							value="${boardVO.regdate}"/>
					</td>
					<td>
						<span class="badge badge-dark">${boardVO.viewcnt }</span>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="row my-2">
		<div class="col-12 text-right">
			<a href="/board/register" class="btn btn-success">글쓰기</a>
		</div>
	</div>
	<div class="row mt-2">
		<div class="col-12">
			<ul class="pagination justify-content-center">
				<c:if test="${pageMaker.prev}">
					<li class="page-item">
						<a class="page-link" href="/board/list${pageMaker.ms(pageMaker.startPage - 1)}">
	       					<span aria-hidden="true">&laquo;</span>
	     				</a>
					</li>
				</c:if>
				
				<c:forEach begin="${pageMaker.startPage}"
					 end="${pageMaker.endPage }" var="idx">
					<li class="page-item 
						<c:out value="${pageMaker.cri.page == idx ? 'active' : '' }"/>">
						<a class="page-link" href="/board/list${pageMaker.ms(idx)}">${idx}</a>
					</li>
				</c:forEach>
				
				<c:if test="${pageMaker.next}">
					<li class="page-item">
						<a class="page-link" href="/board/list${pageMaker.ms(pageMaker.endPage + 1)}">
	       					<span aria-hidden="true">&raquo;</span>
	     				</a>
					</li> 
				</c:if>
			</ul>
		</div>
	</div>
</div>

<%@ include file="../include/footer.jsp" %>

<script>
$("#searchBtn").on("click", function(e){
		self.location = "/board/list${pageMaker.mq(1)}"
			+ "?searchType=" + $("select option:selected").val()
			+ "&keyword=" + $("#keywordInput").val();
});
$("#sForm").on("submit", function(e){
	$("#searchBtn").click();	
	return false;
});
</script>