package fr.free.nrw.commons.wikidata;

import androidx.annotation.NonNull;

import org.wikipedia.dataclient.mwapi.MwPostResponse;
import org.wikipedia.dataclient.mwapi.MwQueryResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

import static org.wikipedia.dataclient.Service.MW_API_PREFIX;

public interface WikidataInterface {

    @Headers("Cache-Control: no-cache")
    @POST("w/api.php?format=json&errorformat=plaintext&action=wbcreateclaim&errorlang=uselang&wrappedhtml=1")
    @Multipart
    Observable<MwPostResponse> postCreateClaim(@NonNull @Part("entity") RequestBody entity,
                                               @NonNull @Part("snaktype") RequestBody snakType,
                                               @NonNull @Part("property") RequestBody property,
                                               @NonNull @Part("value") RequestBody value,
                                               @NonNull @Part("uselang") RequestBody useLang,
                                               @NonNull @Part("token") RequestBody token);

    @Headers("Cache-Control: no-cache")
    @POST(MW_API_PREFIX + "action=tag")
    @FormUrlEncoded
    Observable<MwPostResponse> addEditTag(@NonNull @Field("revid") String revId,
                                          @NonNull @Field("add") String tagName,
                                          @NonNull @Field("reason") String reason,
                                          @NonNull @Field("token") String token,
                                          @NonNull @Field("centralauthtoken") String centralAuthToken);

    @Headers("Cache-Control: no-cache")
    @GET(MW_API_PREFIX + "action=query&meta=tokens&type=csrf")
    @NonNull
    Observable<MwQueryResponse> getCsrfToken();
}
