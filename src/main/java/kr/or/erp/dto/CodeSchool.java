package kr.or.erp.dto;

public class CodeSchool {

	private int code;
	private String name;
	
	public CodeSchool() {}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CodeSchool [code=" + code + ", name=" + name + "]";
	}
	
	
	
}
