package hr.nullteam.rsc.business.api;

import hr.nullteam.rsc.business.api.model.Judge;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import rx.Observable;

public interface JudgeApi {

    @POST("/Create")
    Observable<Judge> createJudge(@Body Judge judge);

    @GET("/Get/{judge_id}")
    Observable<Judge> getjudgebyId(@Path("judge_id") long judgeId);

}
