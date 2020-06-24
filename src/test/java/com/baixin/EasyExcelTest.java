package com.baixin;

import com.alibaba.excel.EasyExcel;
import com.baixin.model.DemoData;
import com.baixin.model.User;
import org.junit.Before;
import org.junit.Test;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

public class EasyExcelTest {
    
    private final String path = "/Users/liqingzheng/Documents/result/";
    
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
    @Before
    public void setUp(){
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }
    
    /**
     * @desc 初始化导出数据
     *
     * @auther: liqz
     * @param: []
     * @return: java.util.List<com.baixin.model.DemoData>
     * @date: 2020-06-23 17:13
     *
     */
    private List<DemoData> data() {
        List<DemoData> list = new ArrayList();
        for (int i = 0; i < 100; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            data.setName("name");
            data.setAge(20);
            data.setAli("ali");
            data.setCity("北京");
            data.setProvince("北京");
            data.setCounty("昌平");
            data.setSex("男");
            // data.setIgnore("Y");
            list.add(data);
        }
        return list;
    }
    
    private List<User> data2() {
        List<User> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            User data = new User();
            data.setRealName("字符串" + i);
            data.setAddDate(new Date());
            data.setEnd(1);
            list.add(data);
        }
        return list;
    }
    
    @Test
    public void uploadFileTest() {
        long start = System.currentTimeMillis();
        // 写法1
        String fileName = path + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());
    
        // 写法2
        fileName = path + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        // ExcelWriter excelWriter = null;
        // try {
        //     excelWriter = EasyExcel.write(fileName, DemoData.class).build();
        //     WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
        //     excelWriter.write(data(), writeSheet);
        // } finally {
        //     // 千万别忘记finish 会帮忙关闭流
        //     if (excelWriter != null) {
        //         excelWriter.finish();
        //     }
        // }
        long end = System.currentTimeMillis();
        System.out.println((end - start)/1000);
    }
    
    @Test
    public void testJxls() {
        long start = System.currentTimeMillis();
        Context jxlsContext = new Context();
        try {
            //获取数据源
            jxlsContext.putVar("list", data2());
            //获取导出模板
            ClassPathResource resource = new ClassPathResource("files/jxls_test.xlsx");
            InputStream resourceInputStream = resource.getInputStream();
            
            JxlsHelper.getInstance().processTemplate(resourceInputStream, new FileOutputStream(new File("/Users/liqingzheng/Documents/result/test.xlsx")), jxlsContext);
        } catch(Exception e) {
            e.printStackTrace();
        }
    
        long end = System.currentTimeMillis();
        System.out.println((end - start)/1000);
    }
    
    /**
     * @desc 动态表头
     *
     * @auther: liqz
     * @param: []
     * @return: java.util.List<java.util.List<java.lang.String>>
     * @date: 2020-06-24 09:48
     *
     */
    private List<List<String>> head() {
        List<List<String>> list = new ArrayList();
        List<String> head0 = new ArrayList();
        head0.add("字符串" + System.currentTimeMillis());
        List<String> head1 = new ArrayList();
        head1.add("数字" + System.currentTimeMillis());
        List<String> head2 = new ArrayList();
        head2.add("日期" + System.currentTimeMillis());
        list.add(head0);
        list.add(head1);
        list.add(head2);
        return list;
    }
    
    /**
     * 动态头，实时生成头写入
     * <p>
     * 思路是这样子的，先创建List<String>头格式的sheet仅仅写入头,然后通过table 不写入头的方式 去写入数据
     *
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 然后写入table即可
     */
    @Test
    public void dynamicHeadWrite() {
        String fileName = path + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(head()).sheet("模板")
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(data());
    }
    
    /**
     * 根据参数只导出指定列
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 根据自己或者排除自己需要的列
     * <p>
     * 3. 直接写即可
     *
     * @since 2.1.1
     */
    @Test
    public void excludeOrIncludeWrite() {
        String fileName = path + System.currentTimeMillis() + ".xlsx";
        
        // 根据用户传入字段 假设我们要忽略 date
        Set<String> excludeColumnFiledNames = new HashSet();
        excludeColumnFiledNames.add("date");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("模板")
                .doWrite(data());
        
        // fileName = path + System.currentTimeMillis() + ".xlsx";
        // // 根据用户传入字段 假设我们只要导出 date
        // Set<String> includeColumnFiledNames = new HashSet();
        // includeColumnFiledNames.add("date");
        // // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // EasyExcel.write(fileName, DemoData.class).includeColumnFiledNames(includeColumnFiledNames).sheet("模板")
        //         .doWrite(data());
    }
    
}
