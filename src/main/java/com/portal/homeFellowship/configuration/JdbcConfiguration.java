package com.portal.homeFellowship.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import com.zaxxer.hikari.HikariDataSource;



@Configuration
public class JdbcConfiguration {
	final static Logger LOGGER = LoggerFactory.getLogger(JdbcConfiguration.class);
	
	@PostConstruct
    private void startup() {
    	LOGGER.info("***************************APPLICATION CONTEXT STARTED******************************");
    }

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.hf")
	public DataSourceProperties dataSourceProperties(){
		return new DataSourceProperties();
	}

	/*
	 * Configure HikariCP pooled DataSource.
	 */
	@Bean(destroyMethod = "")
	@Primary
	public DataSource dataSource() {
		DataSourceProperties dataSourceProperties = dataSourceProperties();
			final HikariDataSource dataSource = (HikariDataSource) DataSourceBuilder
					.create(dataSourceProperties.getClassLoader())
					.driverClassName(dataSourceProperties.getDriverClassName())
					.url(dataSourceProperties.getUrl())
//					.username(EncryptDecrypt.decrypt(dataSourceProperties.getUsername()))
//					.password(EncryptDecrypt.decrypt(dataSourceProperties.getPassword()))
					.username(dataSourceProperties.getUsername())
					.password(dataSourceProperties.getPassword())
					.type(HikariDataSource.class)
					.build();
			dataSource.addDataSourceProperty( "oracle.jdbc.timezoneAsRegion" , false );
			dataSource.setMaximumPoolSize(50);
			// ++++++++++++++ To Fix connection.isValid() Hikari bug +++++++++++++++++++++
			dataSource.setConnectionTestQuery("select sysdate from dual");
			return dataSource;
	}
	
	
//	  @Bean(destroyMethod = "")
//	  @Primary
//	  public DataSource homeFellowshipDataSource() {
//	    DataSourceProperties dataSourceProperties = dataSourceProperties();
//	    
//	    JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
//	    return dataSourceLookup.getDataSource(dataSourceProperties.getJndiName());
//	  }

	
    @Bean
    @Lazy(true) 
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
    
   

    @PreDestroy
    private static void destroy() {
    	LOGGER.info("**********************Application Destroyed...**********************");
    }

}
