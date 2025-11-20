package main.java.membership;

import java.time.LocalDate;
import java.time.Period;

public class Member {
    private int memberID;
    private String name;
    private int phoneNumber;
    private LocalDate birthday;
    private Membership membership;
    private PlayPreference playPreference;

    public Member(String name, int phoneNumber, LocalDate birthday, Membership membership, PlayPreference playPreference) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.membership = membership;
        this.playPreference = playPreference;
    }

    //getters
    public int getMemberID() { return memberID; }

    public String getName() { return name; }

    public LocalDate getBirthday() { return birthday; }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public Membership getMembership() { return membership; }

    public PlayPreference playPreference() { return playPreference; }

    //setters
    public void setName(String name) { this.name = name; }

    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }

    //ingen setBirthday? Bør ikke kunne ændres, når brugeren først er oprettet??
    public void setBirthday(int LocalDate) { this.birthday = birthday; }

    public void setMembership( Membership membership) { this.membership = membership; }

    public void setPlayPreference( PlayPreference playPreference) { this.playPreference = playPreference; }

    public String getAgeCategory() {
        int age = getAge();
        /*

        String ageCategory = switch(age) {
            case (age < 18):
                yield "Junior";
            case age >= 18 && age < 60:
                yield "Senior";
            case age >= 60:
                yield "60+";
            default:
                throw new IllegalStateException("Ugyldig alder: " + age);


        };
        return ageCategory;

         */
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



}
