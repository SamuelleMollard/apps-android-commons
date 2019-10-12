package fr.free.nrw.commons.auth;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

@Singleton
public class AuthTokenClient {
    private final AuthTokenInterface authTokenInterface;

    @Inject
    public AuthTokenClient(AuthTokenInterface authTokenInterface) {
        this.authTokenInterface = authTokenInterface;
    }

    public String getCentralAuthToken() {
        String centralAuthToken = authTokenInterface.getCentralAuthToken().blockingFirst().getCentralAuthToken();
        Timber.d("Central auth token is %s", centralAuthToken);
        return centralAuthToken;
    }
}
