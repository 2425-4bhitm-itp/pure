package at.htl.leonding.feature.person;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PersonDataGenerator {
    @Inject
    PersonMapper personMapper;
    @Inject PersonRepository personRepository;
    
    public List<PersonEntity> generateDummyData() {
        var persons = List.of(
            new Person(null, "John", "Doe"),
            new Person(null, "Jane", "Roe"),
            new Person(null, "Joe", "Sixpack")
        );
        var personEntities = 
            persons
            .stream()
            .map(personMapper::fromResource)
            .map(personRepository.getEntityManager()::merge)
            .toList();
        ;
        return personEntities;
    }
}
