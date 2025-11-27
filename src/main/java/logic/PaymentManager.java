package main.java.logic;

import main.java.membership.Member;
import main.java.membership.MembershipPayment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PaymentManager {
    private MemberManager memberManager;
    private HashMap<Integer, MembershipPayment> payments;

    public PaymentManager(MemberManager memberManager){
        this.memberManager = memberManager;
        this.payments = new HashMap<>();
    }

    public MembershipPayment getPayment(int paymentID){
        return payments.get(paymentID);
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

    public ArrayList<Integer> notPaidIDs(){
        ArrayList<Integer> membersNotPaid = new ArrayList<>();
        for (MembershipPayment payment : payments.values()){
            if(!payment.getIsPaid()){
                int paymentID = payment.getPaymentID();
                membersNotPaid.add(paymentID);
            }


        }
        return membersNotPaid;
    }


}
