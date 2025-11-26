package main.java.membership;

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
    public double calculateYearlyFee() {
        /*
        if(Member.getAge() < 18){
            return 800;
        } else if (Member.getAge() >= 60) {
            return 1500 * 0.75; //25 % rabat til folk 60+
        } else {
            return 1500;
        }

         */
        return 1500;
    }


}
