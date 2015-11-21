package hr.nullteam.rsc.business.api;

import java.util.List;

import hr.nullteam.rsc.business.api.model.Player;
import hr.nullteam.rsc.business.api.model.Team;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface TeamApi {

    @POST("Create")
    Observable<Team> createTeam(@Body Team newTeam);

    @GET("/Get/{team_id}")
    Observable<Team> getTeamWithId(@Path("team_id") long teamId);

    @POST("/Update")
    Observable<Team> updateTeam(@Body Team teamToUpdate);

    @GET("/GetTeamPlayers")
    Observable<List<Player>> getPlayerForTeam(@Query("teamId") long teamId);

}
