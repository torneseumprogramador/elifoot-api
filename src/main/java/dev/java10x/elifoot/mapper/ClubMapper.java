package dev.java10x.elifoot.mapper;

import dev.java10x.elifoot.controller.request.CreateClubRequest;
import dev.java10x.elifoot.controller.response.ClubDetailResponse;
import dev.java10x.elifoot.controller.response.ClubResponse;
import dev.java10x.elifoot.entity.Club;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClubMapper {

    ClubResponse toResponse(Club club);

    ClubDetailResponse toDetailResponse(Club club);

    @Mapping(target = "stadium.id", source = "stadiumId")
    Club toEntity(CreateClubRequest request);

}
