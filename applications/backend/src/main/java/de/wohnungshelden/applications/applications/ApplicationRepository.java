package de.wohnungshelden.applications.applications;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByPropertyIdOrderByCreationTimestampDesc(Long propertyId);

    Optional<Application> findByIdAndPropertyId(int id, Long propertyId);

    @Query("SELECT a FROM Application a WHERE " +
            "LOWER(CONCAT(a.email, ' ', a.firstName, ' ', a.lastName)) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Application> searchByKeyword(@Param("keyword") String keyword);

    @Query("SELECT a FROM Application a WHERE " +
            "a.propertyId = :propertyId AND " +
            "(LOWER(a.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Application> searchByPropertyIdAndKeyword2(@Param("propertyId") Long propertyId,
            @Param("keyword") String keyword);

    @Query("SELECT a FROM Application a WHERE " +
            "a.propertyId = :propertyId AND " +
            "(LOWER(a.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.salutation) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.userComment) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(CAST(a.numberOfPersons AS string)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(CAST(a.wbsPresent AS string)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(CAST(a.earliestMoveInDate AS string)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(CAST(a.pets AS string)) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.applicantComment) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.creationSource) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<Application> searchByPropertyIdAndKeyword(
            @Param("propertyId") Long propertyId,
            @Param("keyword") String keyword);

    @Modifying
    @Transactional
    @Query("UPDATE Application a SET a.status = :status " +
            "WHERE a.id = :id AND a.propertyId = :propertyId")
    int updateStatus(
            @Param("id") int id,
            @Param("propertyId") Long propertyId,
            @Param("status") ApplicationStatus status);

    List<Application> findAllBy();

}
