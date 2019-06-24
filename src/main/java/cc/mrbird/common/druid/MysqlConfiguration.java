package cc.mrbird.common.druid;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.collections.ArrayStack;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.assertj.core.util.Lists;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"cc.mrbird.system.dao", "cc.mrbird.job.dao"}, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MysqlConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceOne());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        VFS.addImplClass(SpringBootVFS.class);
        bean.setTypeAliasesPackage("cc.mrbird.system.domain,cc.mrbird.job.domain");
        Resource[] jobresources = resolver.getResources("classpath:mapper/job/*.xml");
        Resource[] systemresources = resolver.getResources("classpath:mapper/system/*.xml");
        Resource[] finalresources = new Resource[jobresources.length + systemresources.length];
        System.arraycopy(jobresources, 0, finalresources, 0, jobresources.length);
        System.arraycopy(systemresources, 0, finalresources, jobresources.length, systemresources.length);
        bean.setMapperLocations(finalresources);
        return bean.getObject();
    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSourceOne());
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }


}
