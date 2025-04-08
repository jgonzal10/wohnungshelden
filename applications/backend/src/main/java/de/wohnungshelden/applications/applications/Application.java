package de.wohnungshelden.applications.applications;

// import java.time.LocalDate;

public record Application(        
    String email,
    String firstname,
    String lastName,
    Long propertyId
    // LocalDate creationTimestamp
    ) {
}
