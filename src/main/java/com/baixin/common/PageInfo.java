package com.baixin.common;

import com.baixin.model.CheckResult;
import com.baixin.model.OperateLog;

import java.util.List;
import java.util.Map;

/**
 * @ClassName : PageInfo
 * @Author : liqz
 * @desc : PageInfo 分页
 * @Date : 2019/11/12 21:36
 **/
public class PageInfo {

    private int totalSize;
    public static int pageSize = 10;
    private int pageNum;
    private List<OperateLog> contentList;
    private List<CheckResult> contentList2;

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<OperateLog> getContentList() {
        return contentList;
    }

    public void setContentList(List<OperateLog> contentList) {
        this.contentList = contentList;
    }
    
    public List<CheckResult> getContentList2() {
        return contentList2;
    }
    
    public void setContentList2(List<CheckResult> contentList2) {
        this.contentList2 = contentList2;
    }
}
