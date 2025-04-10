package de.wohnungshelden.applications.applications;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(nullable = false)

    // Fields set via UI
    @NotBlank(groups = {UiValidationGroup.class, PortalValidationGroup.class})
    private String email;
    @Column
    private String salutation; // ENUM MR, MRS

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
    private LocalDate erliestMoveInDate;
    @Column
    private String pets; 
    @Column
    private String status = "CREATED"; // CREATED, INVITED , DECLINED
    @Column
    private String applicantComment; 

    @Column
    private String creationSource; // ENUM
    @Column(nullable = false)
    Long propertyId= 1L; // Just for the demo I will consider One Property
    @CreationTimestamp
    private LocalDate creationTimestamp;

}
