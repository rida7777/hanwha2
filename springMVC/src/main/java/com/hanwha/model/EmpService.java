package com.hanwha.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//DAO�� ���� ���� �˸� ��
public class EmpService {

	@Autowired
	EmpDAO_Mybatis dao;
	//EmpDAO dao;
	
	// employees���� manager�� ���� ��� manager id�� �����ض�
	public List<Integer> selectAllManager() {
		//dao���� �������� �ٷ� controller���� �ѱ�� ��
		return dao.selectAllManager();
	}

	// job�� ���� ��� job id�� �����ض�
	public List<String> selectAllJob() {
		return dao.selectAllJob();

	}


	// 2. ���� ���� ��ȸ
	public List<EmpVO> selectAll() {
		return dao.selectAll();
	}

	// 4.Ư�� ���� ��ȸ
	public EmpVO selectById(int empid) {
		return dao.selectById(empid);
	}

	// 10.�����Է�
	public int insertEmp(EmpVO emp) {
		return dao.insertEmp(emp);
	}

	// 11.��������
	public int updateEmp(EmpVO emp) {
		return dao.updateEmp(emp);
	}

	// 12.��������
	public int deleteEmp(int emp) { // �μ� �ڵ常 ���� ���̱� ������
		return dao.deleteEmp(emp);
	}	
}
