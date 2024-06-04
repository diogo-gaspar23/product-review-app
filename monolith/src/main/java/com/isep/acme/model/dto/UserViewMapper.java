package com.isep.acme.model.dto;

import com.isep.acme.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);
}
