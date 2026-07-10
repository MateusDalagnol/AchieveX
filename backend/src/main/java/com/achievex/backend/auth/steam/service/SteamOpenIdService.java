package com.achievex.backend.auth.steam.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SteamOpenIdService {

    private final RestTemplate restTemplate;

    public SteamOpenIdService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String buildAuthenticationUrl(String returnToUrl, String realm){
        String url = "https://steamcommunity.com/openid/login?" +
                "openid.ns=http://specs.openid.net/auth/2.0" +
                "&openid.mode=checkid_setup" +
                "&openid.return_to=" + returnToUrl +
                "&openid.realm=" + realm +
                "&openid.identity=http://specs.openid.net/auth/2.0/identifier_select" +
                "&openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select";

        return url;
    }

    public boolean verifyAuthentication(Map<String, String[]> parameterMap){

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            body.add(entry.getKey(), entry.getValue()[0]);
        }

        body.set("openid.mode", "check_authentication");

        String openIdProviderUrl = "https://steamcommunity.com/openid/login";

        ResponseEntity<String> providerResponse = this.restTemplate.postForEntity(openIdProviderUrl, body, String.class);

        String responseBody = providerResponse.getBody();
        return responseBody != null && responseBody.contains("is_valid:true");
    }

    public String extractSteamId(String claimedId){

        Pattern pattern = Pattern.compile("^https://steamcommunity\\.com/openid/id/(\\d+)$");
        Matcher matcher = pattern.matcher(claimedId);

        if (matcher.matches()){
            return matcher.group(1);
        } else {
            throw new IllegalArgumentException("Invalid claimed id: " + claimedId);
        }
    }

}
