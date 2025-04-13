package dev.java10x.elifoot.mapper;

import dev.java10x.elifoot.controller.request.CreateClubRequest;
import dev.java10x.elifoot.controller.response.ClubDetailResponse;
import dev.java10x.elifoot.controller.response.ClubResponse;
import dev.java10x.elifoot.entity.Club;
import dev.java10x.elifoot.entity.Stadium;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClubMapperTest {

    private final ClubMapper mapper = Mappers.getMapper(ClubMapper.class);

    @Test
    void toResponse() {
        // Given
        Club club = new Club();
        club.setId(1L);
        club.setName("São Paulo FC");
        club.setFounded(LocalDate.of(1935, 1, 1));
        club.setUrlImg("https://www.superstarsoccer.com.br/clubs/spfc.jpg");

        // When
        ClubResponse response = mapper.toResponse(club);

        // Then
        assertNotNull(response);
        assertEquals(club.getId(), response.getId());
        assertEquals(club.getName(), response.getName());
        assertEquals(club.getFounded(), response.getFounded());
        assertEquals(club.getUrlImg(), response.getUrlImg());
    }

    @Test
    void toDetailResponse() {
        // Given
        Club club = new Club();
        club.setId(1L);
        club.setName("São Paulo FC");
        club.setFounded(LocalDate.of(1935, 1, 1));
        club.setUrlImg("https://www.superstarsoccer.com.br/clubs/spfc.jpg");
        club.setStadium(Stadium.builder()
                .id(5L)
                .name("Estádio do Morumbi")
                .city("Morumbi")
                .capacity(35000)
                .urlImg("http://www.superstarsoccer.com.br/clubs/spfc.jpg")
                .build());

        // When
        ClubDetailResponse response = mapper.toDetailResponse(club);

        // Then
        assertNotNull(response);
        assertEquals(club.getId(), response.getId());
        assertEquals(club.getName(), response.getName());
        assertEquals(club.getFounded(), response.getFounded());
        assertEquals(club.getUrlImg(), response.getUrlImg());
        assertEquals(club.getStadium().getId(), response.getStadium().getId());
        assertEquals(club.getStadium().getName(), response.getStadium().getName());
        assertEquals(club.getStadium().getCity(), response.getStadium().getCity());
        assertEquals(club.getStadium().getCapacity(), response.getStadium().getCapacity());
        assertEquals(club.getStadium().getUrlImg(), response.getStadium().getUrlImg());

    }

    @Test
    void toEntity() {
        // Given
        CreateClubRequest request = new CreateClubRequest();
        request.setName("São Paulo FC");
        request.setFounded(LocalDate.of(1935, 1, 1));
        request.setStadiumId(1L);
        request.setUrlImg("https://www.superstarsoccer.com.br/clubs/spfc.jpg");

        // When
        Club club = mapper.toEntity(request);

        // Then
        assertNotNull(club);
        assertEquals(request.getName(), club.getName());
        assertEquals(request.getFounded(), club.getFounded());
        assertEquals(request.getStadiumId(), club.getStadium().getId());
        assertEquals(request.getUrlImg(), club.getUrlImg());
    }
}