package com.mancel.yann.mareu.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Created by Yann MANCEL on 29/07/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.model
 */
public class Meeting {

    // FIELDS --------------------------------------------------------------------------------------

    @SerializedName("identifier")
    @Expose
    private int mId;
    @SerializedName("topic")
    @Expose
    private String mTopic;
    @SerializedName("hour")
    @Expose
    private String mHour;
    @SerializedName("room")
    @Expose
    private String mRoom;
    @SerializedName("member")
    @Expose
    private String mMember;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Construtor
     * @param id an integer that corresponds to the id
     * @param topic a{@link String} that contains the topic
     * @param hour a{@link String} that contains the hour
     * @param room a{@link String} that contains the room
     * @param member a{@link String} that contains the members
     */
    public Meeting(int id, String topic, String hour, String room, String member) {
        this.mId = id;
        this.mTopic = topic;
        this.mHour = hour;
        this.mRoom = room;
        this.mMember = member;
    }

    // METHODS -------------------------------------------------------------------------------------

    public int getId() {
        return this.mId;
    }
    public String getTopic() {
        return this.mTopic;
    }
    public String getHour() {
        return this.mHour;
    }
    public String getRoom() {
        return this.mRoom;
    }
    public String getMember() {
        return this.mMember;
    }

    public void setId(int id) {
        this.mId = id;
    }
    public void setTopic(String topic) {
        this.mTopic = topic;
    }
    public void setHour(String hour) {
        this.mHour = hour;
    }
    public void setRoom(String room) {
        this.mRoom = room;
    }
    public void setMember(String member) {
        this.mMember = member;
    }

    // FOR COMPARISON *****************************************************************************

    @Override
    public boolean equals(Object obj) {
        // Same address
        if (this == obj) return true;

        // Null or the class is different
        if (obj == null || getClass() != obj.getClass()) return false;

        // Cast Object to Meeting
        Meeting meeting = (Meeting) obj;

        return Objects.equals(this.mId, meeting.mId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mId);
    }
}
