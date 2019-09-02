package com.hanwha.model;

import org.springframework.web.multipart.MultipartFile;

/*
 DTO (Data Transfer Object)
 VO (Value Object)
 Java Beans ��� : default������, getter/setter �� �ִ°� 
 */
public class DeptDTO {
	private int department_id;
	private String department_name;
	MultipartFile uploadfile;
	String fileName;
	
	public DeptDTO() {
		
	}
	
	
	public DeptDTO(int department_id, String department_name) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
	}

	
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public MultipartFile getUploadfile() {
		return uploadfile;
	}


	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}


	public int getDepartment_id() {
		return department_id;
	}


	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}


	public String getDepartment_name() {
		return department_name;
	}


	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("�μ�����...DeptDTO [department_id=").append(department_id).append(", dapartment_name=")
				.append(department_name).append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
}
