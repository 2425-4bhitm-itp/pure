package at.htl.leonding.feature.school;

import java.util.List;

import at.htl.leonding.feature.person.PersonEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SchoolDataGenerator {
    @Inject
    SchoolMapper schoolMapper;
    @Inject
    SchoolRepository schoolRepository;

    public SchoolEntity generateDummySchool(List<PersonEntity> persons) {
        var school = new School(null, "HTL-Leonding");
        var entity = schoolMapper.fromResource(school);
        entity.persons = persons;
        schoolRepository.persistAndFlush(entity);
        return entity;
    }
}
