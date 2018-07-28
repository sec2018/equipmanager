//package com.jeesite.modules.test.web.localController;
//
//
//import com.jeesite.common.entity.Page;
//import com.jeesite.modules.test.entity.localentity.GradeandSubjects;
//import com.jeesite.modules.test.service.localService.GradeandSubjectsService;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
//@Controller
//@RequestMapping(value = "${adminPath}/test/gradeSubjRelate")
//public class GradeandSubjectsController {
//
//    @Autowired
//    private GradeandSubjectsService gradeandSubjectsService;
//
//
//    /**
//     * 获取数据
//     */
////    @ModelAttribute
////    public GradeandSubjects get(String id, boolean isNewRecord) {
////        return gradeandSubjectsService.get(id, isNewRecord);
////    }
//
//    /**
//     * 查询列表
//     */
//    @RequiresPermissions("test:gradeSubjRelate:view")
//    @RequestMapping(value = {"list", ""})
//    public String list(GradeandSubjects gradeandSubjects, Model model) {
//        model.addAttribute("gradeSubjRelate", gradeandSubjects);
//        return "modules/test/gradeSubjRelateList";
//    }
//
//    @RequiresPermissions("test:gradeSubjRelate:view")
//    @RequestMapping(value = "listData")
//    @ResponseBody
//    public Page<GradeandSubjects> listData(GradeandSubjects gradeandSubjects, HttpServletRequest request, HttpServletResponse response) {
//        Page<GradeandSubjects> page = gradeandSubjectsService.findSubsByGradeId(new Page<GradeandSubjects>(request,response),gradeandSubjects);
//        return page;
//    }
//
//    /**
//     * 查看编辑表单
//     */
//	@RequiresPermissions("test:gradeSubjRelate:view")
//	@RequestMapping(value = "form")
//	public String form(GradeandSubjects gradeandSubjects, Model model) {
//		model.addAttribute("gradeSubjRelate", gradeandSubjects);
//		return "modules/test/gradeSubjRelateForm";
//	}
//
//
//
//
//}
