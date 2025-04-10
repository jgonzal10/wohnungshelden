package de.wohnungshelden.applications.applications;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ApplicationController.class)
public class ApplicationControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ApplicationService applicationService;

    @Test
    @DisplayName("GET /api/all should return list of applications")
    void all() throws Exception {
        Application application1 = new Application();
        application1.setId(1);
        application1.setEmail("test@email.com");
        application1.setFirstName("Jane");
        application1.setLastName("Test");

        Application application2 = new Application();
        application2.setId(2);
        application2.setEmail("another@email.com");
        application2.setFirstName("Jennifer");
        application2.setLastName("Gonzalez");

        List<Application> mockList = Arrays.asList(application1, application2);

        when(applicationService.all()).thenReturn(mockList);

        mockMvc.perform(get("/api/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email", is("test@email.com")))
                .andExpect(jsonPath("$[1].firstName", is("Jennifer")));
    }

    @Test
    @DisplayName("POST /api/portal create an application")
    void createFromPortal_WithValidRequest_ShouldReturnCreated() throws Exception {
        Application application1 = new Application();
        application1.setId(1);
        application1.setEmail("suarez@email.com");
        application1.setFirstName("Jane");
        application1.setLastName("Test");
        when(applicationService.createApplication(any(Application.class))).thenReturn(application1);

        mockMvc.perform(post("/api/portal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
    "email":"suarez@email.com",
    "lastName":"suarez",
    "creationSource": "PORTAL",
    "salutation":"MR",
    "numberOfPersons":2,
    "wbsPresent":true,
    "pets":"No pets allowed",
    "applicantComment":"This is an applicant comment"
                    }
                   \s"""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("suarez@email.com"));

        verify(applicationService).createApplication(any(Application.class));
    }


    @Test
    @DisplayName("POST /api/portal failed to create an application due to missing required parameter")
    void createFromPortal_WithInvalidRequest_ShouldReturnBadRequest() throws Exception {
        // Act & Assert - Missing required fields for PortalValidationGroup
        mockMvc.perform(post("/api/portal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                        "firsName": "Jennifer"
                    }
                    """))
                .andExpect(status().isBadRequest());
    }

}
