package kr.or.erp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.erp.dao.CodeDepartmentDao;
import kr.or.erp.dao.CodeSchoolDao;
import kr.or.erp.dao.CodeSkillDao;
import kr.or.erp.dto.CodeDepartment;
import kr.or.erp.dto.CodeSchool;
import kr.or.erp.dto.CodeSkill;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private CodeDepartmentDao codeDepartmentDao;
	
	@Autowired
	private CodeSchoolDao codeSchoolDao;
	
	@Autowired
	private CodeSkillDao codeSkillDao;
	
	
	
	public List<CodeDepartment> codeDepartmentList(Map<String, Object> param) {
		String query = (String) param.get("query");
		if("getAllCodeDepartment".equals(query)) {
			return codeDepartmentDao.getAllCodeDepartment();
		}
		
		return codeDepartmentDao.getAllCodeDepartment();
	}
	
	public List<CodeSchool> codeSchoolList(Map<String, Object> param) {
		String query = (String) param.get("query");
		
		if("getAllCodeSchool".equals(query)) {
			return codeSchoolDao.getAllCodeSchoolDao();
		}
		
		return codeSchoolDao.getAllCodeSchoolDao();
	}

	public List<CodeSkill> codeSkillList(Map<String, Object> param) {
		String query = (String) param.get("query");
		if("getAllCodeSkill".equals(query)) {
			return codeSkillDao.getAllCodeSkill();
		}
		
		return codeSkillDao.getAllCodeSkill();
	}
	
	
	
}
