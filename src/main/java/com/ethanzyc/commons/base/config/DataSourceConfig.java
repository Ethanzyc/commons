package com.ethanzyc.commons.base.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.ethanzyc.commons.base.filter.PrintSqlFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ethan
 * @date 2019/8/26 08:17
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("dev")
    public DataSource dataSourceOne(){

        DruidDataSourceBuilder builder = DruidDataSourceBuilder.create();
        DruidDataSource dataSource = builder.build();

        /**
         * 增加一个sql过滤器，显示 sql 打印，只在开发环境生效
         *
         * 打印如下
         * sql 耗时: 2153ms
         * SELECT * FROM t_test LIMIT 7000000, 1000
         *
         */

        PrintSqlFilter printSqlFilter = new PrintSqlFilter();
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(printSqlFilter);
        dataSource.setProxyFilters(filters);

        return dataSource;
    }

}
