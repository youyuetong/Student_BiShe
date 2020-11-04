package edu.job.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.job.dto.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * 这是个可选标记类.
 */
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    /**
     * 查询条件中datatable内置的列
     */
    private static final String[] DATA_TABLE_PROPERTIES={"draw","columns","order","start","length","search"};

    /**
     * [针对以前老的列表页]准备SearchVo对象
     * @param json
     * @return
     */
    public SearchVo prepareSearchVo(String json){

        JSONObject originalJsonObject = JSON.parseObject(json);
        SearchVo searchVo = JSON.parseObject(json, SearchVo.class);

        //组装页面查询条件
        Iterator entries = originalJsonObject.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();

            //保留值为null的属性
//            if(entry.getValue()==null||StringUtils.equalsIgnoreCase(StringUtils.EMPTY,String.valueOf(entry.getValue()).trim())){
//                continue;
//            }

            if(!Arrays.asList(DATA_TABLE_PROPERTIES).contains(String.valueOf(entry.getKey()))){
                searchVo.addCondition(String.valueOf(entry.getKey()),entry.getValue());
            }
        }

        //dataTable 排序相关条件
        JSONArray jsonArrayOrder = originalJsonObject.getJSONArray("order");
        JSONArray jsonArrayColumn = originalJsonObject.getJSONArray("columns");
        if (jsonArrayOrder.size() > 0 && jsonArrayColumn.size() > 0) {
            JSONObject obj = jsonArrayOrder.getJSONObject(0);
            JSONObject columnObject = jsonArrayColumn.getJSONObject(obj.getInteger("column"));
            searchVo.addCondition("sidx", columnObject.get("data"));
            searchVo.addCondition("sort", obj.get("dir"));
        }

        return searchVo;
    }



}
