package cz.marbes.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cz.marbes.app.repository.DogRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:jaroslav.pubal@marbes.cz">Jaroslav Půbal</a>
 */
@Service
public class DogService {

    @Autowired
    private EntityManager entityManager;

    @PersistenceContext
    private EntityManager entityManager2;

    @Autowired
    private DogRepository repository;


    @PostConstruct
    public void init() {
        System.out.println("APPLICATION OK! Count is " + repository.count());
    }
}
