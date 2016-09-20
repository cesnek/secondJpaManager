package cz.marbes.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.List;

/**
 * OK, module with own datasource is shuting off autoconfig.
 *
 * I do config myself.
 *
 *
 * @author <a href="mailto:jaroslav.pubal@marbes.cz">Jaroslav Půbal</a>
 */
@Configuration
public class PrimaryJpaConfiguration {

    public static final String PRIMARY_PERSISTENCE_UNIT_NAME = "default";

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }



    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("dataSource") DataSource dataSource) {
        return builder
            .dataSource(dataSource)
            .persistenceUnit(PRIMARY_PERSISTENCE_UNIT_NAME)
            .build();
    }

    @Bean
    public PlatformTransactionManager mainTransactionManager(
        @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    /**
     * Globální transactionManager pro všechny
     */
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(List<PlatformTransactionManager> managers) {
        return new ChainedTransactionManager(managers.toArray(new PlatformTransactionManager[managers.size()]));
    }

}
