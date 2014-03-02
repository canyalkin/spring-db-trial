package spring.config;

import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.jpa.PersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages="spring.db.dao")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class ApplicationConfig {
	
	@Autowired
	Environment environment;
	
	
	 @Bean( name="ds",destroyMethod = "close")
	  public DataSource dataSource() {
	    final ComboPooledDataSource dataSource = new ComboPooledDataSource();
	    try {
			dataSource.setDriverClass(environment.getProperty("db.driver"));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    dataSource.setJdbcUrl(environment.getProperty("db.url"));
	    dataSource.setUser(environment.getProperty("db.user"));
	    dataSource.setPassword(environment.getProperty("db.passwd"));
	    dataSource.setMinPoolSize(5);
	    dataSource.setMaxPoolSize(20);
	    dataSource.setIdleConnectionTestPeriod(300);
	    return dataSource;
	  }
	 
	 @Bean
	 public JdbcTemplate getSimpleJdbcTemplate(@Qualifier("ds") DataSource dataSources){
		 JdbcTemplate simpleJdbcTemplate=new JdbcTemplate(dataSources);
		 return simpleJdbcTemplate;
	 }
	 
	
	 @Bean(name="jpaAdapter")
	 public EclipseLinkJpaVendorAdapter getEclipseLinkJpaVendorAdapter(){
		 EclipseLinkJpaVendorAdapter eclipseLinkJpaVendorAdapter=new EclipseLinkJpaVendorAdapter();
		 eclipseLinkJpaVendorAdapter.setDatabase(Database.MYSQL);
		 eclipseLinkJpaVendorAdapter.setShowSql(true);
		 eclipseLinkJpaVendorAdapter.setGenerateDdl(true);
		 //eclipseLinkJpaVendorAdapter.setDatabasePlatform("oracle.jdbc.driver.OracleDriver");
		 
		 return eclipseLinkJpaVendorAdapter;
	 }
	 
	 @Bean(name="emFactory")
	 public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean(
			 @Qualifier("ds") DataSource dataSources,
			 @Qualifier("jpaAdapter") JpaVendorAdapter jpaVendorAdapter){
		 
		 LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
		 bean.setPackagesToScan("spring.jpa.model");
		 bean.setPersistenceProviderClass(PersistenceProvider.class);
		 bean.setDataSource(dataSources);
		 bean.setJpaVendorAdapter(jpaVendorAdapter);
		 bean.setPersistenceUnitName("pu");
		 Map<String, String> jpaProperties=new HashMap<String, String>();
		 jpaProperties.put("eclipselink.weaving", "false");
		 bean.setJpaPropertyMap(jpaProperties);
		 bean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		
		 
		 return bean;
	 }
	 
	 @Bean
	 public PlatformTransactionManager getJpaTransactionManager( @Qualifier("emFactory") LocalContainerEntityManagerFactoryBean aEmFactory ){
		 JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
		 
		 jpaTransactionManager.setEntityManagerFactory(aEmFactory.getObject());
		 
		 return jpaTransactionManager;
	 }
	 
	
	
}
