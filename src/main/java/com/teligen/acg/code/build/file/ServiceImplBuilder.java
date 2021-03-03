package com.teligen.acg.code.build.file;

import com.teligen.acg.code.build.BuilderFactory;
import com.teligen.acg.code.build.TemplateBuilder;

import java.util.Map;

/****
 * @Author: guobk
 * @Description:ServiceImpl构建
 * @Date  guobk 20210217
 *****/
public class ServiceImplBuilder {

    /***
     * ServiceImpl构建
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap){
        // 生成ServiceImpl层文件
        BuilderFactory.builder(modelMap,
                "/template/service/impl",
                "ServiceImpl.java",
                TemplateBuilder.PACKAGE_SERVICE_INTERFACE_IMPL,
                "ServiceImpl.java");
    }

}
