package de.wohnungshelden.applications.applications;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@Slf4j
@RequestMapping(path = "/applications")
public class ApplicationsController {
    private final ApplicationService applicationService;

    public ApplicationsController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<Application> create(@RequestBody @Valid Application application) {
        Application createdApplication = applicationService.createApplication(application);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public List<Application> getApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping(path = "/{id}")
    public Application getApplicationByPropertyId(@PathVariable("id") Long propertyId) {
        return applicationService.getApplicationByPropertyId(propertyId);
    }

    @GetMapping("/search")
    public List<Application> searchApplications(@RequestParam(required = false) String keywords) {
        return applicationService.searchApplications(keywords);
    }

}
