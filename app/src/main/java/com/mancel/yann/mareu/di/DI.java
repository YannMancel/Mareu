package com.mancel.yann.mareu.di;

import android.support.annotation.VisibleForTesting;

import com.mancel.yann.mareu.service.DummyApiService;
import com.mancel.yann.mareu.service.ApiService;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.di
 *
 * Pattern FACTORY METHOD
 */
public abstract class DI {

    // FIELDS --------------------------------------------------------------------------------------

    private static ApiService mService = new DummyApiService();

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Returns the only instance of {@link ApiService}
     * @return the only instance of {@link ApiService}
     */
    public static ApiService getApiService() {
        return mService;
    }

    /**
     * Returns always a new instance on {@link ApiService}.
     * Useful for tests, so we ensure the context is clean.
     * @return a {@link ApiService}
     */
    @VisibleForTesting
    public static ApiService getNewInstanceApiService() {
        return new DummyApiService();
    }
}
