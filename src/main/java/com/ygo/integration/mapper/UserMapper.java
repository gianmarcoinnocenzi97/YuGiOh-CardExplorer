package com.ygo.integration.mapper;

import com.ygo.model.User;
import com.ygo.model.dto.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(target = "auth.user", ignore = true)
    User toUser(RegisterRequest request);
}

