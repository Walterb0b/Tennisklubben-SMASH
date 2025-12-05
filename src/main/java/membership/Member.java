package main.java.membership;

import java.time.LocalDate;
import java.time.Period;

/**
 * Håndterer Stamdata på medlemmer.
 * Klassen bruges af MemberManager
 *
 */
public class Member {
    private static int nextID = 1;
    private final int memberID;
    private String name;
    private String phoneNumber;
    private LocalDate birthday;
    private LocalDate signUpDate;
    private LocalDate cancellationDate;
    private Membership membership;
    private PlayPreference playPreference;
    private Integer eloRating = null;
    private int smashPoints = 0;

    /**Konstruktør til oprettelse af medlem med spilpræference
     *
     * @param name Medlemmets navn
     * @param phoneNumber Medlemmets telefonnummer
     * @param birthday Medlemmets fødselsdag
     * @param signUpDate Dato for start af medlemsskab
     * @param membership Vælg om medlemsskabet er aktivt eller passivt
     * @param playPreference Opret information for medlemmets spilpræferencer
     */

    public Member(String name, String phoneNumber, LocalDate birthday, LocalDate signUpDate, Membership membership,
                  PlayPreference playPreference) {
        this.memberID = nextID++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.signUpDate = signUpDate;
        this.membership = membership;
        this.playPreference = playPreference;
    }

    /**Konstruktør til oprettelse af medlem uden spillerpræference
     *
     * @param name Medlemmets navn
     * @param phoneNumber Medlemmets telefonnummer
     * @param birthday Medlemmets fødselsdag
     * @param signUpDate Dato for start af medlemsskab
     * @param membership Vælg om medlemsskabet er aktivt eller passivt
     */

    public Member(String name, String phoneNumber, LocalDate birthday, LocalDate signUpDate, Membership membership) {
        this.memberID = nextID++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.signUpDate = signUpDate;
        this.membership = membership;
    }

    //getters
    public int getNextID() { return nextID; }

    public int getMemberID() { return memberID; }

    public String getName() { return name; }

    public String getPhoneNumber() { return phoneNumber; }

    public LocalDate getBirthday() { return birthday; }

    public LocalDate getSignUpDate() { return signUpDate; }

    public LocalDate getCancellationDate() { return cancellationDate; }

    public int getAge() {
        return Period.between(getBirthday(), LocalDate.now()).getYears();
    }

    public Membership getMembership() { return membership; }

    public PlayPreference getPlayPreference() { return playPreference; }

    public Integer getEloRating() { return eloRating; }

    public int getSmashPoints() { return smashPoints; }

    //setters
    public void setName(String name) { this.name = name; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public void setCancellationDate(LocalDate cancellationDate) { this.cancellationDate = cancellationDate; }

    public void setMembership( Membership membership) { this.membership = membership; }

    public void setPlayPreference( PlayPreference playPreference) { this.playPreference = playPreference; }

    public void setEloRating(Integer newRating) {
        this.eloRating = newRating;
    }

    /**
     * Opdaterer spillerens alderskategori baseret på spillerens nuværende alder
     * @return alderskategori "Junior"/"Senior"/"60+"
     */
    public String getAgeCategory() {
        int age = getAge();
        if (age < 18) {
            return "Junior";
        } else if (age >=18 && age < 60) {
            return "Senior";
        } else if (age >= 60 && age < 120) {
            return "60+";
        } else {
            throw new IllegalStateException("Ugyldig alder: " + age);
        }
    }

    public boolean isCompetitive(){
        return playPreference.isCompetetiveMember();
    }

    /**
     * Tilføjer default eloRating på 1500, hvis spilleren er Konkurrencespiller og ikke har fået eloRating endnu
     */
    public void initializeEloIfNeeded(){
        if(isCompetitive() && eloRating == null) eloRating = 1500;
    }

    public void addSmashPoints(int points){
        smashPoints += points;
    }

    public void setSmashPoints(int smashPoints){
        this.smashPoints = smashPoints;
    }

    public String memberNameAndIDString() {
        return "Navn: " + name + ", MedlemsID: " + memberID;
    }


    @Override
    public String toString() {
        return "MedlemsID: " + memberID + ", Navn: " + name + ", Telefonnummer: " + phoneNumber +
                ", Fødselsdag: " + birthday + ", Alder: " + getAge() + ", AldersKategori: " + getAgeCategory() +
                ", Medlemsskab: " + getMembership() + ", SpillerPræferencer: " + playPreference;
    }
}
