package de.wohnungshelden.applications.applications;

// import java.time.LocalDate;

public record Application(        
    String email,
    String firstName,
    String lastName,
    Long propertyId
    // LocalDate creationTimestamp
    ) {
}
