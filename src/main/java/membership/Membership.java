package main.java.membership;

/**
 * Interface for medlemsskab. Muliggør differentieret pris baseret på om medlemsskabet er aktivt eller passivt
 */
public interface Membership {
    String toString();
    boolean isActive();
    double calculateYearlyFee(int age);
}
