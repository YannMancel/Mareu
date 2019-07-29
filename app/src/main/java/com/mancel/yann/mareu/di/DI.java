package com.mancel.yann.mareu.di;

import com.mancel.yann.mareu.service.DummyMeetingApiService;
import com.mancel.yann.mareu.service.MeetingApiService;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.di
 *
 * Pattern FACTORY METHOD
 */
public abstract class DI {

    // FIELDS --------------------------------------------------------------------------------------

    private static MeetingApiService mService = new DummyMeetingApiService();

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Returns the only instance of {@link MeetingApiService}
     * @return the only instance of {@link MeetingApiService}
     */
    public static MeetingApiService getMeetingApiService() {
        return mService;
    }

    /**
     * Returns always a new instance on {@link MeetingApiService}.
     * Useful for tests, so we ensure the context is clean.
     * @return a {@link MeetingApiService}
     */
    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
