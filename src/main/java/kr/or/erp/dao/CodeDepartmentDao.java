package kr.or.erp.dao;

import java.util.List;

import kr.or.erp.dto.CodeDepartment;

public interface CodeDepartmentDao {

	List<CodeDepartment> getAllCodeDepartment();
	CodeDepartment getDepartmentByCode(int code);
}
