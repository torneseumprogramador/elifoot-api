package dev.java10x.elifoot.mapper;

import dev.java10x.elifoot.controller.request.CreateUserRequest;
import dev.java10x.elifoot.controller.response.UserResponse;
import dev.java10x.elifoot.entity.Scope;
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
    default List<Scope> mapScopeIdsToScopeEntities(List<Long> scopeIds) {
        if (scopeIds == null) return Collections.emptyList();

        return scopeIds.stream()
                .map(id -> Scope.builder().id(id).build())
                .toList();
    }

    @Named("mapScopeEntitiesToStringScopes")
    default List<String> mapScopeEntitiesToStringScopes(List<Scope> scopes) {
        if (scopes == null) return Collections.emptyList();

        return scopes.stream()
                .map(Scope::getName)
                .toList();
    }
}
