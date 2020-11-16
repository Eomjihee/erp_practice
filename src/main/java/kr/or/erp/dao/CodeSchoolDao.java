package kr.or.erp.dao;

import java.util.List;

import kr.or.erp.dto.CodeSchool;

public interface CodeSchoolDao {
	List<CodeSchool> getAllCodeSchoolDao();
	CodeSchool getSchoolByCode(int code);
	
}
