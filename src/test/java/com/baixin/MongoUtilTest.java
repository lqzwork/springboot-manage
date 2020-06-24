package com.baixin;

import org.junit.Test;

public class MongoUtilTest {
    @Test
    public void uploadFileTest() {
        // MongoUtil mongoUtil = new MongoUtil();
        // File files = new File("/Users/liqingzheng/Job_work/Idea_Work/springboot-manage/src/main/resources/static
        // /img/cat.jpg");
        // String id = UUID.randomUUID().toString().replaceAll("-", "");
        //
        // LinkedHashMap<String,Object> metaMap = new LinkedHashMap<String, Object>();
        // System.out.println("我的id是:" + id );
        // metaMap.put("contentType","cat");
        // metaMap.put("_id",id);
        //
        //
        // mongoUtil.uploadFile(files,id,metaMap);
        // System.out.println("Upload File Success");
    }
    
    @Test
    public void deleteFileTest() {
        // MongoUtil mongoUtil = new MongoUtil();
        // String id = "94b6c9aee0cb4c98b6f670a953dd5589";
        //
        // mongoUtil.deleteFile(id);
        // System.out.println("Delete File Success");
    }
    
    @Test
    public void getFileByIdTest() {
        // MongoUtil mongoUtil = new MongoUtil();
        // String id = "92b2d9bb14ee44dbbeac339abfa30d2c";
        // String dbName = "baixin";
        // String collectionName = "myimage";
        // GridFSDBFile fileById = mongoUtil.getFileById(id);
        // try {
        //     System.out.println(fileById.writeTo(new File
        //     ("/Users/liqingzheng/Job_work/Idea_Work/springboot-manage/src/main/resources/static/img/jyxmust.jpg")));
        // }catch (Exception ex){
        //     ex.printStackTrace();
        // }
        // System.out.println("get File by Id Success");
    }
    
    @Test
    public void getAllFileTest() {
        // MongoUtil mongoUtil = new MongoUtil();
        // String dbName = "baixin";
        // String collectionName = "myimage";
        // List<GridFSDBFile> files = mongoUtil.getAllFile();
        // for (GridFSDBFile files:files) {
        //     //files.writeTo(new File("${path}"))
        //     System.out.println(files.getId() + ":" + files.getFilename());
        // }
        // System.out.println("get All File Sucess!!");
    }
    
    @Test
    public void batchDeleteFileByIds() {
        // MongoUtil mongoUtil = new MongoUtil();
        // String[] ids = {"163f5484cd60416ebb71c64814205b41","b815a254f0514867b8e9745b4bb974b5"};
        // String dbName = "baixin";
        // String collectionName = "myimage";
        // mongoUtil.batchDeleteFileByIds(ids);
        // System.out.println("batch delete Files success");
    }
}
