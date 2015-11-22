package hr.nullteam.rsc.business.api;

import hr.nullteam.rsc.business.api.model.RegisterPlayer;
import hr.nullteam.rsc.business.api.model.User;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.mime.TypedFile;
import rx.Observable;

public interface UserApi {

    @GET("/Get/{user_id}")
    Observable<User> getPlayerWithId(@Path("user_id") long user_id);

    @GET("/Get")
    Observable<User> getPlayerWithEmail(@Query("email") String email);

    @POST("/Register")
    Observable<User> registerPlayer(@Body RegisterPlayer registerPlayer);

    @POST("/Login")
    Observable<User> loginPlayer(@Query("email") String email, @Query("password") String password, @Body String bodyString);

    @POST("/UpdateUserImage")
    Observable<User> updateUserImage(@Query("email") String email, @Body TypedFile file);

}
