package at.htl.leonding.feature.school;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
class SchoolMapper {
    public School toResource(SchoolEntity school) {
        return new School(school.id, school.name);
    }
    public SchoolEntity fromResource(School school) {
        var entity = new SchoolEntity();
        entity.id = school.id();
        entity.name = school.name();
        return entity;
    }
}
