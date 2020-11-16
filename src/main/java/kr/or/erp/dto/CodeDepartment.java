package kr.or.erp.dto;

public class CodeDepartment {

	private int code;
	private String name;
	
	public CodeDepartment() {}

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
		return "CodeDepartment [code=" + code + ", name=" + name + "]";
	}
	
	
	
}
