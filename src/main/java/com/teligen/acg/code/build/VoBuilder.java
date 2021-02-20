package com.teligen.acg.code.build;

import java.util.Map;

/****
 * @Author: guobk
 * @Description:Vo构建
 * @Date  guobk 20210217
 *****/
public class VoBuilder {


    /***
     * 构建Vo
     * @param dataModel
     */
    public static void builder(Map<String,Object> dataModel){
        // 生成Vo层文件
        BuilderFactory.builder(dataModel,
                "/template/vo",
                "Vo.java",
                TemplateBuilder.PACKAGE_VO,
                ".java");
    }

}
