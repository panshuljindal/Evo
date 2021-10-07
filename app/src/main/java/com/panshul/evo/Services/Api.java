package com.panshul.evo.Services;

import com.panshul.evo.Object.Club.ClubAllEvents;
import com.panshul.evo.Object.Club.ClubSpecificObject;
import com.panshul.evo.Object.Event.EventMainObject;
import com.panshul.evo.Object.Event.EventObject;
import com.panshul.evo.Object.Event.EventRoot;
import com.panshul.evo.Object.Interested.InterestedPost;
import com.panshul.evo.Object.Like.LikeBody;
import com.panshul.evo.Object.Like.LikeResponse;
import com.panshul.evo.Object.Popular.PopularMainObject;
import com.panshul.evo.Object.Search.SearchInput;
import com.panshul.evo.Object.Search.SearchObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtZXNzYWdlIjoiaGVja2VyIiwiaWF0IjoxNjMyMjA0MjcyfQ.QetSTqHMjmLSZDWFcGidwdjPHD1rbiCdCXP_X_pDPcw";
    @GET("/events")
    @Headers({"auth-token: "+token})
    Call<EventMainObject> getAllEvents( @Query("page") int number);

    @GET("/events?type=Gravitas")
    @Headers({"auth-token: "+token})
    Call<EventMainObject> getGravitasEvents( @Query("page") int number);

    @GET("/events?type=Riviera")
    @Headers({"auth-token: "+token})
    Call<EventMainObject> getRivieraEvents( @Query("page") int number);

    @GET("/events?type=Hackathon")
    @Headers({"auth-token: "+token})
    Call<EventMainObject> getHackathonEvents( @Query("page") int number);

    @GET("/events?type=Workshop")
    @Headers({"auth-token: "+token})
    Call<EventMainObject> getWorkshopEvents( @Query("page") int number);

    @GET("/events?type=Speaker")
    @Headers({"auth-token: "+token})
    Call<EventMainObject> getSpeakerEvents( @Query("page") int number);

    @GET("/events?type=Cultural")
    @Headers({"auth-token: "+token})
    Call<EventMainObject> getCulturalEvents( @Query("page") int number);

    @GET("/events?type=NGO")
    @Headers({"auth-token: "+token})
    Call<EventMainObject> getNGOEvents( @Query("page") int number);

    @GET("/events/popular")
    @Headers({"auth-token: "+token})
    Call<List<PopularMainObject>> getPopular();

    @GET("/events/get/{eventId}")
    @Headers({"auth-token: "+token})
    Call<EventRoot> getSpecificEvent(@Path("eventId") String eventID);

    @GET("/club/get/{clubId}")
    @Headers({"auth-token: "+token})
    Call<ClubSpecificObject> getSpecificClub(@Path("clubId") String clubId);

    @GET("/events/club/{clubId}")
    @Headers({"auth-token: "+token})
    Call<ClubAllEvents> getAllClubEvents(@Path("clubId") String clubId);

    @POST("/events/search")
    @Headers({"auth-token: "+token})
    Call<List<SearchObject>> getSearch(@Body SearchInput input, @Query("page") int number);

    @POST("/events/search?type=2")
    @Headers({"auth-token: "+token})
    Call<List<SearchObject>> getSearchEvent(@Body SearchInput input, @Query("page") int number);

    @POST("/events/search?type=1")
    @Headers({"auth-token: "+token})
    Call<List<SearchObject>> getSearchClub(@Body SearchInput input, @Query("page") int number);

    @POST("/events/saved/")
    @Headers({"auth-token: "+token})
    Call<List<EventObject>> getSaved(@Body InterestedPost events);

    @PUT("/events/like")
    @Headers({"auth-token: "+token})
    Call<LikeResponse> likeEvent(@Body LikeBody eventId);

    @PUT("/events/dislike")
    @Headers({"auth-token: "+token})
    Call<LikeResponse> dislikeEvent(@Body LikeBody eventId);

}
