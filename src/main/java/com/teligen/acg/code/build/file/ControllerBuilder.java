package com.teligen.acg.code.build.file;

import com.teligen.acg.code.build.BuilderFactory;
import com.teligen.acg.code.build.TemplateBuilder;

import java.util.Map;

/****
 * @Author: guobk
 * @Description:Controller构建
 * @Date  guobk 20210217
 *****/
public class ControllerBuilder {

    /***
     * 构建Controller
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap){
        // 生成Controller层文件
        BuilderFactory.builder(modelMap,
                "/template/controller",
                "Controller.java",
                TemplateBuilder.PACKAGE_CONTROLLER,
                "Controller.java");
    }

}
