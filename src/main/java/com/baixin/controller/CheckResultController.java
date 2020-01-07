package com.baixin.controller;

import com.baixin.common.PageInfo;
import com.baixin.model.CheckResult;
import com.baixin.service.CheckResultService;
import com.baixin.service.OperateLogService;
import com.baixin.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
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
}
