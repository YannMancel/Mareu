package com.mancel.yann.mareu.model;

import java.util.Objects;

/**
 * Created by Yann MANCEL on 06/08/2019.
 * Name of the project: Mareu
 * Name of the package: com.mancel.yann.mareu.model
 */
public class Member {

    // FIELDS --------------------------------------------------------------------------------------

    private int mId;
    private String mFirstName;
    private String mLastName;
    private String mEmail;

    // CONSTRUCTORS --------------------------------------------------------------------------------

    /**
     * Construtor
     * @param id an integer that corresponds to the id
     * @param firstName a{@link String} that contains the first name
     * @param lastName a{@link String} that contains the last name
     * @param email a{@link String} that contains the email
     */
    public Member(int id, String firstName, String lastName, String email) {
        this.mId = id;
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mEmail = email;
    }

    // METHODS -------------------------------------------------------------------------------------

    public int getId() {
        return this.mId;
    }
    public String getFirstName() {
        return this.mFirstName;
    }
    public String getLastName() {
        return this.mLastName;
    }
    public String getEmail() {
        return this.mEmail;
    }

    public void setId(int id) {
        this.mId = id;
    }
    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }
    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }
    public void setEmail(String email) {
        this.mEmail = email;
    }

    // FOR COMPARISON ******************************************************************************

    @Override
    public boolean equals(Object obj) {
        // Same address
        if (this == obj) return true;

        // Null or the class is different
        if (obj == null || getClass() != obj.getClass()) return false;

        // Cast Object to Member
        Member member = (Member) obj;

        return Objects.equals(this.mId, member.mId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.mId);
    }
}
