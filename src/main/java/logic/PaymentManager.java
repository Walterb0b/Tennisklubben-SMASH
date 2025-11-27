package main.java.logic;

import main.java.membership.Member;
import main.java.membership.MembershipPayment;

import java.util.HashMap;

public class PaymentManager {
    private MemberManager memberManager;
    private HashMap<Integer, MembershipPayment> payments;

    public PaymentManager(MemberManager memberManager){
        this.memberManager = memberManager;
        this.payments = new HashMap<>();
    }

    public void addNextSeason(){
        for (Member m : memberManager.getAllMembers().values()) {
          //
        }
    }

    public void payFuturePayments(int memberID){}

    private void addQuarterlyPayment(){}

    private void missingPaymentsList(){}

    private void futurePaymentsList(){}
}
