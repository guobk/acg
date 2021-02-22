package ${package_service_impl};

<#if SearchType=="2" || SearchType=="3">
import ${package_mapper}.${Table}Mapper;
import ${package_vo}.${Table};
</#if>
import ${package_service}.${Table}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/****
 * @Author: guobk
 * @Description:${Table}业务层接口实现类
 *****/
@Service
public class ${Table}ServiceImpl implements ${Table}Service {

    Logger logger = LoggerFactory.getLogger(${Table}ServiceImpl.class);
    <#if SearchType=="2" || SearchType=="3">

    @Autowired
    private ${Table}Mapper ${table}Mapper;
    </#if>

    @Autowired
    private RestTemplate restTemplate;

    ${r'@Value("${common_url}")'}
    private String common_url;

    /***
     * 查询${Table}列表数据
     * @param paramMap
     * @return
     */
    @Override
    public Map get${Table}List(Map paramMap){
        Map resultMap = new HashMap<>();

        String resourceId = "${resourceID}";
        String tableName = "${TableID}";
        int pageNum = paramMap.get("pageNum") == null ? 1 : Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = paramMap.get("pageSize") == null ? 1 : Integer.parseInt(paramMap.get("pageSize").toString());

    <#if SearchType=="0">
        String querySql = "select * from " + tableName + " where 1=1";
        String queryTotalSql = "select count(*) from " + tableName + " where 1=1";
        List<Map> totalList = restTemplate.postForObject(this.common_url + "/matrixservice/queryMPPBySQL?resourceId={resourceId}&querySQL={querySQL}",null,List.class,resourceId,queryTotalSql);
        if(totalList.size() > 0){
            resultMap.put("total",Integer.parseInt((String)totalList.get(0).get("COUNT_DATA")));
            List<Map> dataList = restTemplate.postForObject(this.common_url + "/matrixservice/queryMPPBySQL?resourceId={resourceId}&querySQL={querySQL}&pageNum={pageNum}&pageSize={pageSize}",null,List.class,resourceId,querySql,pageNum,pageSize);
            resultMap.put("detail",dataList);
        }else {
            resultMap.put("total",0);
            resultMap.put("detail",new ArrayList<>());
        }
    <#elseif SearchType == "1">
        String querySql = "select * from " + tableName + " where 1=1";
        String queryTotalSql = "select count(*) from " + tableName + " where 1=1";
        List<Map> totalList = restTemplate.postForObject(this.common_url + "/matrixservice/queryOracleBySQL?resourceId={resourceId}&querySQL={querySQL}",null,List.class,resourceId,queryTotalSql);
        if(totalList.size() > 0){
            resultMap.put("total",Integer.parseInt((String)totalList.get(0).get("COUNT_DATA")));
            List<Map> dataList = restTemplate.postForObject(this.common_url + "/matrixservice/queryOracleBySQL?resourceId={resourceId}&querySQL={querySQL}&pageNum={pageNum}&pageSize={pageSize}",null,List.class,resourceId,querySql,pageNum,pageSize);
            resultMap.put("detail",dataList);
        }else {
            resultMap.put("total",0);
            resultMap.put("detail",new ArrayList<>());
        }
    <#elseif SearchType == "2">
        //TODO Oracle
    <#elseif SearchType == "3">
        //TODO MySql
    </#if>

        return resultMap;
    }
}
