package com.mancel.yann.mareu.model;

import java.util.Objects;

/**
 * Created by Yann MANCEL on 05/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.model
 */
public class Room {

    // FIELDS --------------------------------------------------------------------------------------

    private int mId;
    private String mName;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Construtor
     * @param id an integer that corresponds to the id
     * @param name a{@link String} that contains the name
     */
    public Room(int id, String name) {
        this.mId = id;
        this.mName = name;
    }

    // METHODS -------------------------------------------------------------------------------------

    public int getId() {
        return this.mId;
    }
    public String getName() {
        return this.mName;
    }

    public void setId(int id) {
        this.mId = id;
    }
    public void setName(String name) {
        this.mName = name;
    }

    // FOR COMPARISON *****************************************************************************

    @Override
    public boolean equals(Object obj) {
        // Same address
        if (this == obj) return true;

        // Null or the class is different
        if (obj == null || getClass() != obj.getClass()) return false;

        // Cast Object to Room
        Room room = (Room) obj;

        return Objects.equals(this.mId, room.mId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mId);
    }
}
