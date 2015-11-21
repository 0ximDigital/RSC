package hr.nullteam.rsc.business.api;

import hr.nullteam.rsc.business.api.model.Player;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface PlayerApi {

    @POST("/Create")
    Observable<Player> createNewPlayer(@Body Player newPlayer);

    @POST("/UpdateAlive")
    Observable<Player> updatePlayerAlive(@Query("playerId") long playerid, @Query("alive") boolean isAlive);

    @POST("/UpdateReady")
    Observable<Player> updatePlayerReady(@Query("playerId") long playerid, @Query("ready") boolean isReady);

    @POST("/UpdateLocation")
    Observable<Player> updatePlayerLocation(@Query("playerId") long playerId, @Query("lat") float lat, @Query("lng") float lng);

    @GET("/Get/{player_id}")
    Observable<Player> getPlayerWithId(@Path("player_id") long playerId);

}
