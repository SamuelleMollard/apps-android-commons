package fr.free.nrw.commons.auth;

import java.util.Map;

public class CentralAuthTokenResponse {
    private final Map<String, String> centralauthtoken;

    public CentralAuthTokenResponse(Map<String, String> centralauthtoken) {
        this.centralauthtoken = centralauthtoken;
    }

    public String getCentralAuthToken() {
        return centralauthtoken.get("centralauthtoken");
    }
}
