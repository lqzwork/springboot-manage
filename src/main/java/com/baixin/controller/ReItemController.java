package com.baixin.controller;

import com.baixin.mapper.ItemMapper;
import com.baixin.mapper.ReItemMapper;
import com.baixin.model.Item;
import com.baixin.model.ReItem;
import com.baixin.model.ResObject;
import com.baixin.model.User;
import com.baixin.util.Constant;
import com.baixin.util.DateUtil;
import com.baixin.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 回收管理
 */
@Controller
public class ReItemController {

    @Autowired
    private ReItemMapper reItemMapper;

    @Autowired
    private ItemMapper itemMapper;

    @RequestMapping("/user/recoverManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String itemManage(ReItem reItem, @PathVariable Integer pageCurrent,
                             @PathVariable Integer pageSize,
                             @PathVariable Integer pageCount,
                             Model model) {
        if (pageSize == 0) pageSize = 20;
        if (pageCurrent == 0) pageCurrent = 1;
        int rows = reItemMapper.selectAll().size();
        if (pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        reItem.setStart((pageCurrent - 1) * pageSize);
        reItem.setEnd(pageSize);
        List<ReItem> reItemList = reItemMapper.selectAll();
        for (ReItem r : reItemList) {
            r.setRecoveredStr(DateUtil.getDateStr(r.getRecovered()));
        }
        model.addAttribute("reItemList", reItemList);
        String pageHTML = PageUtil.getPageContent("itemManage_{pageCurrent}_{pageSize}_{pageCount}?", pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("ReItem", reItem);
        return "item/recoverManage";
    }

    @ResponseBody
    @PostMapping("/user/reItemEditState")
    @Transactional
    public ResObject<Object> reItemEditState(ReItem reItem, HttpSession httpSession) {
        ReItem reItem1 = reItemMapper.selectByPrimaryKey(reItem.getId());
        Item item = new Item();
        item.setId(reItem1.getId());
        item.setBarcode(reItem1.getBarcode());
        item.setCid(reItem1.getCid());
        item.setImage(reItem1.getImage());
        item.setNorm(reItem1.getNorm());
        item.setUnit(reItem1.getUnit());
        item.setPrice(reItem1.getPrice());
        item.setSellPrice(reItem1.getSellPrice());
        item.setNum(reItem1.getNum());
        item.setSellPoint(reItem1.getSellPoint());
        item.setStatus(reItem1.getStatus());
        item.setTitle(reItem1.getTitle());
        item.setSupplier(reItem1.getSupplier());
        item.setCreated(new Date());
        item.setUpdated(new Date());
        item.setCreateUserId(reItem1.getCreateUserId() + "");
        User user = (User) httpSession.getAttribute("user");
        item.setUpdteUserId(user.getId() + "");
        itemMapper.insert(item);
        reItemMapper.deleteByPrimaryKey(reItem.getId());
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }

    @ResponseBody
    @PostMapping("/user/deleteItemEditState")
    @Transactional
    public ResObject<Object> deleteItemEditState(ReItem reItem) {
        reItemMapper.deleteByPrimaryKey(reItem.getId());
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }

}
