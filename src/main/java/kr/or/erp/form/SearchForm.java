package kr.or.erp.form;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchForm {

	private String name;
	private List<String> gender;
	private String department;
	private List<String> education;
	private List<String> skill;
	private String startYear;
	private String startMonth;
	private String startDay;
	private String endYear;
	private String endMonth;
	private String endDay;
	private int pageNo;
	private String isAllSearch;
	@JsonFormat(pattern="yyyy-MM-dd")
	private String startDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private String endDate;

	private String keywordType;
	private String keyword;
	
	private String noBtn;
	private String nameBtn;
	private String genderBtn;
	private String deptBtn;
	private String graduateBtn;
	
	public SearchForm() {}

	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	public List<String> getEducation() {
		return education;
	}

	public void setEducation(List<String> education) {
		this.education = education;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getIsAllSearch() {
		return isAllSearch;
	}

	public void setIsAllSearch(String isAllSearch) {
		this.isAllSearch = isAllSearch;
	}

	public List<String> getGender() {
		return gender;
	}

	public void setGender(List<String> gender) {
		this.gender = gender;
	}

	public List<String> getSkill() {
		return skill;
	}

	public void setSkill(List<String> skill) {
		this.skill = skill;
	}

	
	public String getKeywordType() {
		return keywordType;
	}

	public void setKeywordType(String keywordType) {
		this.keywordType = keywordType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	public String getNoBtn() {
		return StringUtils.isEmpty(noBtn) ? "up" : noBtn;
	}

	public void setNoBtn(String noBtn) {
		this.noBtn = noBtn;
	}

	public String getNameBtn() {
		return StringUtils.isEmpty(nameBtn) ? "up" : nameBtn;
	}

	public void setNameBtn(String nameBtn) {
		this.nameBtn = nameBtn;
	}

	public String getGenderBtn() {
		return StringUtils.isEmpty(genderBtn) ? "up" : genderBtn;
	}

	public void setGenderBtn(String genderBtn) {
		this.genderBtn = genderBtn;
	}

	public String getDeptBtn() {
		return StringUtils.isEmpty(deptBtn) ? "up" : deptBtn;
	}

	public void setDeptBtn(String deptBtn) {
		this.deptBtn = deptBtn;
	}

	public String getGraduateBtn() {
		return StringUtils.isEmpty(graduateBtn) ? "up" : graduateBtn;
	}

	public void setGraduateBtn(String graduateBtn) {
		this.graduateBtn = graduateBtn;
	}

	@Override
	public String toString() {
		return "SearchForm [name=" + name + ", gender=" + gender + ", department=" + department + ", education="
				+ education + ", skill=" + skill + ", startYear=" + startYear + ", startMonth=" + startMonth
				+ ", startDay=" + startDay + ", endYear=" + endYear + ", endMonth=" + endMonth + ", endDay=" + endDay
				+ ", pageNo=" + pageNo + ", isAllSearch=" + isAllSearch + ", startDate=" + startDate + ", endDate="
				+ endDate + ", keywordType=" + keywordType + ", keyword=" + keyword + ", noBtn=" + noBtn + ", nameBtn="
				+ nameBtn + ", genderBtn=" + genderBtn + ", deptBtn=" + deptBtn + ", graduateBtn=" + graduateBtn + "]";
	}

	
	

}