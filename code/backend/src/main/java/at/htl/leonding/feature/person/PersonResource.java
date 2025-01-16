package at.htl.leonding.feature.person;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
    @Inject
    PersonRepository personRepository;
    @Inject
    PersonMapper personMapper;

    @GET
    public List<Person> all() {
        var persons = personRepository
            .findAll()
            .list()
            .stream()
            .map(personMapper::toResource)
            .toList();
        return persons;
    }
}


