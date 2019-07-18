package cc.mrbird.common.druid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ImpalaJDBCConfiguartion {

    @Bean
    public JdbcTemplate impalaJDBC(@Qualifier("impalaDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
