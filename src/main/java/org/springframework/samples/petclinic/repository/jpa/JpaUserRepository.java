package org.springframework.samples.petclinic.repository.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.springframework.samples.petclinic.model.User;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class JpaUserRepository implements PanacheRepositoryBase<User,Integer> {

    @Inject
    EntityManager em;

    public void save(User user)  {
        if (this.em.find(User.class, user.getUsername()) == null) {
            persist(user);
        } else {
            this.em.merge(user);
        }
    }
}
