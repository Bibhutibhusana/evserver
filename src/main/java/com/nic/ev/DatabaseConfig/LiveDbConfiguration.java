//package com.nic.ev.DatabaseConfig;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.nic.ev.model.UserLogin;
//
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories()
//public class LiveDbConfiguration {
//
//	@Primary
//	@Bean
//	@ConfigurationProperties("spring.datasourceLivedb")
//	public DataSourceProperties liveDsProperties() {
//		return new DataSourceProperties();
//	}
//	
//	@Primary
//	@Bean
//	public DataSource liveDs(@Qualifier("liveDsProperties") DataSourceProperties liveDsProperties) {
//		return liveDsProperties.initializeDataSourceBuilder().build();
//	}
//	
//	@Bean
//	public LocalContainerEntityManagerFactoryBean liveContainerEmFactory(@Qualifier("liveDs") DataSource liveDs, EntityManagerFactoryBuilder builder) {
//		return builder.dataSource(liveDs).packages(UserLogin.class).build();
//	}
//	
//	@Primary
//	@Bean
//	public PlatformTransactionManager liveDsTransactionManager(EntityManagerFactory factory) {
//		return new JpaTransactionManager(factory);
//	}
//}
