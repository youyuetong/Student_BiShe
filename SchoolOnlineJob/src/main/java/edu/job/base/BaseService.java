package edu.job.base;

/**
 * 继承此类的类请务必一定要传入泛型T,严禁继承此类不传入泛型T
 * @author chenjindong
 * @date 2019/1/26 11:33
 */

import edu.job.config.database.DataSource;
import edu.job.config.database.EnumDataSource;
import edu.job.dto.SearchVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 通用Service
 * <pre><b>非常重要!!! 继承此类的类请务必一定要传入泛型T,严禁继承此类,不传入泛型T</b></pre>
 * @author chenjindong
 * @date 2018/11/17 11:08
 * @description
 */
public abstract class BaseService<@NotNull(message = "继承此类的类请务必一定要传入泛型T,严禁继承此类不传入泛型T") T> extends BaseCommonService {

    /**
     * Spring 4.0 提供的新特性，泛型注入
     */
    @Autowired
    private BaseMapper<T> mapper;


    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int insert(T record){
        return this.mapper.insert(record);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int insertSelective(T record){
        return this.mapper.insertSelective(record);
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含`id`属性并且必须为自增列
     *
     * @param recordList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int insertList(List<? extends T> recordList){
        return this.mapper.insertList(recordList);
    }

    /**
     * 插入数据，限制为实体包含`id`属性并且必须为自增列，实体配置的主键策略无效
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int insertUseGeneratedKeys(T record){
        return this.mapper.insertUseGeneratedKeys(record);
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int update(T record){
        return this.mapper.updateByPrimaryKey(record);
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int updateSelective(T record){
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据Example条件更新实体`record`包含的全部属性，null值会被更新
     *
     * @param record
     * @param example
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int updateByExample(T record, Object example){
        return this.mapper.updateByExample(record,example);
    }

    /**
     * 根据Example条件更新实体`record`包含的不是null的属性值
     *
     * @param record
     * @param example
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int updateByExampleSelective(T record, Object example){
        return this.mapper.updateByExampleSelective(record,example);
    }

    /**
     * 根据主键进行删除
     *
     * @param key
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int deleteByPrimaryKey(Object key){
        return this.mapper.deleteByPrimaryKey(key);
    }

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param record
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int delete(T record){
        return this.mapper.delete(record);
    }

    /**
     * 根据Example条件删除数据
     *
     * @param example
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @DataSource(dataSourceType = EnumDataSource.writeDataSource)
    public int deleteByExample(Object example){
        return this.mapper.deleteByExample(example);
    }

    /**
     * 根据主键字段查询总数，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public boolean existsWithPrimaryKey(Object key){
        return this.mapper.existsWithPrimaryKey(key);
    }

    /**
     * 查询全部结果
     *
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public List<T> selectAll(){
        return this.mapper.selectAll();
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public T selectByPrimaryKey(Object key){
        return this.mapper.selectByPrimaryKey(key);
    }

    /**
     * 根据实体中的属性查询总数，查询条件使用等号
     *
     * @param record
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public int selectCount(T record){
        return this.mapper.selectCount(record);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public List<T> select(T record){
        return this.mapper.select(record);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param record
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public T selectOne(T record){
        return this.mapper.selectOne(record);
    }

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public List<T> selectByExample(Object example){
        return this.mapper.selectByExample(example);
    }

    /**
     * 根据Example条件进行查询总数
     *
     * @param example
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public int selectCountByExample(Object example){
        return this.mapper.selectCountByExample(example);
    }

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public T selectOneByExample(Object example){
        return this.mapper.selectOneByExample(example);
    }

    /**
     * 根据example条件和RowBounds进行分页查询
     *
     * @param example
     * @param rowBounds
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds){
        return this.mapper.selectByExampleAndRowBounds(example,rowBounds);
    }

    /**
     * 根据实体属性和RowBounds进行分页查询
     *
     * @param record
     * @param rowBounds
     * @return
     */
    @DataSource(dataSourceType = EnumDataSource.readDataSource)
    public List<T> selectByRowBounds(T record, RowBounds rowBounds){
        return this.mapper.selectByRowBounds(record,rowBounds);
    }

}

