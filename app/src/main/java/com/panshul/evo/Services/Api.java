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
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @GET("/events")
    Call<EventMainObject> getAllEvents();

    @GET("/events?type=Gravitas")
    Call<EventMainObject> getGravitasEvents();

    @GET("/events?type=Riviera")
    Call<EventMainObject> getRivieraEvents();

    @GET("/events?type=Hackathon")
    Call<EventMainObject> getHackathonEvents();

    @GET("/events?type=Workshop")
    Call<EventMainObject> getWorkshopEvents();

    @GET("/events?type=Speaker")
    Call<EventMainObject> getSpeakerEvents();

    @GET("/events?type=Cultural")
    Call<EventMainObject> getCulturalEvents();

    @GET("/events?type=NGO")
    Call<EventMainObject> getNGOEvents();

    @GET("/events/popular")
    Call<List<PopularMainObject>> getPopular();

    @GET("/events/get/{eventId}")
    Call<EventRoot> getSpecificEvent(@Path("eventId") String eventID);

    @GET("/club/get/{clubId}")
    Call<ClubSpecificObject> getSpecificClub(@Path("clubId") String clubId);

    @GET("/events/club/{clubId}")
    Call<ClubAllEvents> getAllClubEvents(@Path("clubId") String clubId);

    @GET("/events/club/{clubId}?type=Gravitas")
    Call<ClubAllEvents> getAllGravitasEvents(@Path("clubId") String clubId);

    @GET("/events/club/{clubId}?type=Riviera")
    Call<ClubAllEvents> getAllRivieraEvents(@Path("clubId") String clubId);

    @GET("/events/club/{clubId}?type=Hackathon")
    Call<ClubAllEvents> getAllHackathonEvents(@Path("clubId") String clubId);

    @GET("/events/club/{clubId}?type=Workshop")
    Call<ClubAllEvents> getAllWorkshopEvents(@Path("clubId") String clubId);

    @GET("/events/club/{clubId}?type=Speaker")
    Call<ClubAllEvents> getAllSpeakersEvents(@Path("clubId") String clubId);

    @GET("/events/club/{clubId}?type=Cultural")
    Call<ClubAllEvents> getAllCulturalEvents(@Path("clubId") String clubId);

    @GET("/events/club/{clubId}?type=NGO")
    Call<ClubAllEvents> getAllNGOEvents(@Path("clubId") String clubId);


    @POST("/events/search/")
    Call<List<SearchObject>> getSearch(@Body SearchInput input);

    @POST("/events/saved/")
    Call<List<EventObject>> getSaved(@Body InterestedPost events);

    @PUT("/events/like")
    Call<LikeResponse> likeEvent(@Body LikeBody eventId);


}
