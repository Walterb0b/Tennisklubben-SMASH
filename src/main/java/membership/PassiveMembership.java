package main.java.membership;

/**
 * Klasse for Passivt Medlemsskab. Håndterer beregning af årligt pris for medlemsskab
 */
public class PassiveMembership implements Membership{
    @Override
    public String toString() {
        return "Passiv";
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public double calculateYearlyFee(int age) {
        return 250;
    }
}
