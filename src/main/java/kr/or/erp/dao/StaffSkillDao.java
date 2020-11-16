package kr.or.erp.dao;

import java.util.List;

import kr.or.erp.dto.StaffSkill;

public interface StaffSkillDao {

	void add(StaffSkill staffSkill);
	List<StaffSkill> getSkillByStaffNo(int staffNo);
	void deleteByStaffNo(int staffNo);
}
