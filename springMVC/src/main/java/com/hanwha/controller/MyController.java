package com.hanwha.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hanwha.model.DeptDAO_Mybatis;
import com.hanwha.model.DeptDTO;
import com.hanwha.model.EmpService;
import com.hanwha.model.EmpVO;

@Controller
public class MyController {

	// controller���� DAO�� �������� ��
	@Autowired
	DeptDAO_Mybatis dao;
	// DeptDAO dao;

	// controller���� service�� ���ļ� dao�� ���°�
	@Autowired
	EmpService service;

	
	
	//error page
	@RequestMapping("/404")
	public String error404(Model model) {
		model.addAttribute("company", "��ȭICT");
		model.addAttribute("manager", "�ۻѸ�");
		return "error404";
	}
	
	@ExceptionHandler(Exception.class)
	public String error500(Exception ex, Model model) {
		model.addAttribute("company", "��ȭICT");
		model.addAttribute("manager", "�ۻѸ�");
		model.addAttribute("phone", "010-1111-1111");
		model.addAttribute("errormessage2", ex.getMessage());
		return "error500";
	}
	
	
	// ������� ��ü ����
	@RequestMapping("/emp/emplist")
	public ModelAndView selectAll() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emplist", service.selectAll());
		mv.setViewName("emp/empAll");
		return mv;
	}

	// �����󼼺���
	@RequestMapping(value = "/emp/empdetail", method = RequestMethod.GET)
	public ModelAndView empDetail(Integer empid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emp", service.selectById(empid));
		mv.addObject("managerlist", service.selectAllManager());
		mv.addObject("joblist", service.selectAllJob());
		mv.addObject("deptlist", dao.selectAll());
		mv.setViewName("emp/empdetail");
		return mv;
	}

	// ���������ϱ�
	@RequestMapping(value = "/emp/empdetail", method = RequestMethod.POST)
	public String empUpdate(EmpVO emp) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emp", service.updateEmp(emp));
		return "redirect:emplist";
	}

	// �����߰��ϱ� �������ֱ�
	@RequestMapping(value = "/emp/empinsert", method = RequestMethod.GET)
	public ModelAndView empInsert() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("deptlist", dao.selectAll());
		mv.addObject("joblist", service.selectAllJob());
		mv.addObject("managerlist", service.selectAllManager());
		mv.setViewName("emp/empinsert");
		return mv;
	}

	// �����߰��ϱ�
	@RequestMapping(value = "/emp/empinsert", method = RequestMethod.POST)
	public String empInsert(EmpVO emp) {
		service.insertEmp(emp);
		return "redirect:emplist";
	}

	// ���������ϱ�
	@RequestMapping("/emp/empdelete")
	public String empDelete(int empid) {
		service.deleteEmp(empid);
		return "redirect:emplist";
	}

	// "/dept/deptlist"��� ��û �޾��� �� �μ��� ����Ʈ�� ���� �����ּ���.
	@RequestMapping("/dept/deptlist2")
	public String deptlist(Model model) {
		model.addAttribute("deptlist", dao.selectAll());
		return "dept/deptlist";
	}

	@RequestMapping(value = "/dept/deptdetail", method = RequestMethod.GET)
	public String deptDetail(Integer deptid, Model model) {
		model.addAttribute("dept", dao.selectById(deptid));
		return "dept/deptdetail";
	}

	
	@RequestMapping(value = "/dept/deptdetail", method = RequestMethod.POST)
	public String deptDetailPost(DeptDTO dept) {
		dao.updateDept(dept);
		return "redirect:deptlist2";
	}

	@RequestMapping(value="/dept/deptinsert", method=RequestMethod.GET) //���� �����ָ� �� ->page�� return 
	public String deptInsertGet() { 
		return "dept/deptinsert"; 
	}

	//�̹��� ���ε�
	@RequestMapping(value = "/dept/deptinsert", method = RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept, HttpServletRequest request) {
		//�̹����� ���̳ʸ��� �´�.
		 MultipartFile uploadfile = dept.getUploadfile();         
		 if (uploadfile == null) 
			 return "redirect:deptinsert";     
		 
		 String path = request.getSession().getServletContext().getRealPath("/resources");         
		 System.out.println("�������� ������� : " + path);
		 String fileName = uploadfile.getOriginalFilename(); 
		 dept.setFileName(fileName);         //������ �̸��� �����Ѵ�.
		 
		 String fpath = path +"\\" + fileName ;                
		 try {                                                   
			 //File ���                 
			 File file = new File(fpath);                 
			 uploadfile.transferTo(file);          
		} catch (IOException e) {     e.printStackTrace();     } 
		 
		dao.insertDept(dept);
		
		return "redirect:deptlist2";
	}

	//�̹��� �ٿ�ε�
	@RequestMapping("/dept/deptdownload")
	public void download(String folder, String file, 
			HttpServletRequest request,
			HttpServletResponse response) throws IOException   {
		response.setHeader("Content-Disposition", "attachment;filename=" + file);
		String fullPath = request.getSession().getServletContext().getRealPath(folder + "/" + file);
		FileInputStream fi = new FileInputStream(fullPath);
		ServletOutputStream sout = response.getOutputStream();
		byte[] buf = new byte[1024];
		int size = 0;
		while ((size = fi.read(buf, 0, 1024)) != -1) {
			sout.write(buf, 0, size);
		}
		fi.close();
		sout.close();
	}
	
	@RequestMapping("/dept/deptdelete")
	public String deptDelete(Integer deptid) {
		dao.deleteDept(deptid);
		return "redirect:deptlist2";
	}  
	 
}

// <bean id="myController" class=""/>
// DeptDAO dao = new DeptDAO();
// <bean id="dao" class="com.hanwha.model.DeptDAO"/>
// <bean id="myController" class="">
// <property name="dao" ref="dao">
// </bean>