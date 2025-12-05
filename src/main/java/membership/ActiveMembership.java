package main.java.membership;

/**
 * Klasse for Aktivt Medlemsskab. Håndterer beregning af årligt pris for medlemsskab
 */
public class ActiveMembership implements Membership{
    @Override
    public String toString() {
        return "Aktivt";
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public double calculateYearlyFee(int age) {

        if(age < 18){
            return 800;
        } else if (age >= 60) {
            return 1500 * 0.75; //25 % rabat til folk 60+
        } else {
            return 1500;
        }

    }


}
