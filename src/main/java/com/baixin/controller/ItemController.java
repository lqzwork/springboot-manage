package com.baixin.controller;

import com.baixin.common.PageInfo;
import com.baixin.mapper.ItemCategoryMapper;
import com.baixin.mapper.ItemMapper;
import com.baixin.mapper.ReItemMapper;
import com.baixin.model.*;
import com.baixin.service.OperateLogService;
import com.baixin.util.*;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.*;


/**
 * 药品管理
 */
@Controller
public class ItemController {
    
    @Autowired
    private ItemMapper itemMapper;
    
    @Autowired
    private ItemCategoryMapper itemCategoryMapper;
    
    @Autowired
    private ReItemMapper reItemMapper;
    
    @Autowired
    private OperateLogService operateLogService;
    
    public static final String ROOT = "src/main/resources/static/img/item/";
    
    // MongoUtil mongoUtil = new MongoUtil();
    
    private final ResourceLoader resourceLoader;
    
    @Autowired
    public ItemController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    List<Item> itemList;
    File getFile = null;
    
    @RequestMapping("/user/itemManage_{pageCurrent}_{pageSize}_{pageCount}")
    public String itemManage(Item item, @PathVariable Integer pageCurrent, @PathVariable Integer pageSize,
                             @PathVariable Integer pageCount, Model model) {
        if(pageSize == 0) pageSize = 20;
        if(pageCurrent == 0) pageCurrent = 1;
        
        int rows = itemMapper.count(item);
        if(pageCount == 0) pageCount = rows % pageSize == 0 ? (rows / pageSize) : (rows / pageSize) + 1;
        item.setStart((pageCurrent - 1) * pageSize);
        item.setEnd(pageSize);
        itemList = itemMapper.list(item);
        for(Item i : itemList) {
            i.setUpdatedStr(DateUtil.getDateStr(i.getUpdated()));
            i.setCreatedStr(DateUtil.getDateStr(i.getCreated()));
        }
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);
        List<ItemCategory> itemCategoryList = itemCategoryMapper.list(itemCategory);
        Integer minPrice = item.getMinPrice();
        Integer maxPrice = item.getMaxPrice();
        model.addAttribute("itemCategoryList", itemCategoryList);
        model.addAttribute("itemList", itemList);
        String pageHTML =
                PageUtil.getPageContent("itemManage_{pageCurrent}_{pageSize}_{pageCount}?title=" + item.getTitle() +
                        "&cid=" + item.getCid() + "&minPrice" + minPrice + "&maxPrice" + maxPrice, pageCurrent,
                        pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("item", item);
        return "item/itemManage";
    }
    
