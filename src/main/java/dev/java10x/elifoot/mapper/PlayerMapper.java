package dev.java10x.elifoot.mapper;

import dev.java10x.elifoot.controller.request.CreatePlayerRequest;
import dev.java10x.elifoot.controller.response.PlayerDetailResponse;
import dev.java10x.elifoot.controller.response.PlayerResponse;
import dev.java10x.elifoot.entity.Player;
import dev.java10x.elifoot.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(source = "position", target = "position", qualifiedByName = "enumToString")
    PlayerResponse toResponse(Player player);

    @Mapping(source = "position", target = "position", qualifiedByName = "enumToString")
    PlayerDetailResponse toDetailResponse(Player player);

    @Mapping(target = "club.id", source = "clubId")
    Player toEntity(CreatePlayerRequest request);

    @Named("enumToString")
    default String mapPositionToString(Position position) {
        return position != null ? position.getLabel() : null;
    }
}
