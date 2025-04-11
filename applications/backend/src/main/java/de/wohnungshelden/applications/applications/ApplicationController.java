package de.wohnungshelden.applications.applications;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Validated
@RequestMapping(path = "/api")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    //Portal auth
    @PostMapping("/portal/property/{propertyId}")
    public Application createFromPortal(@PathVariable("propertyId") Long propertyId, @Validated(PortalValidationGroup.class) @RequestBody Application application) {
        return applicationService.createApplication(propertyId,application);
    }

    @PostMapping("/ui/property/{propertyId}")
    public Application createFromUi(@PathVariable("propertyId") Long propertyId,@Validated(UiValidationGroup.class) @RequestBody Application application) {
        return applicationService.createApplication(propertyId,application);
    }

    @GetMapping(path = "/property/{propertyId}")
    public List<Application> getApplications(@PathVariable("propertyId") Long propertyId) {
        return applicationService.getAllApplicationsByProperty(propertyId);
    }

    @GetMapping(path = "/property/{propertyId}/application/{applicationId}")
    public Application getApplicationById(@PathVariable("propertyId") Long propertyId,
            @PathVariable("applicationId") int applicationId) {
        return applicationService.getApplicationByIdAndPropertyId(applicationId, propertyId);
    }

    @GetMapping(path ="/property/{propertyId}/search")
    public List<Application> searchApplications(@PathVariable("propertyId") Long propertyId,
            @RequestParam(required = false) String keywords) {
        return applicationService.searchApplications(propertyId, keywords);
    }

    @PutMapping(path ="/property/{propertyId}/application/{applicationId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable("propertyId") Long propertyId,
            @PathVariable("applicationId") int applicationId,
            @RequestParam String status) {

        applicationService.updateApplicationStatus(applicationId, propertyId, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path ="/all")
    public List<Application> all() {
        return applicationService.all();
    }

}
