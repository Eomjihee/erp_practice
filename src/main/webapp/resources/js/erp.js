$(function(){
	//오름차순 & 내림차순

	toggleArrow($('#no-arrow'));
	toggleArrow($('#name-arrow'));
	toggleArrow($('#gender-arrow'));
	toggleArrow($('#dept-arrow'));
	toggleArrow($('#graduate-arrow'));
	
	function toggleArrow($id){
		
		$id.click(function(){
			var id = $(this).attr('id');
			
			var $arrowId = $('#'+id+'-val');
			var arrowVal = $arrowId.val();
			
			if(arrowVal === 'up'){

				$('#no-arrow-val').val('up');
				$('#name-arrow-val').val('up');
				$('#gender-arrow-val').val('up');
				$('#dept-arrow-val').val('up');
				$('#graduate-arrow-val').val('up');
				
				$arrowId.val('down');
			} else {
				$arrowId.val('up');
			}
			
			
			$('#search-form').submit();
		})
		
	}


	//수정 & 삭제
	$('.updel-btn').click(function(){
		$('#updel-form')[0].reset();

		var staffNo = $(this).data('staff');
		var data = new Object();
		data.staffNo = staffNo;
		
		$.ajax({
			url:'/get.do',
			type:'POST',
			contentType: 'application/json',
			data:JSON.stringify(data),
			success:function(result){
				console.log(result);
				var staff = result.staff;
				var no = staff.no;
				var name = staff.name;
				var jumin_first = staff.juminNo.split('-')[0];
				var jumin_second = staff.juminNo.split('-')[1];
				var deptCode = staff.department.code;
				var schoolCode = staff.school.code;
				$('#del-staff-no').val(no);
				$('#staff-no').val(no);
				$('#updel-staff-name').val(name);
				$('#updel-jumin-first-box').val(jumin_first);
				$('#updel-jumin-second-box').val(jumin_second);
				$('#updel-dept').val(deptCode);
				$('#updel-education-'+schoolCode).prop('checked',true);
				
				var staffSkillArray = result.staffSkillList;
				for(var i in staffSkillArray){
					var skill = staffSkillArray[i].skill;
					$('#updel-skill-'+skill).prop('checked',true);
				}
				console.log(staff.graduateDay);
				
				var gDate = staff.graduateDay;
				var gYear = Number(gDate.split('-')[0]);
				var gMonth = Number(gDate.split('-')[1]);
				var gDay = Number(gDate.split('-')[2]);
				
				console.log(gYear,gMonth,gDay);
				$('#updel-end-year').val(gYear);
				$('#updel-end-month').val(gMonth);
				$('#updel-end-day').val(gDay);
				
			},
			error:function(){
				
			}
			
		})
		$('#staff-updel-modal').modal('show');
		
	})
	$('#update-action-btn').click(function(){
		
		var $no = $('#staff-no');
		var $name = $('#updel-staff-name');
		var $jumin_1 = $('#updel-jumin-first-box');
		var $jumin_2 = $('#updel-jumin-second-box');
		var $edu = $('#updel-form input[name=education]');
		var $dept = $('#updel-dept');
		var $skill = $('#updel-form input[name=skillCode]');
		
		var endYear = $('#updel-end-year').val();
		var endMonth = $('#updel-end-month').val();
		var endDay = $('#updel-end-day').val();
		var endDate = changeDate(endYear, endMonth, endDay);
		
		var regex= /^[0-9]*$/;
		
		if($name.val() === ''){
			alert('이름을 입력해주세요');
			return false;
		}
		//주민번호 체크
		var availableJumin = ssnCheck($jumin_1.val(), $jumin_2.val())
		if(!availableJumin){
			return false;
		}
		var isDuplJumin = duplicatedJuminCheck($jumin_1.val(), $jumin_2.val(), $no.val());
		console.log('is dupli:',isDuplJumin);
		if(isDuplJumin){
			alert('존재하는 사원 정보입니다.');
			return false;
		}
		if($dept.val() === ''){
			alert('부서를 입력해주세요');
			return false;
		}
		if(endDate === ''){
			alert('졸업일을 입력해주세요');
			return false;
		}
		if(!$skill.is(':checked')){
			alert('기술을 하나 이상 선택해주세요');
			return false;
		}
		if(!$edu.is(':checked')){
			alert('학력을 선택해주세요');
			return false;
		}
		var isConfirm = confirm('정말로 저장하시겠습니까?');
		
		var addForm = new Object();
		addForm.no = $no.val();
		addForm.name = $name.val();
		addForm.juminFirst = $jumin_1.val();
		addForm.juminSecond = $jumin_2.val();
		addForm.departmentCode = $dept.val();
		addForm.education = $edu.val();
		
		var skillCodeArr = new Array();
		for(var i = 0; i < $skill.length; i++){
			var skillCheckbox = $('#updel-form input[name=skillCode]')[i];
			if($(skillCheckbox).is(':checked')){
				skillCodeArr.push(Number($(skillCheckbox).val()));
			}
		}
		
		addForm.skillCode = skillCodeArr;
		addForm.endYear = endYear;
		addForm.endMonth = endMonth;
		addForm.endDay = endDay;
	
		if(isConfirm){
			$.ajax({
				type:"POST",
				url:"/update.do",
				contentType: 'application/json',
				data:JSON.stringify(addForm),
				success:function(result){
					console.log(result);
					history.go(0);
				},
				error:function(result){
					console.log(result);
					alert('실패하였습니다.')
				}
			
			})
		}
		
		
	});
	$('#delete-action-btn').click(function(){
		
		var isConfirm = confirm('정말 삭제 하시겠습니까 ?');
		
		if(isConfirm){
			
			var data = new Object();
			data.no = $('#staff-no').val();
			$.ajax({
				type:"POST",
				url:"/delete.do",
				data:JSON.stringify(data),
				contentType: 'application/json',
				success:function(data){
					if(data.isSuccess === 'success'){
						history.go(0);
					}
				},
				error:function(){
					alert('실패하였습니다.')
				}
				
				
			})
			
		}
	})
	
	
	//초기화버튼
	$('#reset-search-btn').click(function(){
		clearBox();
	})
	//전체검색
	$('#allSearch-btn').click(function(){
		var $isAllSearch = $('#isAllSearch');
		$isAllSearch.val('true');
		clearBox();
		$('#search-form').submit();
	})
	$('.page-no').click(function(){
		console.log($(this).data('page'))
		$('#hidden-page-no').val($(this).data('page'));
		console.log('now',$('#hidden-page-no').val())
		$('#search-form').submit();
	});
	$('.page-before').click(function(){
		console.log($(this).data('page'))
		var pageNo = $('#hidden-page-no').val();
		$('#hidden-page-no').val(Number(pageNo) - 1);
		console.log('now',$('#hidden-page-no').val())
		$('#search-form').submit();
	})
	$('.page-after').click(function(){
		console.log($(this).data('page'))
		var pageNo = $('#hidden-page-no').val();
		$('#hidden-page-no').val(Number(pageNo) + 1);
		console.log('now',$('#hidden-page-no').val())
		$('#search-form').submit();
	})
	
	//날짜 확인
	$('#search-form').submit(function(){
		var $pageNo = $('#hidden-page-no');
		
		var startYear = $('#start-year').val();
		var startMonth = $('#start-month').val();
		var startDay = $('#start-day').val();
		var endYear = $('#end-year').val();
		var endMonth = $('#end-month').val();
		var endDay = $('#end-day').val();
		var startDate = changeDate(startYear, startMonth, startDay);
		var endDate = changeDate(endYear, endMonth, endDay);
		var startDate_S = changeDateString(startYear, startMonth, startDay);
		var endDate_S = changeDateString(endYear, endMonth, endDay);
		
		if(startDate === '' && endDate === ''){
			return true;
		} else {
			$('#search-startDate').val(startDate_S);
			$('#search-endDate').val(endDate_S);
		}
		if(startDate !== '' && endDate !== ''){
			
			if(startDate.getTime() >= endDate.getTime()){
				alert('졸업일을 확인해주세요');
				return false;
			}
		}
		
		return true;
	})
	$('#add-form').submit(function(){
		var $name = $('#staff-name');
		var $jumin_1 = $('#jumin-first-box');
		var $jumin_2 = $('#jumin-second-box');
		var $edu = $('#add-form input[name=education]');
		var $dept = $('#add-dept');
		var $skill = $('#add-form input[name=skillCode]');
		
		var startYear = $('#add-start-year').val();
		var startMonth = $('#add-start-month').val();
		var startDay = $('#add-start-day').val();
		var endYear = $('#add-end-year').val();
		var endMonth = $('#add-end-month').val();
		var endDay = $('#add-end-day').val();
		var startDate = changeDate(startYear, startMonth, startDay);
		var endDate = changeDate(endYear, endMonth, endDay);
		
		var regex= /^[0-9]*$/;
		
		if($name.val() === ''){
			alert('이름을 입력해주세요');
			return false;
		}
//		if($jumin_1.val() === '' || $jumin_2.val() === ''){
//			alert('주민번호를 입력해주세요');
//			return false;
//		}
//		if(!regex.test($jumin_1.val()) || !regex.test($jumin_2.val())){
//			alert('주민번호는 숫자만 입력해주세요');
//			return false;
//		}
//		if(!($jumin_1.val().length === 6) || !($jumin_2.val().length === 7)){
//			alert('올바르지 않은 주민 번호입니다.');
//			return false;
//		}
		var availableJumin = ssnCheck($jumin_1.val(), $jumin_2.val())
		if(!availableJumin){
			return false;
		}
		var isDuplJumin = duplicatedJuminCheck($jumin_1.val(), $jumin_2.val(), 0);
		console.log('is',isDuplJumin);
		if(isDuplJumin){
			alert('존재하는 사원 정보입니다.');
			return false;
		}
		if($dept.val() === ''){
			alert('부서를 입력해주세요');
			return false;
		}
		if(startDate === '' || endDate === ''){
			alert('졸업일을 입력해주세요');
			return false;
		}
		if(startDate.getTime() >= endDate.getTime()){
			alert('졸업일을 확인해주세요');
			return false;
		}
		if(!$skill.is(':checked')){
			alert('기술을 하나 이상 선택해주세요');
			return false;
		}
		if(!$edu.is(':checked')){
			alert('학력을 선택해주세요');
			return false;
		}
		var isConfirm = confirm('정말로 저장하시겠습니까?');
		
		// AJAX로 주민번호 중복처리
		
		return isConfirm;
	})
	
	
	function changeDate(year, month, day){
		month = (month < 10) ? '0' + month : month;
		day = (day < 10) ? '0' + day : day;
		if(year === ''){
			return '';
		}
		if(month === ''){
			return '';
		}
		if(day === ''){
			return '';
		}
		return new Date (year + '-' + month + '-' + day);
	}
	function changeDateString(year, month, day){
		month = (month < 10) ? '0' + month : month;
		day = (day < 10) ? '0' + day : day;
		if(year === ''){
			return '';
		}
		if(month === ''){
			return '';
		}
		if(day === ''){
			return '';
		}
		return (year + '-' + month + '-' + day);
	}
	function clearBox(){
		$('#hidden-page-no').val(1);
		$('#name').val('');
		$('#jumin-first-box').val('');
		$('#jumin-second-box').val('');
		$('input[name=gender]').prop('checked',false);
		$('input[name=education]').prop('checked',false);
		$('#dept').val('');
		$('input[name=skill]').prop('checked',false);
		$('#search-startDate').val('');
		$('#search-endDate').val('');
		$('#start-year').val('');
		$('#start-month').val('');
		$('#start-day').val('');
		$('#end-year').val('');
		$('#end-month').val('');
		$('#end-day').val('');
		
		$('#keyword-type-or').prop('checked',false);
		$('#keyword-type-and').prop('checked',false);
		$('#keyword-box').val('');
		
		$('#no-arrow-val').val('up');
		$('#name-arrow-val').val('up');
		$('#gender-arrow-val').val('up');
		$('#dept-arrow-val').val('up');
		$('#graduate-arrow-val').val('up');
		
	}
	function ssnCheck(_ssn1, _ssn2)
	{
	    var ssn1    = _ssn1,
	        ssn2    = _ssn2,
	        ssn     = ssn1+''+ssn2,
	        arr_ssn = [],
	        compare = [2,3,4,5,6,7,8,9,2,3,4,5],
	        sum     = 0;
	 
	    // 입력여부 체크
	    if (ssn1 == '')
	    {
	        alert('주민등록번호를 기입해주세요.');
	        return false;
	    }
	 
	    if (ssn2 == '')
	    {
	        alert('주민등록번호를 기입해주세요.');
	        return false;
	    }    
	 
	    // 입력값 체크
	    if (ssn1.match('[^0-9]'))
	    {
	        alert("주민등록번호는 숫자만 입력하셔야 합니다."); 
	        return false; 
	    }
	    if (ssn2.match('[^0-9]'))
	    {
	        alert("주민등록번호는 숫자만 입력하셔야 합니다."); 
	        return false; 
	    }
	 
	    // 자리수 체크
	    if (ssn.length != 13)
	    {
	        alert("올바른 주민등록 번호를 입력하여 주세요.");return false;
	    }    
	 
	 
	    // 공식: M = (11 - ((2×A + 3×B + 4×C + 5×D + 6×E + 7×F + 8×G + 9×H + 2×I + 3×J + 4×K + 5×L) % 11)) % 11
	    for (var i = 0; i<13; i++) 
	    { 
	        arr_ssn[i] = ssn.substring(i,i+1); 
	    }
	     
	    for (var i = 0; i<12; i++)
	    {
	        sum = sum + (arr_ssn[i] * compare[i]); 
	    }
	 
	    sum = (11 - (sum % 11)) % 10;
	     
	    if (sum != arr_ssn[12])
	    { 
	        alert("올바른 주민등록 번호를 입력하여 주세요.");
	        return false; 
	    }
	 
	    return true;
	}
	function duplicatedJuminCheck(first, second, no){
		var isDuplicated = false;
		var data = new Object();
		data.staffNo = Number(no);
		data.jumin = first + "-" + second;
		$.ajax({
			type:'POST',
			url:'/getjumin.do',
			contentType: 'application/json',
			async: false,
			data:JSON.stringify(data),
			success:function(result){
				isDuplicated = result.isDuplicated;
			}
			
		})
		return isDuplicated;
	}
	
	
})