package main.java.membership;

public class PassiveMembership implements Membership{
    @Override
    public String getName() {
        return "Passiv";
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public double calculateYearlyFee() {
        return 250;
    }
}
