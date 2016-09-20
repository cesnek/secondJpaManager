package cz.marbes.module.repository;

import cz.marbes.module.domain.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="mailto:jaroslav.pubal@marbes.cz">Jaroslav Půbal</a>
 */
public interface CatRepository extends JpaRepository<Cat, Integer> {
}
