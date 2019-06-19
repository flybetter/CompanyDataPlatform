package cc.mrbird.common.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
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
    @Value("${c3p0.DriverClass}")
    private String driverClass;

    @Value("${c3p0.initialPoolSize}")
    private Integer initialPoolSize;

    @Value("${c3p0.maxIdleTime}")
    private Integer maxIdleTime;

    @Value("${c3p0.JdbcUrl}")
    private String jdbcUrl;

    @Value("${c3p0.AcquireIncrement}")
    private Integer acquireIncrement;

    @Value("${c3p0.MinPoolSize}")
    private Integer minPoolSize;

    @Value("${c3p0.MaxPoolSize}")
    private Integer maxPoolSize;

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "baseDataSource")
    public DataSource dataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(driverClass);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(jdbcUrl);
        cpds.setInitialPoolSize(initialPoolSize);
        cpds.setMaxIdleTime(maxIdleTime);
        cpds.setMinPoolSize(minPoolSize);
        cpds.setAcquireIncrement(acquireIncrement);
        cpds.setMaxPoolSize(minPoolSize);
        return cpds;
    }

    @Bean(name = "secondaryJdbcTemplate")
    public JdbcTemplate secondaryTemplate(@Qualifier("baseDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
