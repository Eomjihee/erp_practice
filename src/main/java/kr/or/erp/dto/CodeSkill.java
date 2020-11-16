package kr.or.erp.dto;

public class CodeSkill {

	private int code;
	private String name;
	
	public CodeSkill() {}

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
		return "CodeSkill [code=" + code + ", name=" + name + "]";
	}
	
	
	
}
