<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="modal fade" id="staff-input-modal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    
<div class="container">
 <form action="add.do" method="post" id="add-form">
  <div class="row">
  <!-- 검색 form -->
  	<div class="col-sm-12 mt-2">
  		<div class="row">
  			<div class="col-sm-12">
		  		<table class="table table-bordered" id="search-form-table" >
		  			<tbody>
		  				<tr>
		  					<td colspan="6">
		  						<h3 class="text-center">사원 정보 등록</h3>
		  					</td>
		  				</tr>
		  				<tr>
		  					<td>
		  						<div>이름</div>
		  					</td>
		  					<td>
		  						<div class="form-group">
		  							<input type="text" class="form-control" name="name" id="staff-name"/>
		  						</div>
		  					</td>
		  					<td>
		  						<div>주민번호</div>
		  					</td>
		  					<td>
		  						<div class="form-group">
		  							<input type="text" class="form-control" id="jumin-first-box" name="juminFirst" style="max-width: 5rem;"/>
		  							<span> - </span>
		  							<input type="password" class="form-control" id="jumin-second-box" name="juminSecond" style="max-width: 5rem;"/>
		  						</div>
		  					</td>
		  					<td>
		  						<div>부서</div>
		  					</td>
		  					<td>
		  						<div class="form-group">
		  							<select class="form-control" name="departmentCode" id="add-dept">
		  								<option value="" selected="selected"></option>
			  							<c:if test="${not empty deptList}">	
			  								<c:forEach items="${deptList }" var="dept">
			  								<option value="${dept.code }" >${dept.name }</option>
			  								</c:forEach>
			  							</c:if>	
		  							</select>
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
			  							<input type="radio" class="input-control" id="add-education-${school.code }" 
			  								name="education" value="${school.code }"/>
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
			  							<input type="checkbox" class="input-control" 
			  							name="skillCode" value="${skill.code }" id="add-skill-${skill.code }"/>
			  							<label for="skill-${skill.code }">${skill.name }</label>
			  						</c:forEach>
		  						</c:if>
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
				  						<select name="startYear" id="add-start-year" class="input-control">
				  							<option value="" selected="selected"></option>
				  							<c:forEach begin="0" end="${todayYear - 1970 }" var="i">
				  								<c:set var="yearOption" value="${todayYear - i }" />
				  								<option value="${yearOption }"
				  								>${yearOption}</option>
				  							</c:forEach>
				  						</select>
				  						<span>년</span>
				  						<select name="startMonth" id="add-start-month" class="input-control">
				  							<option value=""></option>
				  							<c:forEach begin="1" end="12" var="month">
				  								<option value="${month }">
				  									<fmt:formatNumber pattern="00" value="${month }" />
				  								</option>
				  							</c:forEach>
				  						</select>
				  						<span>월</span>
				  						<select name="startDay" id="add-start-day" class="input-control">
				  							<option value=""></option>
				  							<c:forEach begin="1" end="31" var="day">
					  							<option value="${day }">
						  							<fmt:formatNumber pattern="00" value="${day }" />
					  							</option>
				  							</c:forEach>	
				  						</select>
				  						<span>일</span>
			  						</span>
			  						<span style="margin:0 20px 0 20px;">~</span>
			  						<span>
			  							<select name="endYear" id="add-end-year" class="input-control">
				  							<option value="" selected="selected"></option>
				  							<c:forEach begin="0" end="${todayYear - 1970 }" var="i">
				  								<c:set var="yearOption" value="${todayYear - i }" />
				  								<option value="${yearOption }">
				  								${yearOption}
				  								</option>
				  							</c:forEach>
				  						</select>
				  						<span>년</span>
				  						<select name="endMonth" id="add-end-month" class="input-control">
				  							<option value=""></option>
				  							<c:forEach begin="1" end="12" var="month">
				  								<option value="${month }">
				  									<fmt:formatNumber pattern="00" value="${month }" />
				  								</option>
				  							</c:forEach>
				  						</select>
				  						<span>월</span>
				  						<select name="endDay" id="add-end-day" class="input-control">
				  							<option value=""></option>
				  							<c:forEach begin="1" end="31" var="day">
					  							<option value="${day }">
					  								<fmt:formatNumber pattern="00" value="${day }" />
					  							</option>
				  							</c:forEach>	
				  						</select>
				  						<span>일</span>
			  						</span>
		  						</div>
		  					</td>
		  				</tr>
		  			</tbody>
		  		</table>
  			</div>
  			<div class="col-sm-12 mb-3">
  				<div class="row">
  					<div class="col-sm-6 text-right">
		  				<button style="width: 10rem;" class="btn btn-primary" type="submit">등록</button>
  					</div>
  					<div class="col-sm-6 text-right">
		  				<button class="btn btn-primary" type="reset">초기화</button>
  					</div>
  				</div>
  			</div>
  		</div>
  		
  	</div>
  </div>
  </form>
</div>
</div>
  </div>
</div>
