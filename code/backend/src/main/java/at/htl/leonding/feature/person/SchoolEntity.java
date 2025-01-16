package at.htl.leonding.feature.person;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
class SchoolEntity {
    @Id
    public Long id;

    public String schoolname;

    @OneToMany
    @JoinColumn(name="school_id")
    public List<PersonEntity> persons;
}
