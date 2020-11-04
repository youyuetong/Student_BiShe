package edu.job.config.database;

/**
 * 基于规范与开发便捷的角度,建议用枚举,建议与datasource bean name一致
 * @date 2019-02-18 17:54:58
 */
public enum EnumDataSource {

    /**
     * 写数据源
     */
    writeDataSource,

    /**
     * 读数据源
     */
    readDataSource;

}
