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
import org.springframework.util.StringUtils;
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
    <#if SearchType=="0" || SearchType=="1">

        String resourceId = "${resourceID}";
        String tableName = "${TableID}";

        <#list ConditionWordsEqual as conditionEQ>
        String ${conditionEQ} = paramMap.get("${conditionEQ}") == null ? "" : paramMap.get("${conditionEQ}").toString();
        </#list>
        <#list ConditionWordsLike as conditionLK>
        String ${conditionLK} = paramMap.get("${conditionLK}") == null ? "" : paramMap.get("${conditionLK}").toString();
        </#list>
        <#if ConditionWordsStart??>
        String ${ConditionWordsStart} = paramMap.get("${ConditionWordsStart}") == null ? "" : paramMap.get("${ConditionWordsStart}").toString();
        </#if>
        <#if ConditionWordsEnd??>
        String ${ConditionWordsEnd} = paramMap.get("${ConditionWordsEnd}") == null ? "" : paramMap.get("${ConditionWordsEnd}").toString();
        </#if>
    </#if>
        int pageNum = paramMap.get("pageNum") == null ? 1 : Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = paramMap.get("pageSize") == null ? 1000 : Integer.parseInt(paramMap.get("pageSize").toString());

    <#if SearchType=="0" || SearchType=="1">
        String querySql = "select * from " + tableName + " where 1=1";
        String queryTotalSql = "select count(*) as TOTAL from " + tableName + " where 1=1 ";
        String conditionSql = "";
        String conditionLikeSql = "";
    <#list ConditionWordsEqual as conditionEQ>
        if(StringUtils.hasText("${conditionEQ}")){
            conditionSql +=" and ${conditionEQ}='" +${conditionEQ}+ "' ";
        }
    </#list>
    <#if ConditionWordsLike??>
    <#list ConditionWordsLike as conditionLK>
        if(StringUtils.hasText("${conditionLK}")){
            conditionLikeSql +="or ${conditionLK} like '%" +${conditionLK}+ "%' ";
        }
    </#list>
        if(StringUtils.hasText(conditionLikeSql)){
            conditionLikeSql = conditionLikeSql.substring(2);
        }
    </#if>
    <#if ConditionWordsStart??>
        if(StringUtils.hasText("${ConditionWordsStart}")){
            conditionSql +=" and ${ConditionWordsStart} &gt;= '" +${ConditionWordsStart}+ "' ";
        }
    </#if>
    <#if ConditionWordsEnd??>
        if(StringUtils.hasText("${ConditionWordsEnd}")){
            conditionSql +=" and ${ConditionWordsEnd} &lt;= '" +${ConditionWordsEnd}+ "' ";
        }
    </#if>
        if(StringUtils.hasText(conditionLikeSql)){
            conditionSql = conditionSql + " and ( " + conditionLikeSql + " ) ";
        }
        if(StringUtils.hasText(conditionSql)){
            querySql += conditionSql;
            queryTotalSql += conditionSql;
        }
    </#if>
    <#if SearchType == "0">
        List<Map> totalList = restTemplate.postForObject(this.common_url + "/matrixservice/queryMPPBySQL?resourceId={resourceId}&querySQL={querySQL}",null,List.class,resourceId,queryTotalSql);
        if(totalList.size() > 0){
            resultMap.put("total",Integer.parseInt((String)totalList.get(0).get("TOTAL")));
            List<Map> dataList = restTemplate.postForObject(this.common_url + "/matrixservice/queryMPPBySQL?resourceId={resourceId}&querySQL={querySQL}&pageNum={pageNum}&pageSize={pageSize}",null,List.class,resourceId,querySql,pageNum,pageSize);
            resultMap.put("detail",dataList);
        }else {
            resultMap.put("total",0);
            resultMap.put("detail",new ArrayList<>());
        }
    <#elseif SearchType == "1">
        List<Map> totalList = restTemplate.postForObject(this.common_url + "/matrixservice/queryOracleBySQL?resourceId={resourceId}&querySQL={querySQL}",null,List.class,resourceId,queryTotalSql);
        if(totalList.size() > 0){
            resultMap.put("total",Integer.parseInt((String)totalList.get(0).get("TOTAL")));
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
