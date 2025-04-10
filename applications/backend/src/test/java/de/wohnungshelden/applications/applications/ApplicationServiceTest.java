package de.wohnungshelden.applications.applications;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ApplicationServiceTest {

    private ApplicationRepository applicationRepository;
    private ApplicationService applicationService;


    @BeforeEach
    void setUp() {
        applicationRepository = mock(ApplicationRepository.class);
        applicationService = new ApplicationService(applicationRepository);
    }
    @Test
    @DisplayName("Should return list of applications from repository")
    void testAllApplications() {
        Application app1 = new Application();
        app1.setId(1);
        app1.setEmail("test1@example.com");
        app1.setFirstName("John");
        app1.setLastName("Doe");
        app1.setSalutation(ApplicationSalutation.MR);

        Application app2 = new Application();
        app2.setId(2);
        app2.setEmail("test2@example.com");
        app2.setFirstName("Jane");
        app2.setLastName("Smith");
        app2.setSalutation(ApplicationSalutation.MRS);

        List<Application> mockList = Arrays.asList(app1, app2);

        when(applicationRepository.findAll()).thenReturn(mockList);

        List<Application> result = applicationService.all();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getEmail()).isEqualTo("test1@example.com");
        assertThat(result.get(1).getSalutation()).isEqualTo(ApplicationSalutation.MRS);
        verify(applicationRepository, times(1)).findAll();
    }
}
