package pl.coztymit.exchange.identity.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.coztymit.exchange.identity.application.CreateIdentityStatus;
import pl.coztymit.exchange.identity.application.IdentityApplicationService;
import pl.coztymit.exchange.identity.application.IdentityResponse;
import pl.coztymit.exchange.kernel.IdentityId;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class IdentityControllerTest {

    @Mock
    private IdentityApplicationService identityApplicationService;

    @InjectMocks
    private IdentityController identityController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(identityController).build();
    }

    @Test
    public void createIdentity() throws Exception {

        CreateIdentityStatus createIdentityStatus = CreateIdentityStatus.prepareExistsStatus();
        given(identityApplicationService.createIdentity(any())).willReturn(createIdentityStatus);

        mockMvc.perform(post("/identity")
                        .contentType("application/json")
                        .content("{\"pesel\": \"90090503657\", \"firstName\": \"Jan\", \"surname\": \"Kowalski\", \"email\": \"jan.kowalski@example.com\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllIdentityIds() throws Exception {
        IdentityId identityId = new IdentityId(UUID.randomUUID());
        given(identityApplicationService.getAllIdentityIds()).willReturn(Collections.singletonList(identityId));

        mockMvc.perform(get("/identity/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void getIdentity() throws Exception {
        IdentityId identityId = new IdentityId(UUID.randomUUID());
        IdentityResponse identityResponse = new IdentityResponse(identityId, "90090503657", "Jan", "Kowalski", "test@coztymit.pl");

        given(identityApplicationService.getIdentity(any())).willReturn(identityResponse);

        mockMvc.perform(get("/identity/" + identityId.toString()))
                .andExpect(status().isOk());
    }
}