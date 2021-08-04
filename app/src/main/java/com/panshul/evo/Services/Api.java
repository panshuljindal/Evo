package com.panshul.evo.Services;

import com.panshul.evo.Object.Event.EventMainObject;
import com.panshul.evo.Object.Event.EventObject;
import com.panshul.evo.Object.Event.EventSpecificObject;
import com.panshul.evo.Object.Popular.PopularMainObject;
import com.panshul.evo.Object.Search.SearchObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("/events")
    Call<EventMainObject> getAllEvents();

    @GET("")
    Call<List<EventMainObject>> getGravitasEvents();

    @GET("")
    Call<List<EventMainObject>> getRivieraEvents();

    @GET("")
    Call<List<EventMainObject>> getHackathonEvents();

    @GET("")
    Call<List<EventMainObject>> getWorkshopEvents();

    @GET("")
    Call<List<EventMainObject>> getSpeakerEvents();

    @GET("")
    Call<List<EventMainObject>> getCulturalEvents();

    @GET("")
    Call<List<EventMainObject>> getNGOEvents();

    @GET("/events/popular")
    Call<List<PopularMainObject>> getPopular();

    @GET("/events/get/{eventId}")
    Call<EventSpecificObject> getSpecificEvent(@Path("eventId") String eventID);

    @GET("")
    Call<List<SearchObject>> getSearch( );


}
