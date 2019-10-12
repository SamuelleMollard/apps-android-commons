package fr.free.nrw.commons.auth;

import androidx.annotation.NonNull;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import static org.wikipedia.dataclient.Service.MW_API_PREFIX;

public interface AuthTokenInterface {
    @Headers("Cache-Control: no-cache")
    @GET(MW_API_PREFIX + "action=centralauthtoken")
    @NonNull
    Observable<CentralAuthTokenResponse> getCentralAuthToken();
}
