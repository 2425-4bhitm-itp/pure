package at.htl.leonding.feature.person;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class PersonMapper {
    public Person toResource(PersonEntity person) {
        return new Person(person.id, person.firstname, person.lastname);
    }
    public PersonEntity fromResource(Person person) {
        final var entity = new PersonEntity();
        entity.id = person.id();
        entity.firstname = person.firstname();
        entity.lastname = person.lastname();
        return entity;
    }
}