package cz.marbes.app.service;

import cz.marbes.app.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:jaroslav.pubal@marbes.cz">Jaroslav Půbal</a>
 */
@Service
public class DogService {

    @Autowired//do not respect @Primary on cz.marbes.app.PrimaryJpaConfiguration.entityManagerFactory()
    private EntityManager entityManager;

    @PersistenceContext
    //how can I say "Primary" in cz.marbes.app.PrimaryJpaConfiguration.entityManagerFactory()?
    //I cant change all @PersistenceContext(unitName = ) in existing application
    private EntityManager entityManager2;

    @Autowired
    private DogRepository repository;


    @PostConstruct
    public void init() {
        System.out.println("APPLICATION OK! Count is " + repository.count());
    }
}
