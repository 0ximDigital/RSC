package hr.nullteam.rsc.business.api;

import hr.nullteam.rsc.business.api.model.Player;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface UserApi {

    @GET("/Get/{user_id}")
    Observable<Player> getPlayerWithId(@Path("user_id") long user_id);

    @GET("/Get")
    Observable<Player> getPlayerWithEmail(@Query("email") String email);

    @POST("/Register")
    Observable<Player> registerPlayer(@Body Player player);

    @POST("/Login")
    Observable<Player> loginPlayer(@Query("email") String email, @Query("password") String password);

}
