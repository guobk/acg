package com.teligen.acg.code.build;

import java.util.Map;

/****
 * @Author: guobk
 * @Description:Dao构建
 * @Date  guobk 20210217
 *****/
public class MapperBuilder {


    /***
     * 构建Dao
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap){
        // 生成Mapper层文件
        BuilderFactory.builder(modelMap,
            "/template/mapper",
                "Mapper.java",
                TemplateBuilder.PACKAGE_MAPPER,
                "Mapper.java");
    }

}
