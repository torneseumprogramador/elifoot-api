package dev.java10x.elifoot.mapper;

import dev.java10x.elifoot.controller.request.CreateUserRequest;
import dev.java10x.elifoot.controller.response.UserResponse;
import dev.java10x.elifoot.entity.Role;
import dev.java10x.elifoot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoleIdsToRoleEntities")
    User toEntity(CreateUserRequest request);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoleEntitiesToStringRoles")
    UserResponse toResponse(User user);

    @Named("mapRoleIdsToRoleEntities")
    default List<Role> mapRoleIdsToRoleEntities(List<Long> roleIds) {
        if (roleIds == null) return Collections.emptyList();

        return roleIds.stream()
                .map(id -> Role.builder().id(id).build())
                .toList();
    }

    @Named("mapRoleEntitiesToStringRoles")
    default List<String> mapRoleEntitiesToStringRoles(List<Role> roles) {
        if (roles == null) return Collections.emptyList();

        return roles.stream()
                .map(Role::getName)
                .toList();
    }
}
