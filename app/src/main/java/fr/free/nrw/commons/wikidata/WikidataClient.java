package fr.free.nrw.commons.wikidata;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.free.nrw.commons.auth.AuthTokenClient;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import timber.log.Timber;

@Singleton
public class WikidataClient {

    private final WikidataInterface wikidataInterface;
    private final AuthTokenClient authTokenClient;

    @Inject
    public WikidataClient(WikidataInterface wikidataInterface,
                          AuthTokenClient authTokenClient) {
        this.wikidataInterface = wikidataInterface;
        this.authTokenClient = authTokenClient;
    }

    public Observable<Long> createClaim(String entityId, String property, String snaktype, String value) {
        try {
            return getCsrfToken()
                    .flatMap(csrfToken -> wikidataInterface.postCreateClaim(toRequestBody(entityId),
                            toRequestBody(snaktype),
                            toRequestBody(property),
                            toRequestBody("\"" + value + "\""),
                            toRequestBody("qqx"),
                            toRequestBody(csrfToken)))
                    .map(mwPostResponse -> {
                        if (mwPostResponse.getSuccessVal() == 1) {
                            return 1L;
                        }
                        return -1L;
                    });
        } catch (Throwable throwable) {
            return Observable.just(-1L);
        }
    }

    public RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }

    @NotNull
    private Observable<String> getCsrfToken() {
        return wikidataInterface.getCsrfToken()
                .map(mwQueryResponse -> {
                    Timber.d("Get csrf token response %s", mwQueryResponse.success());
                    return mwQueryResponse.query().csrfToken();
                });
    }

    public ObservableSource<?> addEditTag(Long revisionId, String commonsAppTag, String commonsAppEditReason) {
        return null;
    }
}
