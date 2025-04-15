package dev.java10x.elifoot.controller;

import dev.java10x.elifoot.controller.request.CreateStadiumRequest;
import dev.java10x.elifoot.repository.StadiumRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StadiumControllerTest extends BaseIntegrationTest {

    @Autowired
    StadiumRepository stadiumRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Caso seja necessário antes de cada teste limpar a base para o teste seguinte
        // stadiumRepository.deleteAll();
    }

    @WithMockUser(authorities = {"SCOPE_stadium:write"})
    @Test
    @DisplayName("Should create a stadium")
    void shouldCreateStadium() throws Exception {
        CreateStadiumRequest request = new CreateStadiumRequest("Novo estadio", "Pelotas", 60000, "http://img.com/pelotas.jpg");

        mockMvc.perform(post("/stadiums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Novo estadio"))
                .andExpect(jsonPath("$.city").value("Pelotas"))
                .andExpect(jsonPath("$.capacity").value(60000))
                .andExpect(jsonPath("$.urlImg").value("http://img.com/pelotas.jpg"));
    }

    @WithMockUser(authorities = {"SCOPE_stadium:read"})
    @Test
    @DisplayName("Should list stadiums")
    void shouldListStadium() throws Exception {
        mockMvc.perform(get("/stadiums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content").isNotEmpty())
                .andExpect(jsonPath("$.content.length()").value(Matchers.greaterThan(1)));
    }

    @WithMockUser(authorities = {"SCOPE_stadium:read"})
    @Test
    @DisplayName("Should return 403 when user lacks write scope")
    void shouldReturnForbiddenWhenUserLacksWriteScope() throws Exception {
        CreateStadiumRequest request = new CreateStadiumRequest("Novo estadio", "Pelotas", 60000, "http://img.com/pelotas.jpg");

        mockMvc.perform(post("/stadiums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(authorities = {"SCOPE_stadium:write"})
    @Test
    @DisplayName("Should return 400 when request is invalid")
    void shouldReturnBadRequestWhenRequestIsInvalid() throws Exception {
        CreateStadiumRequest request = new CreateStadiumRequest(
                null,
                null,
                40000,
                "http://img.com/centenario.jpg"
        );

        mockMvc.perform(post("/stadiums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.city").exists())
                .andExpect(jsonPath("$.errors.name").exists());
    }
}