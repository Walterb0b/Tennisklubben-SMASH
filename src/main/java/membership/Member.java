package main.java.membership;

import java.time.LocalDate;
import java.time.Period;

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

    public LocalDate getBirthday() { return birthday; }

    public LocalDate getSignUpDate() { return signUpDate; }

    public LocalDate getCancellationDate() { return cancellationDate; }

    public int getAge() {
        return Period.between(getBirthday(), LocalDate.now()).getYears();
    }

    public Membership getMembership() { return membership; }

    public PlayPreference playPreference() { return playPreference; }

    //setters
    public void setName(String name) { this.name = name; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    //ingen setBirthday? Bør ikke kunne ændres, når brugeren først er oprettet??
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public void setCancellationDate(LocalDate cancellationDate) { this.cancellationDate = cancellationDate; }

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

    @Override
    public String toString() {
        return "MedlemsID: " + memberID + ", Navn: " + name + ", Telefonnummer: " + phoneNumber +
                ", Fødselsdag: " + birthday + ", Alder: " + getAge() + ", AldersKategori: " + getAgeCategory() +
                ", Medlemsskab: " + getMembership() + ", SpillerPræferencer: " + playPreference;
    }



}
