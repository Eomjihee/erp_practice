package kr.or.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import kr.or.erp.dto.Staff;

public interface StaffDao {
	void add(Staff staff);
	List<Staff> getAllStaff(Map<String, Object> param);
	int totalStaffCounts(Map<String, Object> param);
	Staff getStaffByNo(int staffNo);
	void updateStaff(Staff staff);
	void deleteStaff(int staffNo);
	Staff getJuminByJumin(@Param("jumin") String jumin, @Param("no") int staffNo);
}
