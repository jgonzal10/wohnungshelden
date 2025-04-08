package de.wohnungshelden.applications.applications;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class ApplicationRepository {
    private final List<Application> applications = List.of(
            new Application("email1", "Jennifer", "Gonzalez",1L),
            new Application("email2", "Julian", "Garzon",2L));
    public List<Application> findAll() {
        return applications;
    }

    public Optional<Application> findByPropertyId(Long propertyId) {
        return applications.stream().filter(application -> application.propertyId() == propertyId).findAny();
    }
}
