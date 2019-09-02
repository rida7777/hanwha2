package com.hanwha.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//DAO에 들어가는 길을 알면 됨
public class EmpService {

	@Autowired
	EmpDAO_Mybatis dao;
	//EmpDAO dao;
	
	// employees에서 manager를 전부 골라서 manager id를 저장해라
	public List<Integer> selectAllManager() {
		//dao에게 받은것은 바로 controller에게 넘기면 됨
		return dao.selectAllManager();
	}

	// job을 전부 골라서 job id를 저장해라
	public List<String> selectAllJob() {
		return dao.selectAllJob();

	}


	// 2. 직원 전부 조회
	public List<EmpVO> selectAll() {
		return dao.selectAll();
	}

	// 4.특정 직원 조회
	public EmpVO selectById(int empid) {
		return dao.selectById(empid);
	}

	// 10.직원입력
	public int insertEmp(EmpVO emp) {
		return dao.insertEmp(emp);
	}

	// 11.직원수정
	public int updateEmp(EmpVO emp) {
		return dao.updateEmp(emp);
	}

	// 12.직원삭제
	public int deleteEmp(int emp) { // 부서 코드만 받을 것이기 때문에
		return dao.deleteEmp(emp);
	}	
}
