package com.checkyou.shinhansec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;


@Service
@EnableAsync
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    @Value("${email.domain}")
    private String domain;

    @Value("${email.url}")
    private String url;

    @Async
    public void send(String email, String authToken){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject("회원 가입 이메일 인증");
        simpleMailMessage.setText("아래 링크를 눌러서 이메일 인증을 완료 해주세요.\n"+domain + url + "?email="+email+"&authToken="+authToken);

        javaMailSender.send(simpleMailMessage);
    }
}
