package com.hanwha.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAO_Mybatis {
	
	@Autowired
	SqlSession session;
	String namespace="com.hanwha.emp.";
	
	public List<Integer> selectAllManager() {
		return session.selectList(namespace+"selectallmanager");
	}

	// job�� ���� ��� job id�� �����ض�
	public List<String> selectAllJob() {
		return session.selectList(namespace+"selectalljob");
	}


	// 2. ���� ���� ��ȸ
	public List<EmpVO> selectAll() {
		return session.selectList(namespace+"selectall");
	}

	// 4.Ư�� ���� ��ȸ
	public EmpVO selectById(int empid) {
		return session.selectOne(namespace+"selectbyid", empid);
	}

	// 10.�����Է�
	public int insertEmp(EmpVO emp) {
		return session.insert(namespace+"insert", emp);
	}

	// 11.��������
	public int updateEmp(EmpVO emp) {
		return session.update(namespace+"update", emp);
	}

	// 12.��������
	public int deleteEmp(int emp) { // �μ� �ڵ常 ���� ���̱� ������
		return session.delete(namespace+"delete", emp);
	}	
}
