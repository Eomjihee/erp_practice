package kr.or.erp.form;

import java.util.Arrays;

public class AddForm {

	private int no;
	private String name;
	private int juminFirst;
	private int juminSecond;
	private int departmentCode;
	private int education;
	private int[] skillCode;
	private String startYear;
	private String startMonth;
	private String startDay;
	private String endYear;
	private String endMonth;
	private String endDay;
	
	public AddForm() {}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getJuminFirst() {
		return juminFirst;
	}

	public void setJuminFirst(int juminFirst) {
		this.juminFirst = juminFirst;
	}

	public int getJuminSecond() {
		return juminSecond;
	}

	public void setJuminSecond(int juminSecond) {
		this.juminSecond = juminSecond;
	}

	public int getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(int departmentCode) {
		this.departmentCode = departmentCode;
	}

	public int getEducation() {
		return education;
	}

	public void setEducation(int education) {
		this.education = education;
	}

	public int[] getSkillCode() {
		return skillCode;
	}

	public void setSkillCode(int[] skillCode) {
		this.skillCode = skillCode;
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

	@Override
	public String toString() {
		return "AddForm [no=" + no + ", name=" + name + ", juminFirst=" + juminFirst + ", juminSecond=" + juminSecond
				+ ", departmentCode=" + departmentCode + ", education=" + education + ", skillCode="
				+ Arrays.toString(skillCode) + ", startYear=" + startYear + ", startMonth=" + startMonth + ", startDay="
				+ startDay + ", endYear=" + endYear + ", endMonth=" + endMonth + ", endDay=" + endDay + "]";
	}

	
	
}
