package cz.marbes.app.repository;

import cz.marbes.app.domain.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:jaroslav.pubal@marbes.cz">Jaroslav Půbal</a>
 */
public interface DogRepository extends JpaRepository<Dog, Integer> {
}
