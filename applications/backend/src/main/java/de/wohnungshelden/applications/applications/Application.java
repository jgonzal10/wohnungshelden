package de.wohnungshelden.applications.applications;

import java.time.LocalDate;

import org.hibernate.annotations.GenerationTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import java.time.LocalDate;
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
    private String email;
    @Column
    private String salutation; // ENUM

    @Column
    private String firstName;
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
    private String status; // CREATED
    @Column
    private String applicantComment; 

    @Column
    private String creationSource; // ENUM
    @Column(columnDefinition = "serial", updatable = false, insertable = false)
    Long propertyId;
    @Column
    private LocalDate creationTimestamp;

}
