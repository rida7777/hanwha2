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

	// job을 전부 골라서 job id를 저장해라
	public List<String> selectAllJob() {
		return session.selectList(namespace+"selectalljob");
	}


	// 2. 직원 전부 조회
	public List<EmpVO> selectAll() {
		return session.selectList(namespace+"selectall");
	}

	// 4.특정 직원 조회
	public EmpVO selectById(int empid) {
		return session.selectOne(namespace+"selectbyid", empid);
	}

	// 10.직원입력
	public int insertEmp(EmpVO emp) {
		return session.insert(namespace+"insert", emp);
	}

	// 11.직원수정
	public int updateEmp(EmpVO emp) {
		return session.update(namespace+"update", emp);
	}

	// 12.직원삭제
	public int deleteEmp(int emp) { // 부서 코드만 받을 것이기 때문에
		return session.delete(namespace+"delete", emp);
	}	
}
