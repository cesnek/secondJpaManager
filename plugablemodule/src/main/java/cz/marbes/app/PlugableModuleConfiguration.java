package cz.marbes.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * <b>Small independen module.</b>
 * <b>How to configure second datasource/entity manager without modify main application?</b>
 *
 * <b>Finally I did this with soma hacks.</b>
 *
 * @author <a href="mailto:jaroslav.pubal@marbes.cz">Jaroslav Půbal</a>
 */
@Configuration
@EnableJpaRepositories(
    entityManagerFactoryRef = PlugableModuleConfiguration.ENTITY_MANAGER_NAME,
    basePackages = { PlugableModuleConfiguration.REPOSITORY_PACKAGE })
public class PlugableModuleConfiguration {

    public static final String ENTITY_PACKAGE = "cz.marbes.module.domain";
    public static final String REPOSITORY_PACKAGE = "cz.marbes.module.repository";
    public static final String ENTITY_MANAGER_NAME = "navEntityManagerFactory";
    public static final String DATASOURCE_NAME = "navDataSource";
    public static final String PERSISTENCE_UNIT_NAME = "nav";


    @Bean(name = DATASOURCE_NAME)
    @ConfigurationProperties(prefix="module.datasource")
    public DataSource navDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = ENTITY_MANAGER_NAME)
    public LocalContainerEntityManagerFactoryBean navEntityManagerFactory(
        JpaVendorAdapter jpaVendorAdapter,
        @Qualifier(DATASOURCE_NAME) DataSource navDataSource) {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setDataSource(navDataSource);
        entityManagerFactoryBean.setPackagesToScan(ENTITY_PACKAGE);
        entityManagerFactoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);

        return entityManagerFactoryBean;

    }

    @Bean(name = "secondTransactionManager")
    public JpaTransactionManager transactionManager(
        @Qualifier(ENTITY_MANAGER_NAME) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
