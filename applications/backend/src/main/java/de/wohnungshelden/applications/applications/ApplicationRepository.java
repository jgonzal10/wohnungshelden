package de.wohnungshelden.applications.applications;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class ApplicationRepository {
    private static final AtomicLong ID_GENERATOR = new AtomicLong();
        private final Map<Long, Application> applicationByPropertyId = new ConcurrentHashMap<>();
    private final List<Application> applications = List.of(
            new Application("email1", "Jennifer", "Gonzalez", 1L),
            new Application("email2", "Julian", "Garzon", 2L));

    public List<Application> findAll() {
        return applications;
    }

    public Application create(Application application) {
        Long propertyId = ID_GENERATOR.getAndIncrement();

        var saved = new Application(application.email(),application.firstName(),application.lastName(),propertyId);
        applicationByPropertyId.put(propertyId, saved); 
        // TODO save new Application in Database
        return saved;
    }

    public Optional<Application> findByPropertyId(Long propertyId) {
        return applications.stream().filter(application -> application.propertyId() == propertyId).findAny();
    }
}
