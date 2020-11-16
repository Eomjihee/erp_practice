package kr.or.erp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Staff {
	
	private int rowNumber;
	private int no;
	private String name;
	private String juminNo;
	private String gender;
	private CodeSchool school;
	private CodeDepartment department;
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")
	private Date graduateDay;

	public Staff() {}

	public int getRowNumber() {
		return rowNumber;
	}


	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getGender() {
		return gender;
	}
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

	public String getJuminNo() {
		return juminNo;
	}

	public void setJuminNo(String juminNo) {
		if("1".equals(juminNo.substring(7,8))) {
			this.gender = "남";
		} else if ("3".equals(juminNo.substring(7,8))) {
			this.gender = "남";
		} else {
			this.gender = "여";
		}
		this.juminNo = juminNo;
	}

	public CodeSchool getSchool() {
		return school;
	}

	public void setSchool(CodeSchool school) {
		this.school = school;
	}

	public CodeDepartment getDepartment() {
		return department;
	}

	public void setDepartment(CodeDepartment department) {
		this.department = department;
	}

	public Date getGraduateDay() {
		return graduateDay;
	}

	public void setGraduateDay(Date graduateDay) {
		this.graduateDay = graduateDay;
	}

	@Override
	public String toString() {
		return "Staff [rowNumber=" + rowNumber + ", no=" + no + ", name=" + name + ", juminNo=" + juminNo + ", gender="
				+ gender + ", school=" + school + ", department=" + department + ", graduateDay=" + graduateDay + "]";
	}

	

}
