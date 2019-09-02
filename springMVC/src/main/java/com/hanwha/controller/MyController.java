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

	// controller에서 DAO로 직접가는 것
	@Autowired
	DeptDAO_Mybatis dao;
	// DeptDAO dao;

	// controller에서 service를 거쳐서 dao로 가는것
	@Autowired
	EmpService service;

	
	
	//error page
	@RequestMapping("/404")
	public String error404(Model model) {
		model.addAttribute("company", "한화ICT");
		model.addAttribute("manager", "송뿌리");
		return "error404";
	}
	
	@ExceptionHandler(Exception.class)
	public String error500(Exception ex, Model model) {
		model.addAttribute("company", "한화ICT");
		model.addAttribute("manager", "송뿌리");
		model.addAttribute("phone", "010-1111-1111");
		model.addAttribute("errormessage2", ex.getMessage());
		return "error500";
	}
	
	
	// 직원목록 전체 보기
	@RequestMapping("/emp/emplist")
	public ModelAndView selectAll() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emplist", service.selectAll());
		mv.setViewName("emp/empAll");
		return mv;
	}

	// 직원상세보기
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

	// 직원수정하기
	@RequestMapping(value = "/emp/empdetail", method = RequestMethod.POST)
	public String empUpdate(EmpVO emp) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emp", service.updateEmp(emp));
		return "redirect:emplist";
	}

	// 직원추가하기 폼보여주기
	@RequestMapping(value = "/emp/empinsert", method = RequestMethod.GET)
	public ModelAndView empInsert() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("deptlist", dao.selectAll());
		mv.addObject("joblist", service.selectAllJob());
		mv.addObject("managerlist", service.selectAllManager());
		mv.setViewName("emp/empinsert");
		return mv;
	}

	// 직원추가하기
	@RequestMapping(value = "/emp/empinsert", method = RequestMethod.POST)
	public String empInsert(EmpVO emp) {
		service.insertEmp(emp);
		return "redirect:emplist";
	}

	// 직원삭제하기
	@RequestMapping("/emp/empdelete")
	public String empDelete(int empid) {
		service.deleteEmp(empid);
		return "redirect:emplist";
	}

	// "/dept/deptlist"라고 요청 받았을 때 부서의 리스트를 전부 보여주세요.
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

	@RequestMapping(value="/dept/deptinsert", method=RequestMethod.GET) //폼만 보여주면 됨 ->page만 return 
	public String deptInsertGet() { 
		return "dept/deptinsert"; 
	}

	//이미지 업로드
	@RequestMapping(value = "/dept/deptinsert", method = RequestMethod.POST)
	public String deptInsertPost(DeptDTO dept, HttpServletRequest request) {
		//이미지의 바이너리가 온다.
		 MultipartFile uploadfile = dept.getUploadfile();         
		 if (uploadfile == null) 
			 return "redirect:deptinsert";     
		 
		 String path = request.getSession().getServletContext().getRealPath("/resources");         
		 System.out.println("웹서버의 실제경로 : " + path);
		 String fileName = uploadfile.getOriginalFilename(); 
		 dept.setFileName(fileName);         //파일의 이름을 저장한다.
		 
		 String fpath = path +"\\" + fileName ;                
		 try {                                                   
			 //File 사용                 
			 File file = new File(fpath);                 
			 uploadfile.transferTo(file);          
		} catch (IOException e) {     e.printStackTrace();     } 
		 
		dao.insertDept(dept);
		
		return "redirect:deptlist2";
	}

	//이미지 다운로드
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