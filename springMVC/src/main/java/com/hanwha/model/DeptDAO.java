package com.hanwha.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hanwha.util.DBUtil_Oracle;

/*
 DAO(Data Access Object) : Business logic > db에 접근하는 로직
 */

//<bean id="deptDAO" class="com.hanwha.model.DeptDAO"/>

@Repository
public class DeptDAO {
	
	@Autowired
	DataSource ds;
	
	//1.부서 모두 조회
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist = new ArrayList<>();
		Connection conn = null;
		
		Statement st = null; 							//나중에 닫을 때 st변수에 접근 할 수 있어야 하기 때문에 try/catch 밖에다가 선언
		ResultSet rs = null;
		String sql =  "select * from departments";
		try {
			conn=ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int deptid = rs.getInt("department_id");
				String dname = rs.getString("department_name");
				DeptDTO dept = new DeptDTO(deptid, dname);
				deptlist.add(dept);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil_Oracle.dbClose(rs, st, conn);
		}
		
		return deptlist;
	}

	//3.특정 부서 조회
	public DeptDTO selectById(int deptid) {
		DeptDTO dept = null;
		Connection conn = null;
		Statement st = null; 							//나중에 닫을 때 st변수에 접근 할 수 있어야 하기 때문에 try/catch 밖에다가 선언
		ResultSet rs = null;
		String sql =  "select * from departments where department_id = " + deptid;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				String dname = rs.getString("department_name");
				dept = new DeptDTO(deptid, dname);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil_Oracle.dbClose(rs, st, conn);
		}
		
		return dept;
	}

	// 7.부서입력
	public int insertDept(DeptDTO dept) {
		Connection conn = null; //db연결
		PreparedStatement st = null; 
		int result = 0;
		String sql = "insert into departments (department_id, department_name) values(?,?)";
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, dept.getDepartment_id());
			st.setString(2, dept.getDepartment_name());
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//8.부서수정
	public int updateDept(DeptDTO dept) {
		Connection conn = null; //db연결
		PreparedStatement st = null; 
		int result = 0;
		String sql = "update departments set department_name=? where department_id =?";
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(2, dept.getDepartment_id());
			st.setString(1, dept.getDepartment_name());
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//9.부서삭제
	public int deleteDept(int dept) {	//부서 코드만 받을 것이기 때문에
		Connection conn = null; //db연결
		PreparedStatement st = null; 
		int result = 0;
		String sql = "delete from departments where department_id =?";
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, dept);
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
