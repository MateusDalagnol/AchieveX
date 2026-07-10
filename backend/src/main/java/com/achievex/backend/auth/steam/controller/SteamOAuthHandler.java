package com.achievex.backend.auth.steam.controller;

import com.achievex.backend.auth.steam.service.SteamOpenIdService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SteamOAuthHandler {

    private final SteamOpenIdService steamOpenIdService;

    public SteamOAuthHandler(SteamOpenIdService steamOpenIdService) {
        this.steamOpenIdService = steamOpenIdService;
    }

    @GetMapping("/api/v1/auth/steam")
    public ResponseEntity<String> redirectToSteam(){

        String returnTo = "http://localhost:8080/api/v1/auth/steam/callback";
        String realm = "http://localhost:8080";

        String url = steamOpenIdService.buildAuthenticationUrl(returnTo, realm);

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", url)
                .build();
    }

    @GetMapping("/api/v1/auth/steam/callback")
    public ResponseEntity<String> handlerCallback(HttpServletRequest request){
        boolean valid = steamOpenIdService.verifyAuthentication(request.getParameterMap());

        if(!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Steam authentication");
        }

        String claimedId = request.getParameter("openid.claimed_id");
        String steamId = steamOpenIdService.extractSteamId(claimedId);

        return ResponseEntity.ok(steamId);
    }

}
