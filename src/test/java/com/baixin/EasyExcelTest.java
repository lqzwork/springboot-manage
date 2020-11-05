package com.baixin;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baixin.model.DemoData;
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
    public void setUp() {
        request = new MockHttpServletRequest();
        request.setCharacterEncoding("UTF-8");
        response = new MockHttpServletResponse();
    }
    
    /**
     * @desc 初始化导出数据
     * @auther: liqz
     * @param: []
     * @return: java.util.List<com.baixin.model.DemoData>
     * @date: 2020-06-23 17:13
     */
    private List<DemoData> data() {
        List<DemoData> list = new ArrayList();
        for(int i = 0; i < 100000; i++) {
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
            data.setIgnore("Y");
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
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start));
    }
    
    @Test
    public void testJxls() {
        long start = System.currentTimeMillis();
        Context jxlsContext = new Context();
        try {
            //获取数据源
            jxlsContext.putVar("list", data());
            //获取导出模板
            ClassPathResource resource = new ClassPathResource("files/jxls_test.xlsx");
            InputStream resourceInputStream = resource.getInputStream();
            
            JxlsHelper.getInstance().processTemplate(resourceInputStream, new FileOutputStream(new File("/Users" +
                    "/liqingzheng/Documents/result/test.xlsx")), jxlsContext);
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        long end = System.currentTimeMillis();
        System.out.println((end - start));
    }
    
    /**
     * @desc 动态表头
     * @auther: liqz
     * @param: []
     * @return: java.util.List<java.util.List       <       java.lang.String>>
     * @date: 2020-06-24 09:48
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
        EasyExcel.write(fileName, DemoData.class).excludeColumnFiledNames(excludeColumnFiledNames).sheet("模板").doWrite(data());
        
        fileName = path + System.currentTimeMillis() + ".xlsx";
        // 根据用户传入字段 假设我们只要导出 date
        Set<String> includeColumnFiledNames = new HashSet();
        includeColumnFiledNames.add("date");
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, DemoData.class).includeColumnFiledNames(includeColumnFiledNames).sheet("模板")
                .doWrite(data());
    }
    
    /**
     * 重复多次写入 多个sheet页
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link}
     * <p>
     * 2. 使用{@link}注解指定复杂的头
     * <p>
     * 3. 直接调用二次写入即可
     */
    @Test
    public void repeatedWrite() {
        // 方法1 如果写到同一个sheet
        String fileName = path + "repeatedWrite1-" + ".xlsx";
        ExcelWriter excelWriter = null;
        try {
            // 这里 需要指定写用哪个class去写
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < 5; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<DemoData> data = data();
                excelWriter.write(data, writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        
        // 方法2 如果写到不同的sheet 同一个对象
        long start2 = System.currentTimeMillis();
        fileName = path + "repeatedWrite2-" + ".xlsx";
        try {
            // 这里 指定文件
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (int i = 0; i < 5; i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<DemoData> data = data();
                excelWriter.write(data, writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        long end2 = System.currentTimeMillis();
        System.out.println((end2 - start2));
        
        // 方法3 如果写到不同的sheet 不同的对象 比方法2快一些
        long start3 = System.currentTimeMillis();
        fileName = path + "repeatedWrite3-" + ".xlsx";
        try {
            // 这里 指定文件
            excelWriter = EasyExcel.write(fileName).build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (int i = 0; i < 5; i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).head(DemoData.class).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                List<DemoData> data = data();
                excelWriter.write(data, writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        long end3 = System.currentTimeMillis();
        System.out.println((end3 - start3));
    }
    
    /**
     * @desc 测试根据数据量拆分多个sheet
     *
     * @auther: liqz
     * @param: []
     * @return: void
     * @date: 2020-06-28 17:27
     *
     */
    @Test
    public void testSheets() {
        // 方法3 如果写到不同的sheet 不同的对象 比方法2快一些
        long start3 = System.currentTimeMillis();
        String fileName = path + "repeatedWrite4-" + ".xlsx";
        ExcelWriter excelWriter = null;
        try {
            // 这里 指定文件
            excelWriter = EasyExcel.write(fileName).build();
            List<DemoData> data = data();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
            for (int i = 0; i < 5; i++) {
                // 每次都要创建writeSheet 这里注意必须指定sheetNo 而且sheetName必须不一样。这里注意DemoData.class 可以每次都变，我这里为了方便 所以用的同一个class 实际上可以一直变
                WriteSheet writeSheet = EasyExcel.writerSheet(i, "模板" + i).head(DemoData.class).build();
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                excelWriter.write(data, writeSheet);
            }
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
        long end3 = System.currentTimeMillis();
        System.out.println((end3 - start3));
    }
}
