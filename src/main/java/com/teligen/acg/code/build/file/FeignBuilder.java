package com.teligen.acg.code.build.file;

import com.teligen.acg.code.build.BuilderFactory;
import com.teligen.acg.code.build.TemplateBuilder;

import java.util.Map;

/****
 * @Author: guobk
 * @Description:Feign构建
 * @Date  guobk 20210217
 *****/
public class FeignBuilder {


    /***
     * 构建Feign
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap){
        // 生成Dao层文件
        BuilderFactory.builder(modelMap,
                "/template/feign",
                "Feign.java",
                TemplateBuilder.PACKAGE_FEIGN,
                "Feign.java");
    }

}
