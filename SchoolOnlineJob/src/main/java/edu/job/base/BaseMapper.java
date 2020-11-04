package edu.job.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用mapper接口
 * @author chenjindong
 * @date 2019/1/26 11:32
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
