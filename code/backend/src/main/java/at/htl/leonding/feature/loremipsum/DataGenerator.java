package at.htl.leonding.feature.loremipsum;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import at.htl.leonding.feature.person.PersonDataGenerator;
import at.htl.leonding.feature.school.SchoolDataGenerator;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DataGenerator {

    @ConfigProperty(name="quarkus.hibernate-orm.database.generation")
    String generationStrategy;

    @Inject
    PersonDataGenerator personDataGenerator;
    @Inject
    SchoolDataGenerator schoolDataGenerator;

    @Transactional
    void init(@Observes StartupEvent startupEvent) {
        if ("drop-and-create".equals(generationStrategy)) {
            generateDummyData();
        }
    }
    void generateDummyData() {
        var persons = personDataGenerator.generateDummyData();
        schoolDataGenerator.generateDummySchool(persons);
    }
}
