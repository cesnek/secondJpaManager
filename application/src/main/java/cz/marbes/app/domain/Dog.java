package cz.marbes.app.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author <a href="mailto:jaroslav.pubal@marbes.cz">Jaroslav Půbal</a>
 */
@Entity
public class Dog {

    @Id
    private Integer id;

}
