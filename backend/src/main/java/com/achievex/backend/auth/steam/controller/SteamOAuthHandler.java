package com.achievex.backend.auth.steam.controller;

import com.achievex.backend.auth.steam.service.SteamOpenIdService;
import com.achievex.backend.user.domain.User;
import com.achievex.backend.user.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SteamOAuthHandler {

    private final SteamOpenIdService steamOpenIdService;
    private final UserService userService;

    public SteamOAuthHandler(SteamOpenIdService steamOpenIdService, UserService userService) {
        this.steamOpenIdService = steamOpenIdService;
        this.userService = userService;
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
    public ResponseEntity<User> handlerCallback(HttpServletRequest request){
        boolean valid = steamOpenIdService.verifyAuthentication(request.getParameterMap());

        if(!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String claimedId = request.getParameter("openid.claimed_id");
        String steamId = steamOpenIdService.extractSteamId(claimedId);

        User createdUser = userService.findOrCreateBySteamId(steamId);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
