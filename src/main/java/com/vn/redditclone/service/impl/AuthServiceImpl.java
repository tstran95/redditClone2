package com.vn.redditclone.service.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.vn.redditclone.bean.request.RegisterRequest;
import com.vn.redditclone.bean.response.BaseResponse;
import com.vn.redditclone.bean.response.ResponseCodeEnum;
import com.vn.redditclone.model.NotificationEmail;
import com.vn.redditclone.model.User;
import com.vn.redditclone.model.VerificationToken;
import com.vn.redditclone.repository.UserRepository;
import com.vn.redditclone.repository.VerificationTokenRepository;
import com.vn.redditclone.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailServiceImpl mailService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public BaseResponse signup(RegisterRequest request) {
        if (Objects.nonNull(userRepository.findByUserName(request.getUsername()))
                || Objects.nonNull(userRepository.findByEmail(request.getEmail()))) {
            return BaseResponse.of(ResponseCodeEnum.INVALID_ERROR);
        }
        User user = modelMapper.map(request , User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedDate(Instant.now());
        user.setStatus(false);
        userRepository.save(user);
        String token = generateVerificationToken(user);
        NotificationEmail notificationEmail = NotificationEmail.builder()
                .subject("Please active your account!")
                .recipient(request.getEmail())
                .body("Please click on the below url to activate your account : "
                + "http://localhost:8080/api/auth/accountVerification/" + token)
                .build();
        mailService.sendMail(notificationEmail);
        return BaseResponse.of(ResponseCodeEnum.SUCCESS);
    }

    private String generateVerificationToken(User user) {
        String token = NanoIdUtils.randomNanoId();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
