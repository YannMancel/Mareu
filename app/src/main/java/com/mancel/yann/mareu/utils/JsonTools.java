package com.mancel.yann.mareu.utils;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Yann MANCEL on 02/07/2019.
 * Name of the project: Entrevoisins
 * Name of the package: com.openclassrooms.entrevoisins.utils
 */
public abstract class JsonTools {

    /**
     * Converts a Java class to a Json format
     * @param object a {@link Object} for convertion
     * @return a {@link String} that contains the Json format
     */
    public static String convertJavaToJson(final Object object) {
        return new Gson().toJson(object);
    }

    /**
     * Converts a Json format to a Java class
     * @param json a {@link String} that contains the Json format
     * @param classOfT a {@link Class} type
     * @param <T> the class for conversion
     * @return the Java class
     */
    public static <T> T convertJsonToJava(final String json, Class<T> classOfT) {
        return new Gson().fromJson(json, classOfT);
    }

    /**
     * Converts a Json format to a Java class (For the {@link java.util.Collection} )
     * @param json a {@link String} that contains the Json format
     * @param classOfT a {@link Class} type
     * @param <T> the class for conversion
     * @return the Java class
     */
    public static <T> List<T> convertJsonToJavaList(final String json, Class<T> classOfT) {
        return new Gson().fromJson(json, new ListOfJson<T>(classOfT));
    }
}
