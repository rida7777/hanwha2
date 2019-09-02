package com.hanwha.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeptDAO_Mybatis {

	@Autowired
	SqlSession session;
	
	public List<DeptDTO> selectAll() {
		return session.selectList("com.hanwha.dept.selectall");
	}
	
	public DeptDTO selectById(int deptid) {
		return session.selectOne("com.hanwha.dept.selectbyid", deptid);
	}
	
	public int insertDept(DeptDTO dept) {
		return session.insert("com.hanwha.dept.insert", dept);
	}
	
	public int updateDept(DeptDTO dept) {
		return session.update("com.hanwha.dept.update", dept);
	}
	
	public int deleteDept(int dept) {
		return session.delete("com.hanwha.dept.delete", dept);
	}
}
