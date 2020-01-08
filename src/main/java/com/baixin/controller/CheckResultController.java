package com.baixin.controller;

import com.baixin.common.PageInfo;
import com.baixin.model.*;
import com.baixin.service.CheckResultService;
import com.baixin.service.OperateLogService;
import com.baixin.util.Constant;
import com.baixin.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 报告单管理
 */
@Controller
public class CheckResultController {
    @Autowired
    private CheckResultService checkResultService;
    
    public static final String ROOT = "src/main/resources/static/img/item/";
    
    private final ResourceLoader resourceLoader;
    
    @Autowired
    public CheckResultController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    @GetMapping("/user/checkResultEdit")
    @Transactional
    public String itemEditGet(Model model, CheckResult checkResult) {
        if(checkResult.getId() != 0) {
            CheckResult checkResult2 = checkResultService.findById(checkResult);
            model.addAttribute("checkResult", checkResult2);
        }
        return "checks/checkResultEdit";
    }
    
    @PostMapping("/user/checkResultEdit")
    public String checkResultEditPost(Model model, HttpServletRequest request, CheckResult checkResult, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        checkResult.setCreateUserId(user.getId());
        checkResultService.saveCheckResult(checkResult);
        return "redirect:checkResultPage_0_0_0";
    }
    
    @RequestMapping("/user/checkResultPage_{pageCurrent}_{pageSize}_{pageCount}")
    public String listCheckResultPage_(CheckResult checkResult, @PathVariable Integer pageCurrent,
                                     @PathVariable Integer pageSize, @PathVariable Integer pageCount, Model model) {
        if(pageCurrent == 0) {
            pageCurrent = 1;
        }
        pageSize = PageInfo.pageSize;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", checkResult.getPatientName());
        paramMap.put("pageNum", pageCurrent);
        paramMap.put("pageSize", pageSize);
        PageInfo page = checkResultService.listPage(paramMap);
        pageCount = (page.getTotalSize() / pageSize) + 1;
        model.addAttribute("checkResultPageList", page.getContentList2());
        String pageHTML =
                PageUtil.getPageContent("checkResultPage_{pageCurrent}_{pageSize}_{pageCount}?itemId=" + checkResult.getPatientName(), pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("checkResult", checkResult);
        return "checks/checkResultPage";
    }
    
    @ResponseBody
    @PostMapping("/user/checkResultDelete")
    @Transactional
    public ResObject<Object> checkResultDelete(CheckResult checkResult, HttpSession httpSession) {
        checkResultService.checkResultDelete(checkResult);
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }
    
    @ResponseBody
    @PostMapping("/user/detailCheckReports")
    @Transactional
    public String detailCheckReports(@RequestParam("checkResultId") int checkResultId,@RequestParam("files") MultipartFile[] files,
                                     HttpSession httpSession) {
        checkResultService.detailCheckReports(checkResultId,files);
        return "redirect:checkResultPage_0_0_0";
    }
    
}
