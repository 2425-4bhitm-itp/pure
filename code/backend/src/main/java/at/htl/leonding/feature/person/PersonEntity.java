package at.htl.leonding.feature.person;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;    
    String firstname;

    @Column(length=128, nullable = false, unique = true)
    String lastname;
}

@ApplicationScoped
class PersonRepository implements PanacheRepository<PersonEntity> {
}