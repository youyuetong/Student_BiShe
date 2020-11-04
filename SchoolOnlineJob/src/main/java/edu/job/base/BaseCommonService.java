package edu.job.base;

/**
 * 继承此类的类请务必一定要传入泛型T,严禁继承此类不传入泛型T
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.job.dto.SearchVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通用基础Service
 * @description
 */
public abstract class BaseCommonService {


    /**
     * 声明函数式编程接口,为了规避PageHelper插件的限制
     * @param <T>
     */
    @FunctionalInterface
    public interface Function<T> {
        /**
         * 回调方法
         * @return
         */
        List<T> callback();
    }

    /**
     * 通用分页
     * @param func
     * @param searchVo
     * @return
     */
    public Map<String,Object> wrapPage(Function func, SearchVo searchVo){

        Map<String,Object> result=new HashMap<>();
        PageHelper.startPage( searchVo.getStart() /  searchVo.getLength()+1, searchVo.getLength());
        PageInfo pageInfo = new PageInfo(func.callback());

        result.put("draw",searchVo.getDraw()+1);
        result.put("queryresult", pageInfo.getList());
        result.put("recordsTotal", pageInfo.getTotal());
        result.put("recordsFiltered", pageInfo.getTotal());

        return result;

    }





}

