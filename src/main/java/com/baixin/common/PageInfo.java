package com.baixin.common;

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
    public static int pageSize = 20;
    private int pageNum;
    private List<OperateLog> contentList;

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

}
