package at.htl.leonding.feature.school;

import java.util.List;

import at.htl.leonding.feature.person.PersonEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class SchoolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String name;

    @OneToMany
    @JoinColumn(name="school_id")
    List<PersonEntity> persons;
}
@ApplicationScoped
class SchoolRepository implements PanacheRepository<SchoolEntity> {
}