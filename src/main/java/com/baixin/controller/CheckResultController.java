package com.baixin.controller;

import com.baixin.common.PageInfo;
import com.baixin.model.CheckResult;
import com.baixin.model.ResObject;
import com.baixin.model.User;
import com.baixin.service.CheckResultService;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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
    public String checkResultEditPost(Model model, HttpServletRequest request, CheckResult checkResult,
                                      HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        checkResult.setCreateUserId(user.getId());
        checkResultService.saveCheckResult(checkResult);
        return "redirect:checkResultPage_0_0_0";
    }
    
    /**
     * @desc 分页列表
     * @auther: liqz
     * @param: [checkResult, pageCurrent, pageSize, pageCount, model]
     * @return: java.lang.String
     * @date: 2020-01-08 14:41
     */
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
    
    /**
     * @desc 删除操作
     * @auther: liqz
     * @param: [checkResult, httpSession]
     * @return: com.baixin.model.ResObject<java.lang.Object>
     * @date: 2020-01-08 14:41
     */
    @ResponseBody
    @PostMapping("/user/checkResultDelete")
    @Transactional
    public ResObject<Object> checkResultDelete(CheckResult checkResult, HttpSession httpSession) {
        checkResultService.checkResultDelete(checkResult);
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }
    
    /**
     * @desc 替换word中指定文本
     * @auther: liqz
     * @param: [checkResultId, files, httpSession]
     * @return: java.lang.String
     * @date: 2020-01-08 14:40
     */
    @PostMapping("/user/detailCheckReports")
    @Transactional
    public String detailCheckReports(@RequestParam("checkResultId") int checkResultId,
                                     @RequestParam("files") MultipartFile[] files, HttpSession httpSession) {
        checkResultService.detailCheckReports(checkResultId, files);
        return "redirect:checkResultPage_0_0_0";
    }
    
    @PostMapping("/user/detailCheckReportsZip")
    @ResponseBody
    public void detailCheckReportsZip(@RequestParam("checkResultId") int checkResultId,
                                      @RequestParam("files") MultipartFile[] files, HttpServletResponse response) {
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            File file = checkResultService.detailCheckReports(checkResultId, files);
            response.setContentType("application/x-msdownload;");
            response.setContentType("application/force-download");// 强制下载
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
            response.setHeader("Content-Length", String.valueOf(file.length()));
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = 0;
            while(-1 != (i = bis.read(buffer))) {
                os.write(buffer, 0, i);
            }
            
            bis.close();
            fis.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}