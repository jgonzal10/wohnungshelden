package de.wohnungshelden.applications.applications;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
public class Application{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Fields set via UI
    @Column(nullable = false)
    @NotBlank(groups = {UiValidationGroup.class, PortalValidationGroup.class})
    private String email;
    @Column
    private ApplicationSalutation salutation; // ENUM MR, MRS

    @NotBlank(groups = {UiValidationGroup.class})
    @Column
    private String firstName;
    @NotBlank(groups = {UiValidationGroup.class, PortalValidationGroup.class})
    @Column(nullable = false)
    private String lastName;
    @Column
    private String userComment;


    @Column
    private Integer numberOfPersons;
    @Column
    private Boolean wbsPresent;
    @Column
    private LocalDate earliestMoveInDate;
    @Column
    private String pets; 
    @Enumerated(EnumType.STRING)
    @Column
    private ApplicationStatus status = ApplicationStatus.CREATED; // CREATED, INVITED , DECLINED
    @Column
    private String applicantComment; 

    @Column
    private ApplicationCreationSource creationSource;
    @Column(nullable = false)
    Long propertyId= 1L; // Just for the demo I will consider One Property
    @CreationTimestamp
    private LocalDate creationTimestamp;


    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalutation(ApplicationSalutation salutation) {
        this.salutation = salutation;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public void setWbsPresent(Boolean wbsPresent) {
        this.wbsPresent = wbsPresent;
    }

    public void setEarliestMoveInDate(LocalDate earliestMoveInDate) {
        this.earliestMoveInDate = earliestMoveInDate;
    }

    public void setPets(String pets) {
        this.pets = pets;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public void setApplicantComment(String applicantComment) {
        this.applicantComment = applicantComment;
    }

    public void setCreationSource(ApplicationCreationSource creationSource) {
        this.creationSource = creationSource;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

}
