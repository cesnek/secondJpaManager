package cz.marbes.module.service;

import cz.marbes.app.PlugableModuleConfiguration;
import cz.marbes.module.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:jaroslav.pubal@marbes.cz">Jaroslav Půbal</a>
 */
@Service
public class CatService {

    //OK, in module I must specify @PersistenceContext(unitName = )
    @PersistenceContext(unitName = PlugableModuleConfiguration.PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    @Autowired
    private CatRepository repository;

    @PostConstruct
    public void init() {
        System.out.println("MODULE OK! Count is " + repository.count());
    }
}
