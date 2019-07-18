package cc.mrbird.common.druid;

import cc.mrbird.web.dao.NewHouseMapper;
import cc.mrbird.web.dao.SecondHouseMapper;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cc.mrbird.web.dao", sqlSessionFactoryRef = "impalaSqlSessionFactory")
public class ImpalaConfiguration {


    @Bean(name = "impalaDataSource")
    @ConfigurationProperties("c3p0")
    public DataSource dataSource() {
        return new ComboPooledDataSource();
    }

    @Bean(name = "impalaSqlSessionFactory")
    public SqlSessionFactory impalaSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources("classpath:mapper/web-impala/*.xml"));
        factoryBean.setTypeAliasesPackage("cc.mrbird.web.domain");
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }

    @Bean(name = "impalaSqlSessionTemplate")
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(impalaSqlSessionFactory());
    }

    @Bean
    public MapperFactoryBean<NewHouseMapper> newHouseMapper() throws Exception {
        MapperFactoryBean<NewHouseMapper> factoryBean = new MapperFactoryBean<>(NewHouseMapper.class);
        factoryBean.setSqlSessionTemplate(sqlSession());
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<SecondHouseMapper> secondHouseMapper() throws Exception {
        MapperFactoryBean<SecondHouseMapper> factoryBean = new MapperFactoryBean<>(SecondHouseMapper.class);
        factoryBean.setSqlSessionTemplate(sqlSession());
        return factoryBean;
    }


    @Bean(name = "impalaJDBC")
    public JdbcTemplate impalaJDBC(@Qualifier("impalaDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
