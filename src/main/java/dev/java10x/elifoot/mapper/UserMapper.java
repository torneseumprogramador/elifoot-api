package dev.java10x.elifoot.mapper;

import dev.java10x.elifoot.controller.request.CreateUserRequest;
import dev.java10x.elifoot.controller.response.UserResponse;
import dev.java10x.elifoot.entity.Scopes;
import dev.java10x.elifoot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "scopes", source = "scopes", qualifiedByName = "mapScopeIdsToScopeEntities")
    User toEntity(CreateUserRequest request);

    @Mapping(target = "scopes", source = "scopes", qualifiedByName = "mapScopeEntitiesToStringScopes")
    UserResponse toResponse(User user);

    @Named("mapScopeIdsToScopeEntities")
    default List<Scopes> mapScopeIdsToScopeEntities(List<Long> scopeIds) {
        if (scopeIds == null) return Collections.emptyList();

        return scopeIds.stream()
                .map(id -> Scopes.builder().id(id).build())
                .toList();
    }

    @Named("mapScopeEntitiesToStringScopes")
    default List<String> mapScopeEntitiesToStringScopes(List<Scopes> scopes) {
        if (scopes == null) return Collections.emptyList();

        return scopes.stream()
                .map(Scopes::getName)
                .toList();
    }
}
