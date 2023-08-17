package ie.jules.salon.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@TestConfiguration
@Profile("integrationTest")
public class TestConfig {

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			ObjectProvider<JpaVendorAdapter> jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
				new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan("ie.jules.salon.model.entity");
		entityManagerFactoryBean.setPersistenceUnitName("testPU");
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter.getIfAvailable());

		return entityManagerFactoryBean;
	}
}
