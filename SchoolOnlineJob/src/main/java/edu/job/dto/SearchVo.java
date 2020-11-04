package edu.job.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * 与查询条件一一对应的查询对象
 */
public class SearchVo {

    private Integer draw;

    private Integer start;

    private Integer length;

    private Map<String,Object> condition=new HashMap<>();

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Map<String, Object> getCondition() {
        return condition;
    }

    public void setCondition(Map<String, Object> condition) {
        this.condition = condition;
    }

    /**
     * 链式添加查询条件
     * 对前台传递过来的页面查询条件添加一些自定义参数
     * @param key
     * @param value
     * @return
     */
    public SearchVo addCondition(String key, Object value){
        this.getCondition().put(key,value);
        return this;
    }
}


