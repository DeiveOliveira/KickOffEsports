package com.KickOofEsports.KickOffEsports.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogoutController {

    @PostMapping("/logout")
    public ResponseEntity logout(HttpSession session) {
        System.out.println("Deslogando usuário");

        session.invalidate();

        return ResponseEntity.ok().body(session);

    }
}
