package com.mancel.yann.mareu.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

/**
 * Created by Yann MANCEL on 19/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.utils
 */
public abstract class ShowMessage {

    // METHODS -------------------------------------------------------------------------------------

    /**
     * Shows a {@link Snackbar} with a message
     * @param coordinatorLayout a {@link CoordinatorLayout} that contains the view
     * @param message a {@link String} that contains the message to display
     */
    public static void showMessageWithSnackbar(@NonNull CoordinatorLayout coordinatorLayout,@NonNull String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Shows a {@link Toast} with a message
     * @param context a {@link Context}
     * @param message a {@link String} that contains the message to display
     */
    public static void showMessageWithToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
