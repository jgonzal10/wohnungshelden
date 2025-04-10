package de.wohnungshelden.applications.applications;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByPropertyIdOrderByCreationTimestampDesc(Long propertyId);

    Optional<Application> findByIdAndPropertyId(int id,Long propertyId);

    @Query("SELECT a FROM Application a WHERE " +
            "LOWER(CONCAT(a.email, ' ', a.firstName, ' ', a.lastName)) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Application> searchByKeyword(@Param("keyword") String keyword);


List<Application> findAllBy();

}
