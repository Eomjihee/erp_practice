package kr.or.erp.service;

import java.util.List;
import java.util.Map;

import kr.or.erp.dto.CodeDepartment;
import kr.or.erp.dto.CodeSchool;
import kr.or.erp.dto.CodeSkill;

public interface CodeService {
	List<CodeDepartment> codeDepartmentList(Map<String, Object> param);
	List<CodeSchool> codeSchoolList(Map<String, Object> param);
	List<CodeSkill> codeSkillList(Map<String, Object> param);
}
