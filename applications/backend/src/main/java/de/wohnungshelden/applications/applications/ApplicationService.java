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
    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplicationsByProperty(Long propertyId) {
        return applicationRepository.findByPropertyIdOrderByCreationTimestampDesc(propertyId);
    }
    
    public Application getApplicationByIdAndPropertyId(int applicationId,Long propertyId) {
        return applicationRepository.findByIdAndPropertyId(applicationId,propertyId).orElse(null);
    }

    public List<Application> searchApplications(Long propertyId,String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return applicationRepository.findAll();
        }
        return applicationRepository.searchByPropertyIdAndKeyword(propertyId,keyword.trim());
    }

    public void updateApplicationStatus(int id, Long propertyId, String statusStr) {
        ApplicationStatus status;
        try {
            status = ApplicationStatus.valueOf(statusStr);
        } catch (IllegalArgumentException e) {
            throw new Error("Invalid Status");
        }

    
        int updated = applicationRepository.updateStatus(id, propertyId, status);
        
        if (updated == 0) {
            throw new Error("Status update failed");
        }
    }

    public List<Application> all() {
        return applicationRepository.findAll();
    }
}