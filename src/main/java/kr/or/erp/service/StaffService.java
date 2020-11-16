package kr.or.erp.service;

import java.util.Map;

import kr.or.erp.form.AddForm;

public interface StaffService {

	void addStaff(AddForm addForm);
	Map<String, Object> list(Map<String, Object> param);
	Map<String, Object> getStaff(int staffNo);
	void updateStaff(AddForm addForm);
	void deleteStaff(int staffNo);
	boolean isDuplicatedJumin(String jumin, int staffNo);
}
