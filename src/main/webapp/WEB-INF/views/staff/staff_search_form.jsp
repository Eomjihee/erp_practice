<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <title>ERP 예제</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" href="<c:url value="/resources/css/erp.css"/>" /> 
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"></script>
</head>
<body>

<div class="container-fluid">
 <form:form action="search.do" method="post" modelAttribute="searchForm" id="search-form">
  <div class="row">
  <!-- 검색 form -->
  	<div class="col-sm-12 mt-2">
  		<div class="row">
  			<div class="col-sm-12">
		  		<table class="table table-bordered" id="search-form-table" >
		  			<tbody>
		  				<tr>
		  					<td colspan="6">
		  						<h3 class="text-center">사원 정보 검색</h3>
		  					</td>
		  				</tr>
		  				<tr>
		  					<td>
		  						<div>이름</div>
		  					</td>
		  					<td>
		  						<div class="form-group">
		  							<form:input type="text" class="form-control" path="name"/>
		  						</div>
		  					</td>
		  					<td>
		  						<div>성별</div>
		  					</td>
		  					<td>
		  						<div class="form-group">
		  							<form:checkbox id="radio-male" class="input-control" 
		  								value="male" path="gender"/>
		  							<label for="radio-male">남</label>
		  							<form:checkbox id="radio-female" class="input-control"  
		  								value="female" path="gender"/>
		  							<label for="radio-female">여</label>
		  						</div>
		  					</td>
		  					<td>
		  						<div>부서</div>
		  					</td>
		  					<td>
		  						<div class="form-group">
		  							<form:select class="form-control" path="department" id="dept">
		  								<option value="" selected="selected"></option>
			  							<c:if test="${not empty deptList}">	
			  								<c:forEach items="${deptList }" var="dept">
			  								<form:option value="${dept.code }" >${dept.name }</form:option>
			  								</c:forEach>
			  							</c:if>	
		  							</form:select>
		  						</div>
		  					</td>
		  				</tr>
		  				<tr>
		  					<td>
		  						<div>학력</div>
		  					</td>
		  					<td>
		  						<div class="form-group">
		  						<!-- list jstl 구현 -->
		  						<c:if test="${not empty schoolList}">
			  						<c:forEach items="${schoolList }" var="school">
			  							<form:checkbox class="input-control" id="education-${school.code }" 
			  								path="education" value="${school.code }"/>
			  							<label for="education-${school.code }">${school.name }</label>
			  						</c:forEach>
		  						</c:if>
		  						</div>
		  					</td>
		  					<td>
		  						<div>기술</div>
		  					</td>
		  					<td colspan="3">
		  						<div class="form-group">
		  						<!-- checkbox list 기술 jstl 구현 -->
		  						<c:if test="${not empty skillList}">
			  						<c:forEach items="${skillList }" var="skill">
			  							<form:checkbox class="input-control" 
			  							path="skill" name="skill" value="${skill.code }" id="skill-${skill.code }"/>
			  							<label for="skill-${skill.code }">${skill.name }</label>
			  						</c:forEach>
		  						</c:if>
		  						</div>
		  						<div class="form-group">
		  							<label for="">키워드
		  							</label>
		  							<form:radiobutton path="keywordType" id="keyword-type-or" value="or"/>
		  							<label for="keyword-type-or">OR</label>
		  							<form:radiobutton path="keywordType" id="keyword-type-and" value="and"/>
		  							<label for="keyword-type-and">AND</label>
		  							<form:input type="text" class="form-control" path="keyword" id="keyword-box"/>
		  						</div>
		  					</td>
		  				</tr>
		  				<tr>
		  					<td>
		  						<div>졸업일</div>
		  					</td>
		  					<td colspan="5">
		  						<div class="form-group text-center">
		  							<fmt:formatDate value="${todayDate }" var="todayYear" pattern="yyyy"/>
		  							<fmt:formatDate value="${todayDate }" var="todayMonth" pattern="MM"/>
		  							<fmt:formatDate value="${todayDate }" var="todayDay" pattern="dd"/>
			  						<span>
				  						<form:select path="startYear" id="start-year" class="input-control">
				  							<option value="" selected="selected"></option>
				  							<c:forEach begin="0" end="${todayYear - 1970 }" var="i">
				  								<c:set var="yearOption" value="${todayYear - i }" />
				  								<form:option value="${yearOption }"
				  								>${yearOption}</form:option>
				  							</c:forEach>
				  						</form:select>
				  						<span>년</span>
				  						<form:select path="startMonth" id="start-month" class="input-control">
				  							<option value=""></option>
				  							<c:forEach begin="1" end="12" var="month">
				  								<form:option value="${month }">
				  									<fmt:formatNumber pattern="00" value="${month }" />
				  								</form:option>
				  							</c:forEach>
				  						</form:select>
				  						<span>월</span>
				  						<form:select path="startDay" id="start-day" class="input-control">
				  							<option value=""></option>
				  							<c:forEach begin="1" end="31" var="day">
					  							<form:option value="${day }">
						  							<fmt:formatNumber pattern="00" value="${day }" />
					  							</form:option>
				  							</c:forEach>	
				  						</form:select>
				  						<span>일</span>
			  						</span>
			  						<span style="margin:0 20px 0 20px;">~</span>
			  						<span>
			  							<form:select path="endYear" id="end-year" class="input-control">
				  							<option value="" selected="selected"></option>
				  							<c:forEach begin="0" end="${todayYear - 1970 }" var="i">
				  								<c:set var="yearOption" value="${todayYear - i }" />
				  								<form:option value="${yearOption }">
				  								${yearOption}
				  								</form:option>
				  							</c:forEach>
				  						</form:select>
				  						<span>년</span>
				  						<form:select path="endMonth" id="end-month" class="input-control">
				  							<option value=""></option>
				  							<c:forEach begin="1" end="12" var="month">
				  								<form:option value="${month }">
				  									<fmt:formatNumber pattern="00" value="${month }" />
				  								</form:option>
				  							</c:forEach>
				  						</form:select>
				  						<span>월</span>
				  						<form:select path="endDay" id="end-day" class="input-control">
				  							<option value=""></option>
				  							<c:forEach begin="1" end="31" var="day">
					  							<form:option value="${day }">
					  								<fmt:formatNumber pattern="00" value="${day }" />
					  							</form:option>
				  							</c:forEach>	
				  						</form:select>
				  						<span>일</span>
			  						</span>
		  						</div>
		  					</td>
		  				</tr>
		  			</tbody>
		  		</table>
  			</div>
  			<div class="col-sm-12">
  				<div class="row">
  					<div class="col-sm-6 text-right">
		  				<button style="width: 10rem;" class="btn btn-primary" type="submit">검색</button>
  					</div>
  					<div class="col-sm-6 text-right">
		  				<button class="btn btn-primary" type="button" id="allSearch-btn">전부검색</button>
		  				<button class="btn btn-primary" type="button" id="reset-search-btn">초기화</button>
		  				<button class="btn btn-primary" type="button" data-toggle="modal" data-target="#staff-input-modal">등록</button>
  					</div>
  					<form:input type="hidden" path="isAllSearch" value="false" id="isAllSearch" />
  				</div>
  			</div>
  		</div>
  		
  	</div>
  	<!-- 검색결과 -->
  	<div class="col-sm-12 mt-5">
  		<c:if test="${staffList ne null }">
  		<div class="col-sm-12 mt-1">
  			<p class="text-right">검색결과 -> <span>${pagination.totalRows }</span> 건</p>
  		</div>
  		<table class="table table-bordered">
  			<colgroup>
  			<col width="10%">
  			<col width="20%">
  			<col width="10%">
  			<col width="20%">
  			<col width="20%">
  			<col width="20%">
  			</colgroup>
  			<thead class="table-dark text-center">
  				<tr>
  					<th>번호 
  						<span class="ml-1">
  							<button type="button" class="btn btn-dark btn-sm" id="no-arrow"><i class="far fa-caret-square-${searchForm.noBtn }"></i></button>
  							<form:input type="hidden" path="noBtn" value="${searchForm.noBtn }" id="no-arrow-val"/>
  						</span>
  					</th>
  					<th>이름
  						<span class="ml-1">
  							<button type="button" class="btn btn-dark btn-sm" id="name-arrow"><i class="far fa-caret-square-${searchForm.nameBtn }"></i></button>
  							<form:input type="hidden" path="nameBtn" value="${searchForm.nameBtn }" id="name-arrow-val"/>
  						</span>
  					</th>
  					<th>성별
  						<span class="ml-1">
  							<button type="button" class="btn btn-dark btn-sm" id="gender-arrow"><i class="far fa-caret-square-${searchForm.genderBtn }"></i></button>
  							<form:input type="hidden" path="genderBtn" value="${searchForm.genderBtn }" id="gender-arrow-val"/>
  						</span>
  					</th>
  					<th>부서
  						<span class="ml-1">
  							<button type="button" class="btn btn-dark btn-sm" id="dept-arrow"><i class="far fa-caret-square-${searchForm.deptBtn }"></i></button>
  							<form:input type="hidden" path="deptBtn" value="${searchForm.deptBtn }" id="dept-arrow-val"/>
  						</span>
  					</th>
  					<th>졸업일
  						<span class="ml-1">
  							<button type="button" class="btn btn-dark btn-sm" id="graduate-arrow"><i class="far fa-caret-square-${searchForm.graduateBtn }"></i></button>
  							<form:input type="hidden" path="graduateBtn" value="${searchForm.graduateBtn }" id="graduate-arrow-val"/>
  						</span>
  					</th>
  					<th></th>
  				</tr>
  			</thead>
  			<tbody class="text-center">
  			<c:forEach items="${staffList }" var="staff" varStatus="status">
  				<c:set var="sum" value="${sum+1}"/>
  				<tr>
  					<td>${staff.rowNumber}</td>
  					<td>${staff.name }</td>
  					<td>${staff.gender }</td>
  					<td>${staff.department.name }</td>
  					<td><fmt:formatDate value="${staff.graduateDay }" pattern="yyyy-MM-dd" /></td>
  					<td>
  						<button type="button" class="btn btn-secondary updel-btn" 
  						data-toggle="modal" 
  						data-staff="${staff.no }">수정/삭제</button>
  					</td>
  				</tr>
  			</c:forEach>
  			
  			</tbody>
  		</table>
  		</c:if>
  	</div>
  	<div class="col-sm-12 mt-1 d-flex justify-content-center">
  	<c:if test="${pagination ne null }">
			<nav aria-label="Page navigation">
				<ul class="pagination text-center">
					<c:if test="${pagination.pageNo > 1 }" >
						<li class="page-item">
							<button type="button" class="page-link page-before" data-page="${page - 1 }"
									aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										<span class="sr-only">이전</span>
							</button>
						</li>
					</c:if>
					<c:forEach begin="${pagination.beginPage }" end="${pagination.endPage }" var="page">
					<li class="page-item  ${pagination.pageNo eq page ? 'active' : '' }">
						<button type="button" class="page-link page-no" data-page="${page }" >${page }</button>
					</li>
					</c:forEach>
					<c:if test="${pagination.pageNo < pagination.totalPages }"> 
					<li class="page-item"><button type="button" class="page-link page-after" data-page="${page + 1 }" 
						aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
								class="sr-only">다음</span>
							</button>
					</li>
					</c:if>
				</ul>
			</nav>
			<form:input type="hidden" path="startDate" name="startDate" value="" id="search-startDate"/>
			<form:input type="hidden" path="endDate" name="endDate" value="" id="search-endDate"/>
			<form:input type="hidden" path="pageNo" value="${pagination.pageNo }" id="hidden-page-no"/>
	</c:if>
	</div>
  </div>
  </form:form>
</div>
<%@ include file="./staff_input_form.jsp" %>
<%@ include file="./staff_updel_form.jsp" %>
<script type="text/javascript" src="<c:url value="/resources/js/erp.js" />"></script>
</body>
</html>