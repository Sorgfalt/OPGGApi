package com.opgg.opggapi.service;

import com.opgg.opggapi.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final BCryptPasswordEncoder encoder;
    @Override
    public Long userJoin(UserDto.RequestUserDto dto) {
        /* 비밀번호 암호화 */
        dto.encryptPassword(encoder.encode(dto.getPassword()));

        User user = dto.toEntity();
        userRepository.save(user);
        log.info("DB에 회원 저장 성공");

        return user.getId();
    }
}
