package com.viniciusdevassis.ficha.musical.presentation.mappers;

import com.viniciusdevassis.ficha.musical.domain.entities.User;
import com.viniciusdevassis.ficha.musical.presentation.dtos.RegisterDTO;
import com.viniciusdevassis.ficha.musical.presentation.dtos.ResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RegisterDTO userToRegisterDTO(User user);

    User registerDTOToUser(RegisterDTO dto);

    ResponseDTO userToResponseDTO(User user);

    User responseDTOToUser(ResponseDTO dto);
}