    @RequestMapping("/user/download1")
    public void postItemExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //导出excel
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
        fieldMap.put("id", "药品id");
        fieldMap.put("title", "药品名称");
        fieldMap.put("supplier", "供应商/产地");
        fieldMap.put("norm", "规格");
        fieldMap.put("unit", "单位");
        fieldMap.put("price", "药品进价");
        fieldMap.put("sellPrice", "药品售价");
        fieldMap.put("num", "库存数量");
        fieldMap.put("createUserId", "创建人ID");
        fieldMap.put("createdStr", "创建时间");
        fieldMap.put("updatedStr", "更新时间");
        String sheetName = "药品管理报表";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=baixin_yiyao.xls");//默认Excel名称
        response.flushBuffer();
        OutputStream fos = response.getOutputStream();
        try {
            ExcelUtil.listToExcel(itemList, fieldMap, sheetName, fos);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
    String imageName = null;
    
    @GetMapping("/user/itemEdit")
    @Transactional
    public String itemEditGet(Model model, Item item) {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setStart(0);
        itemCategory.setEnd(Integer.MAX_VALUE);
        List<ItemCategory> itemCategoryList = itemCategoryMapper.list(itemCategory);
        model.addAttribute("itemCategoryList", itemCategoryList);
        if(item.getId() != 0) {
            Item item1 = itemMapper.findById(item);
            String id = String.valueOf(item.getId());
            // GridFSDBFile fileById = mongoUtil.getFileById(id);
            GridFSDBFile fileById = null;
            if(fileById != null) {
                StringBuilder sb = new StringBuilder(ROOT);
                imageName = fileById.getFilename();
                sb.append(imageName);
                try {
                    getFile = new File(sb.toString());
                    fileById.writeTo(getFile);
                } catch(Exception e) {
                    e.printStackTrace();
                }
                item1.setImage(imageName);
            }
            model.addAttribute("item", item1);
        }
        return "item/itemEdit";
    }
    
    @PostMapping("/user/itemEdit")
    @Transactional
    public String itemEditPost(Model model, HttpServletRequest request, Item item, HttpSession httpSession) {
        //根据时间和随机数生成id
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        item.setBarcode("");
        item.setImage("");
        User user = (User) httpSession.getAttribute("user");
        item.setUpdteUserId(user.getId() + "");
        if(item.getId() != 0) {
            itemMapper.update(item);
        } else {
            item.setCreateUserId(user.getId() + "");
            itemMapper.insert(item);
        }
        return "redirect:itemManage_0_0_0";
    }
    
    @GetMapping(value = "/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile() {
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, imageName).toString()));
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @ResponseBody
    @PostMapping("/user/itemEditState")
    @Transactional
    public ResObject<Object> itemEditState(Item item1, HttpSession httpSession) {
        Item item = itemMapper.findById(item1);
        ReItem reItem = new ReItem();
        reItem.setId(item.getId());
        reItem.setBarcode(item.getBarcode());
        reItem.setCid(item.getCid());
        reItem.setImage(item.getImage());
        reItem.setNorm(item.getNorm());
        reItem.setUnit(item.getUnit());
        reItem.setPrice(item.getPrice());
        reItem.setSellPrice(item.getSellPrice());
        reItem.setNum(item.getNum());
        reItem.setSellPoint(item.getSellPoint());
        reItem.setStatus(item.getStatus());
        reItem.setTitle(item.getTitle());
        reItem.setSupplier(item.getSupplier());
        reItem.setRecovered(new Date());
        reItem.setCreateUserId(item.getCreateUserId());
        User user = (User) httpSession.getAttribute("user");
        reItem.setUpdteUserId(user.getId() + "");
        reItemMapper.insert(reItem);
        itemMapper.delete(item1);
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }
    
    @ResponseBody
    @PostMapping("/user/itemEditNum")
    @Transactional
    public ResObject<Object> itemEditNum(Item item1, HttpSession httpSession) {
        item1.setUpdated(new Date());
        int update = itemMapper.updateNum(item1);
        OperateLog operateLog = new OperateLog();
        operateLog.setItemId(item1.getId() + "");
        operateLog.setModifyNum(item1.getNum());
        operateLog.setOperate(item1.getNum() > 0 ? "买入" : "卖出");
        User user = (User) httpSession.getAttribute("user");
        operateLog.setOperateUserId(user.getId() + "");
        operateLog.setOperateTime(new Date());
        operateLogService.recordOperateLog(operateLog);
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }
    
    /**
     * @desc 获取操作日志
     * @auther: liqz
     * @param: [item1, httpSession]
     * @return: com.baixin.model.ResObject<java.lang.Object>
     * @date: 2019-11-22 17:05
     */
    @PostMapping("/user/itemOperateLogs")
    @Transactional
    public ResObject<Object> itemOperateLogs(Item item1, HttpSession httpSession) {
        int update = itemMapper.updateNum(item1);
        OperateLog operateLog = new OperateLog();
        operateLog.setItemId(item1.getId() + "");
        operateLog.setModifyNum(item1.getNum());
        operateLog.setOperate(item1.getNum() > 0 ? "买入" : "卖出");
        User user = (User) httpSession.getAttribute("user");
        operateLog.setOperateUserId(user.getId() + "");
        operateLog.setOperateTime(new Date());
        operateLogService.recordOperateLog(operateLog);
        ResObject<Object> object = new ResObject<Object>(Constant.Code01, Constant.Msg01, null, null);
        return object;
    }
    
    @RequestMapping("/user/listOperateLogPage_{pageCurrent}_{pageSize}_{pageCount}")
    public String listOperateLogPage(OperateLog operateLog, @PathVariable Integer pageCurrent,
                                     @PathVariable Integer pageSize, @PathVariable Integer pageCount, Model model) {
        if(pageCurrent == 0) {
            pageCurrent = 1;
        }
        pageSize = PageInfo.pageSize;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("itemId", operateLog.getItemId());
        paramMap.put("pageNum", pageCurrent);
        paramMap.put("pageSize", pageSize);
        PageInfo page = operateLogService.listPage(paramMap);
        pageCount = (page.getTotalSize() / pageSize) + 1;
        model.addAttribute("operateLogPageList", page.getContentList());
        String pageHTML =
                PageUtil.getPageContent("listOperateLogPage_{pageCurrent}_{pageSize}_{pageCount}?itemId=" + operateLog.getItemId(), pageCurrent, pageSize, pageCount);
        model.addAttribute("pageHTML", pageHTML);
        model.addAttribute("operateLog", operateLog);
        return "item/listOperateLogPage";
    }
}
