package com.teligen.acg.code.build;

import java.util.Map;

/****
 * @Author: guobk
 * @Description:Dao构建
 * @Date  guobk 20210217
 *****/
public class MapperXmlBuilder {


  /***
   * 构建Dao
   * @param modelMap
   */
  public static void builder(Map<String,Object> modelMap){
    // 生成MyBatisXmlMapper文件
    BuilderFactory.builder(modelMap,
        "/template/mapperXml",
        "Mapper.xml",
        TemplateBuilder.PACKAGE_MAPPERXML,
        "Mapper.xml");
  }

}
