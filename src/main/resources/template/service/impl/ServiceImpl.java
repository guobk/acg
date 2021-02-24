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
    <#if SearchType=="0" || SearchType=="1">

    private static final String QueryMPPRoute = "/matrixservice/queryMPPBySQL?resourceId={resourceId}&querySQL={querySQL}";
    private static final String QueryOracleRoute = "/matrixservice/queryOracleBySQL?resourceId={resourceId}&querySQL={querySQL}";
    private static final String SaveSQLRoute = "/matrixservice/executeSaveData?resourceId={resourceId}&hql={hql}";
    </#if>

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

        <#list SearchConditionWordsEqual as conditionEQ>
        String ${conditionEQ} = paramMap.get("${conditionEQ}") == null ? "" : paramMap.get("${conditionEQ}").toString();
        </#list>
        <#list SearchConditionWordsLike as conditionLK>
        String ${conditionLK} = paramMap.get("${conditionLK}") == null ? "" : paramMap.get("${conditionLK}").toString();
        </#list>
        <#if SearchConditionWordsStart??>
        String ${SearchConditionWordsStart} = paramMap.get("${SearchConditionWordsStart}") == null ? "" : paramMap.get("${SearchConditionWordsStart}").toString();
        </#if>
        <#if SearchConditionWordsEnd??>
        String ${SearchConditionWordsEnd} = paramMap.get("${SearchConditionWordsEnd}") == null ? "" : paramMap.get("${SearchConditionWordsEnd}").toString();
        </#if>
    </#if>
        int pageNum = paramMap.get("pageNum") == null ? 1 : Integer.parseInt(paramMap.get("pageNum").toString());
        int pageSize = paramMap.get("pageSize") == null ? 1000 : Integer.parseInt(paramMap.get("pageSize").toString());

    <#if SearchType=="0" || SearchType=="1">
        String querySql = "select * from " + tableName + " where 1=1";
        String queryTotalSql = "select count(*) as TOTAL from " + tableName + " where 1=1 ";
        String conditionSql = "";
        String conditionLikeSql = "";
    <#list SearchConditionWordsEqual as conditionEQ>
        if(StringUtils.hasText("${conditionEQ}")){
            conditionSql +=" and ${conditionEQ}='" +${conditionEQ}+ "' ";
        }
    </#list>
    <#if SearchConditionWordsLike??>
    <#list SearchConditionWordsLike as conditionLK>
        if(StringUtils.hasText("${conditionLK}")){
            conditionLikeSql +="or ${conditionLK} like '%" +${conditionLK}+ "%' ";
        }
    </#list>
        if(StringUtils.hasText(conditionLikeSql)){
            conditionLikeSql = conditionLikeSql.substring(2);
        }
    </#if>
    <#if SearchConditionWordsStart??>
        if(StringUtils.hasText("${SearchConditionWordsStart}")){
            conditionSql +=" and ${SearchConditionWordsStart} &gt;= '" +${SearchConditionWordsStart}+ "' ";
        }
    </#if>
    <#if SearchConditionWordsEnd??>
        if(StringUtils.hasText("${SearchConditionWordsEnd}")){
            conditionSql +=" and ${SearchConditionWordsEnd} &lt;= '" +${SearchConditionWordsEnd}+ "' ";
        }
    </#if>
        if(StringUtils.hasText(conditionLikeSql)){
            conditionSql = conditionSql + " and ( " + conditionLikeSql + " ) ";
        }
        if(StringUtils.hasText(conditionSql)){
            querySql += conditionSql;
            queryTotalSql += conditionSql;
        }
        logger.info("本次查询的SQL为:"+querySql);
    </#if>
    <#if SearchType == "0">
        List<Map> totalList = restTemplate.postForObject(this.common_url + this.QueryMPPRoute,null,List.class,resourceId,queryTotalSql);
        if(totalList.size() > 0){
            logger.info("查询成功,总数量为:"+totalList.size());
            resultMap.put("total",Integer.parseInt((String)totalList.get(0).get("TOTAL")));
            List<Map> dataList = restTemplate.postForObject(this.common_url + this.QueryMPPRoute + "&pageNum={pageNum}&pageSize={pageSize}",null,List.class,resourceId,querySql,pageNum,pageSize);
            resultMap.put("detail",dataList);
        }else {
            logger.warn("本次查询结果数量为0");
            resultMap.put("total",0);
            resultMap.put("detail",new ArrayList<>());
        }
    <#elseif SearchType == "1">
        List<Map> totalList = restTemplate.postForObject(this.common_url + this.QueryOracleRoute,null,List.class,resourceId,queryTotalSql);
        if(totalList.size() > 0){
            logger.info("查询成功,总数量为:"+totalList.size());
            resultMap.put("total",Integer.parseInt((String)totalList.get(0).get("TOTAL")));
            List<Map> dataList = restTemplate.postForObject(this.common_url + this.QueryOracleRoute + "&pageNum={pageNum}&pageSize={pageSize}",null,List.class,resourceId,querySql,pageNum,pageSize);
            resultMap.put("detail",dataList);
        }else {
            logger.warn("本次查询结果数量为0");
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

    /***
     * 删除${Table}列表数据
     * @param paramMap
     * @return
     */
    @Override
    public Map delete${Table}List(Map paramMap){
        Map resultMap = new HashMap<>();
    <#if SearchType=="0" || SearchType=="1">

        String resourceId = "${resourceID}";
        String tableName = "${TableID}";

    <#list DeleteConditionWords as conditionDEL>
        String[] ${conditionDEL}List = paramMap.get("${conditionDEL}") == null ? new String[0] : paramMap.get("${conditionDEL}").toString().split("${DeleteConditionSplit}");
    </#list>

    <#list DeleteConditionWords as conditionDEL>
        if(${conditionDEL}List.length <=0){
            logger.error("删除${Table}对象时，检测到${conditionDEL}条件为空");
            resultMap.put("status","error");
            resultMap.put("info","删除失败，${conditionDEL}条件为空");
            return resultMap;
        }
    </#list>
    </#if>

    <#if SearchType == "0" ||   SearchType == "1">
        <#list DeleteConditionWords as conditionDEL>
        <#if conditionDEL_index == 0>
        for (int i = 0; i < ${conditionDEL}List.length; i++) {
            String deleteSql = "delete from " + tableName + " where 1=1 ";
            try {
            <#list DeleteConditionWords as tempDEL>
                deleteSql +=" and ${tempDEL}='" +${tempDEL}List[i]+ "' ";
            </#list>
                logger.info("本次删除语句为:"+deleteSql);
                restTemplate.postForObject(this.common_url + this.SaveSQLRoute,null,Map.class,resourceId,deleteSql);
            } catch(Exception e) {
                logger.error("${Table}对象删除失败");
                resultMap.put("status","error");
                resultMap.put("info","删除失败，请联系管理员");
                e.printStackTrace();
                return resultMap;
            }
        }
        </#if>
        </#list>

        logger.info("${Table}对象删除成功");
        resultMap.put("status","success");
        return resultMap;

    <#elseif SearchType == "2">
    //TODO Oracle
    <#elseif SearchType == "3">
    //TODO MySql
    </#if>
    }

    /***
     * 更新${Table}列表数据
     * @param paramMap
     * @return
     */
    @Override
    public Map update${Table}(Map paramMap){
        Map resultMap = new HashMap<>();
    <#if SearchType=="0" || SearchType=="1">

        String resourceId = "${resourceID}";
        String tableName = "${TableID}";

    <#list UpdateConditionWords as conditionUPDATE>
        String ${conditionUPDATE} = paramMap.get("${conditionUPDATE}") == null ? "" : paramMap.get("${conditionUPDATE}").toString();
    </#list>
    <#list UpdateKeyWords as keyWords>
        String ${keyWords} = paramMap.get("${keyWords}") == null ? "" : paramMap.get("${keyWords}").toString();
    </#list>

    <#list UpdateKeyWords as keyWords>
        if(!StringUtils.hasText("${keyWords}")){
            logger.error("更新${Table}对象时，检测到${keyWords}条件为空");
            resultMap.put("status","error");
            resultMap.put("info","更新失败，${keyWords}条件为空");
            return resultMap;
        }
    </#list>
    </#if>

    <#if SearchType == "0" ||   SearchType == "1">
        String updateSql = "update set " + tableName;
    <#list UpdateConditionWords as conditionUPDATE>
        updateSql +=" ${conditionUPDATE} ='" + ${conditionUPDATE} + "' ";
    </#list>
        updateSql +=" where 1=1 ";
    <#list UpdateKeyWords as keyWords>
        updateSql +=" and ${keyWords} ='" + ${keyWords} + "'";
    </#list>
        logger.info("本次更新语句为:"+updateSql);
        try {
            restTemplate.postForObject(this.common_url + this.SaveSQLRoute,null,Map.class,resourceId,updateSql);
        } catch(Exception e) {
            logger.error("${Table}对象更新失败");
            resultMap.put("status","error");
            resultMap.put("info","更新失败，请联系管理员");
            e.printStackTrace();
            return resultMap;
        }

        logger.info("${Table}对象更新成功");
        resultMap.put("status","success");
        return resultMap;

    <#elseif SearchType == "2">
    //TODO Oracle
    <#elseif SearchType == "3">
    //TODO MySql
    </#if>
    }

    /***
     * 新增${Table}列表数据
     * @param paramMap
     * @return
     */
    @Override
    public Map insert${Table}(Map paramMap){
        Map resultMap = new HashMap<>();
    <#if SearchType=="0" || SearchType=="1">

        String resourceId = "${resourceID}";
        String tableName = "${TableID}";

    <#list InsertConditionWords as conditionINSERT>
        String ${conditionINSERT} = paramMap.get("${conditionINSERT}") == null ? "" : paramMap.get("${conditionINSERT}").toString();
    </#list>

    </#if>

    <#if SearchType == "0" ||   SearchType == "1">
        String insertSql = "insert into " + tableName + " ( ";
    <#list InsertConditionWords as conditionINSERT>
    <#if conditionINSERT_index == 0>
        insertSql += "${conditionINSERT}";
    <#else>
        insertSql += ",${conditionINSERT}";
    </#if>
    </#list>
        insertSql +=")";
        insertSql +=" values ( ";
    <#list InsertConditionWords as conditionINSERT>
    <#if conditionINSERT_index == 0>
        insertSql += "'" + ${conditionINSERT} + "'";
    <#else>
        insertSql += ",'" + ${conditionINSERT} + "'";
    </#if>
    </#list>
        insertSql +=")";
        logger.info("本次新增语句为:"+insertSql);
        try {
            restTemplate.postForObject(this.common_url + this.SaveSQLRoute,null,Map.class,resourceId,insertSql);
        } catch(Exception e) {
            logger.info("${Table}对象新增失败");
            resultMap.put("status","error");
            resultMap.put("info","新增失败，请联系管理员");
            e.printStackTrace();
            return resultMap;
        }

        logger.info("${Table}对象新增成功");
        resultMap.put("status","success");
        return resultMap;

    <#elseif SearchType == "2">
    //TODO Oracle
    <#elseif SearchType == "3">
    //TODO MySql
    </#if>
    }
}
