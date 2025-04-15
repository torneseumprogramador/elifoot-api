package dev.java10x.elifoot.service;

import dev.java10x.elifoot.controller.request.CreateClubRequest;
import dev.java10x.elifoot.controller.response.ClubResponse;
import dev.java10x.elifoot.entity.Club;
import dev.java10x.elifoot.entity.Stadium;
import dev.java10x.elifoot.mapper.ClubMapper;
import dev.java10x.elifoot.repository.ClubRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateClubServiceTest {

    @InjectMocks
    CreateClubService createClubService;
    @Mock
    ClubRepository clubRepository;
    @Mock
    FindStadiumService findStadiumService;
    @Mock
    ClubMapper clubMapper;

    private Stadium stadium;
    private Club club;

    @BeforeEach
    void setUp() {
        stadium = new Stadium(1L, "Estádio Central", "Cidade A", 50000, "url_imagem");
        club = new Club(1L, "Clube A", LocalDate.of(1980, 10, 10), "url_imagem", stadium, List.of());
    }

    @Test
    @DisplayName("Should save new club with success")
    void shouldSaveNewClub() {
        // Given
        CreateClubRequest request = new CreateClubRequest();
        request.setName("Clube A");
        request.setFounded(LocalDate.of(1980, 10, 10));
        request.setUrlImg("url_imagem");
        request.setStadiumId(1L);

        ClubResponse clubResponse = new ClubResponse();
        clubResponse.setId(1L);
        clubResponse.setName("Clube A");
        clubResponse.setFounded(LocalDate.of(1980, 10, 10));
        clubResponse.setUrlImg("url_imagem");

        Mockito.when(clubMapper.toEntity(request)).thenReturn(club);
        Mockito.when(findStadiumService.findById(1L)).thenReturn(stadium);
        Mockito.when(clubRepository.save(club)).thenReturn(club);
        Mockito.when(clubMapper.toResponse(club)).thenReturn(clubResponse);
        // When
        ClubResponse response = createClubService.execute(request);

        // Then
        Mockito.verify(clubMapper).toEntity(request);
        Mockito.verify(findStadiumService).findById(1L);
        Mockito.verify(clubRepository).save(club);
        Mockito.verify(clubMapper).toResponse(club);
        assertNotNull(response);
    }
}