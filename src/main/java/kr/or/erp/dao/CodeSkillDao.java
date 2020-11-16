package kr.or.erp.dao;

import java.util.List;

import kr.or.erp.dto.CodeSkill;

public interface CodeSkillDao {

	List<CodeSkill> getAllCodeSkill();
	CodeSkill getSkillByCode(int code);
}
