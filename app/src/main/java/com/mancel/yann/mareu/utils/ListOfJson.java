package com.mancel.yann.mareu.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Yann MANCEL on 04/07/2019.
 * Name of the project: Entrevoisins
 * Name of the package: com.openclassrooms.entrevoisins.utils
 *
 * A {@link ParameterizedType} subclass.
 * @param <T> a class
 */
public class ListOfJson<T> implements ParameterizedType {
    // FIELDS --------------------------------------------------------------------------------------

    private Class<?> wrapped;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Constructor
     * @param wrapped a {@link Class}
     */
    public ListOfJson(Class<?> wrapped) {
        this.wrapped = wrapped;
    }

    // METHODS -------------------------------------------------------------------------------------

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] {this.wrapped};
    }

    @Override
    public Type getRawType() {
        return List.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }
}
