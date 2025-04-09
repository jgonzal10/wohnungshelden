package de.wohnungshelden.applications.applications;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findAll();
    Optional<Application> findByPropertyId(Long propertyId);
}
