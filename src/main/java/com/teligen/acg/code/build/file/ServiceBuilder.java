package com.teligen.acg.code.build.file;

import com.teligen.acg.code.build.BuilderFactory;
import com.teligen.acg.code.build.TemplateBuilder;

import java.util.Map;

/****
 * @Author: guobk
 * @Description:Service构建
 * @Date  guobk 20210217
 *****/
public class ServiceBuilder {


    /***
     * 构建Service
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap){
        // 生成Service层文件
        BuilderFactory.builder(modelMap,
                "/template/service",
                "Service.java",
                TemplateBuilder.PACKAGE_SERVICE_INTERFACE,
                "Service.java");
    }

}
