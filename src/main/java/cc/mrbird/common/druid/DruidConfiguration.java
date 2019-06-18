package cc.mrbird.common.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


@Configuration
public class DruidConfiguration {

    @Primary
    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties("spring.datasource.druid.mysql")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "impalaDataSource")
    @ConfigurationProperties("spring.datasource.druid.impala")
    public DataSource dataSourceTwo() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryTemplate(@Qualifier("impalaDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
