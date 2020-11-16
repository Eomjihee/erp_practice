package kr.or.erp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.erp.dao.CodeDepartmentDao;
import kr.or.erp.dao.CodeSchoolDao;
import kr.or.erp.dao.StaffDao;
import kr.or.erp.dao.StaffSkillDao;
import kr.or.erp.dto.CodeDepartment;
import kr.or.erp.dto.CodeSchool;
import kr.or.erp.dto.CodeSkill;
import kr.or.erp.dto.Pagination;
import kr.or.erp.dto.Staff;
import kr.or.erp.dto.StaffSkill;
import kr.or.erp.form.AddForm;


@Service
public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffDao staffDao;

	@Autowired
	private StaffSkillDao skillDao;

	@Autowired
	private CodeDepartmentDao departmentDao;

	@Autowired
	private CodeSchoolDao schoolDao;

	public Map<String, Object> getStaff(int staffNo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Staff savedStaff = staffDao.getStaffByNo(staffNo);
		List<StaffSkill> savedSkills = skillDao.getSkillByStaffNo(staffNo);

		resultMap.put("staffSkillList", savedSkills);
		resultMap.put("staff", savedStaff);
		return resultMap;
	}

	public Map<String, Object> list(Map<String, Object> param){
		Map<String, Object> resultMap = new HashMap<String, Object>();

		int totalRows = staffDao.totalStaffCounts(param);
		int rowsPerPage = 5;
		int pagesPerBlock = 5;
		int pageNo = (Integer) param.get("pageNo");
		Pagination pagination = new Pagination(rowsPerPage, pagesPerBlock, pageNo, totalRows);
		System.out.println("totalcount" + totalRows);
		System.out.println(pagination);
		param.put("pagination", pagination);
		param.put("searchForm", param.get("searchForm"));
		List<Staff> staffList = staffDao.getAllStaff(param);
		for(Staff staff : staffList) {
			CodeDepartment department = departmentDao.getDepartmentByCode(staff.getDepartment().getCode());
			CodeSchool school = schoolDao.getSchoolByCode(staff.getSchool().getCode());
			staff.setDepartment(department);
			staff.setSchool(school);

			System.out.println(staff);
		}
		resultMap.put("pagination", pagination);
		resultMap.put("staffList", staffList);
		return resultMap;
	}


	@Transactional
	public void addStaff(AddForm addForm) {
		if(addForm == null) {
			new RuntimeException("정보가 존재하지 않습니다.");
		}
		Staff staff = this.getStaffByAddForm(addForm);
		//staff insert
		staffDao.add(staff);

		StaffSkill staffSkill = new StaffSkill();
		staffSkill.setStaff(staff); 
		staffSkill.setSkills(this.getStaffSkillArray(addForm.getSkillCode()));

		//staffSkill insert
		skillDao.add(staffSkill);
		System.out.println(staffSkill);
	}
	@Transactional
	public void updateStaff(AddForm addForm) {
		System.out.println("addForm :" + addForm);
		System.out.println(addForm);
		if(addForm == null) {
			new RuntimeException("정보가 존재하지 않습니다.");
		}
		Staff staff = this.getStaffByAddForm(addForm);
		staffDao.updateStaff(staff);
		
		StaffSkill staffSkill = new StaffSkill();
		staffSkill.setStaff(staff); 
		staffSkill.setSkills(this.getStaffSkillArray(addForm.getSkillCode()));
		
		System.out.println("sk :" + staffSkill);
		System.out.println("st :" + staff);
		
		//delete
		skillDao.deleteByStaffNo(staff.getNo());
		//skill insert
		skillDao.add(staffSkill);
		
	}
	
	public void deleteStaff(int staffNo) {
		skillDao.deleteByStaffNo(staffNo);
		staffDao.deleteStaff(staffNo);
	}
	public boolean isDuplicatedJumin(String jumin, int staffNo) {
		Staff savedStaff = staffDao.getJuminByJumin(jumin, staffNo);
		System.out.println(savedStaff);
		if(savedStaff != null) return true;
		
		return false;
	}
	
	private Staff getStaffByAddForm(AddForm addForm) {
		Staff staff = new Staff();
		staff.setNo(addForm.getNo());
		staff.setName(addForm.getName());
		staff.setJuminNo(this.toStringJumin(addForm.getJuminFirst(), addForm.getJuminSecond()));
		staff.setGraduateDay(this.toDateGradudate(addForm.getEndYear(), addForm.getEndMonth(), addForm.getEndDay()));

		CodeSchool school = new CodeSchool();
		school.setCode(addForm.getEducation());

		CodeDepartment department = new CodeDepartment();
		department.setCode(addForm.getDepartmentCode());

		staff.setSchool(school);
		staff.setDepartment(department);
		
		return staff;
	}

	private CodeSkill[] getStaffSkillArray(int[] skillcodes) {
		CodeSkill[] codeSkills = new CodeSkill[skillcodes.length];
		for(int i = 0; i < skillcodes.length; i++) {
			CodeSkill codeSkill = new CodeSkill();
			codeSkill.setCode(skillcodes[i]);
			codeSkills[i] = codeSkill;
		}
		return codeSkills;
	}

	private String toStringJumin(int first, int second) {
		StringBuffer sb = new StringBuffer();
		sb.append(first)
		.append("-")
		.append(second);
		return sb.toString();
	}
	private Date toDateGradudate(String year, String month, String day) {
		StringBuffer sb = new StringBuffer();
		sb.append(year).append("-").append(month).append("-").append(day);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");

		Date date = null;
		try {
			date = df.parse(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException("유효하지 않은 날짜 형식입니다.");
		}

		return date;
	}
}
