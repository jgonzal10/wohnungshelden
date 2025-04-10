package de.wohnungshelden.applications.applications;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ApplicationService {
    
    private final ApplicationRepository applicationRepository;
    
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> getAllApplicationsByProperty(Long propertyId) {
        return applicationRepository.findByPropertyIdOrderByCreationTimestampDesc(propertyId);
    }
    
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }
    
    public Application getApplicationByIdAndPropertyId(int applicationId,Long propertyId) {
        return applicationRepository.findByIdAndPropertyId(applicationId,propertyId).orElse(null);
    }

    public List<Application> searchApplications(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return applicationRepository.findAll();
        }
        return applicationRepository.searchByKeyword(keyword.trim());
    }


    public List<Application> all() {
        return applicationRepository.findAll();
    }
}