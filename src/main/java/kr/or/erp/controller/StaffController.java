package kr.or.erp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.erp.dto.CodeDepartment;
import kr.or.erp.dto.CodeSchool;
import kr.or.erp.dto.CodeSkill;
import kr.or.erp.dto.Pagination;
import kr.or.erp.dto.Staff;
import kr.or.erp.form.AddForm;
import kr.or.erp.form.SearchForm;
import kr.or.erp.service.CodeService;
import kr.or.erp.service.StaffService;

@Controller
public class StaffController {
	@org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
	public String runtimeExceptionHandler(RuntimeException e) {
		e.printStackTrace();
		return "error/server-error";
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public String ExceptionHandler(Exception e) {
		e.printStackTrace();
		return "error/server-error";
	}	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private StaffService staffService;
	@RequestMapping("/main.do")
	public String main(Model model) {
		
		SearchForm searchForm = new SearchForm();
		System.out.println(searchForm);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("query", "getAllCodeSchool");
		List<CodeSchool> schoolList = codeService.codeSchoolList(param);
		param.put("query", "getAllCodeSkill");
		List<CodeSkill> skillList = codeService.codeSkillList(param);
		param.put("query", "getAllCodeDepartment");
		List<CodeDepartment> deptList = codeService.codeDepartmentList(param);
		
		model.addAttribute("schoolList", schoolList);
		model.addAttribute("skillList", skillList);
		model.addAttribute("deptList", deptList);
		model.addAttribute("todayDate", new Date());
		model.addAttribute("searchForm", searchForm);
		
		return "staff/staff_search_form";
	}
	@RequestMapping(value ="/search.do" , method = RequestMethod.GET)
	public String searchGet() {
		return "redirect:main.do";
	}
	@PostMapping("/search.do")
	public String searchPost(@ModelAttribute("searchForm") SearchForm searchForm, Model model) {
	
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchForm", searchForm);
		
		param.put("query", "getAllCodeSchool");
		List<CodeSchool> schoolList = codeService.codeSchoolList(param);
		param.put("query", "getAllCodeSkill");
		List<CodeSkill> skillList = codeService.codeSkillList(param);
		param.put("query", "getAllCodeDepartment");
		List<CodeDepartment> deptList = codeService.codeDepartmentList(param);
		param.put("pageNo", ((searchForm.getPageNo() == 0) ? 1 : searchForm.getPageNo()));
		
		List<Staff> staffList = (List<Staff>) staffService.list(param).get("staffList");
		Pagination pagination = (Pagination) staffService.list(param).get("pagination");
		
		model.addAttribute("schoolList", schoolList);
		model.addAttribute("skillList", skillList);
		model.addAttribute("deptList", deptList);
		model.addAttribute("todayDate", new Date());
		model.addAttribute("searchForm", searchForm);
		model.addAttribute("staffList", staffList);
		model.addAttribute("pagination", pagination);
		model.addAttribute("totalRows", pagination.getTotalRows());
		System.out.println(model.getAttribute(searchForm.getIsAllSearch()));
		return "staff/staff_search_form";
	}
	@PostMapping("/add.do")
	public String add(AddForm addForm) {
		System.out.println(addForm);
		staffService.addStaff(addForm);
		return "redirect:/main.do";
	}
	@PostMapping("/get.do")
	@ResponseBody
	public Map<String, Object> get(@RequestBody Map<String, Object> param) {
		
		int staffNo = (Integer) param.get("staffNo");
		Map<String, Object> resultMap = staffService.getStaff(staffNo);
		
		return resultMap;
	}
	@PostMapping("/update.do")
	@ResponseBody
	public Map<String, Object> update(@RequestBody AddForm addForm) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		staffService.updateStaff(addForm);
		System.out.println(addForm);
		
		resultMap.put("isSuccess", "success");
		resultMap.put("msg", "수정 되었습니다.");
		return resultMap;
	}
	@PostMapping("/delete.do")
	@ResponseBody
	public Map<String, Object> delete(@RequestBody Map<String, Object> param) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(param);
		int staffNo = Integer.parseInt((String)param.get("no"));
		staffService.deleteStaff(staffNo);
		resultMap.put("isSuccess", "success");
		resultMap.put("msg", "삭제 되었습니다.");
		
		return resultMap;
	}
	@PostMapping("/getjumin.do")
	@ResponseBody
	public Map<String, Object> getJumin(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(map);
		String jumin = (String) map.get("jumin");
		int staffNo = (Integer) map.get("staffNo");
		boolean isDuplicated = staffService.isDuplicatedJumin(jumin, staffNo);
		System.out.println(isDuplicated); 
		resultMap.put("isDuplicated", isDuplicated);
		return resultMap;
	}
	@GetMapping("/ex")
	@ResponseBody
	public String ex() {
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("a","ex");
		
		int a = staffService.total();
		String b = Integer.toString(a);
		
		return b;
		
	}
}
