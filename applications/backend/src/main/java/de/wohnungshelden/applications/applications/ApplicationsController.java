package de.wohnungshelden.applications.applications;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/applications")
public class ApplicationsController {
    private final ApplicationRepository applicationRepository;

    public ApplicationsController(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    @GetMapping(path ="/")
    public List<Application> getApplications() {
        return applicationRepository.findAll();
    }

    @GetMapping(path ="/{id}")
    public Application getApplicationByPropertyId(@PathVariable("id") Long propertyId) {
        return applicationRepository.findByPropertyId(propertyId).orElseThrow(() -> new NoSuchElementException("Application with propertyId " + propertyId + " not found"));
    }

}
