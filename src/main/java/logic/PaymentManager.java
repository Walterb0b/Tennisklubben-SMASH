package main.java.logic;

import main.java.membership.Member;
import main.java.membership.MembershipPayment;
import main.java.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class PaymentManager {
    private MemberManager memberManager;
    private HashMap<Integer, MembershipPayment> payments;
    private int firstQuarterWeekdays;

    public PaymentManager(MemberManager memberManager){
        this.memberManager = memberManager;
        this.payments = new HashMap<>();
        //this.firstQuarterWeekdays = 5;
    }

    public void addPayment(MembershipPayment membershipPayment) {
        payments.put(membershipPayment.getPaymentID(), membershipPayment);
    }
/*
    public void createSeasonQuarterPaymentOLD(int year){
        int yearValid = Validator.yearValidator(year);
        String yearValidShort = String.valueOf(yearValid).substring(2);
        String[] quarters = {"Q1", "Q2", "Q3", "Q4"};
        for (String q : quarters) {
            for (Member m : memberManager.getAllMembers().values()) {
                String seasonQuarter = yearValidShort + q;
                addPayment(new MembershipPayment(m,seasonQuarter));
                //MembershipPayment msp = new MembershipPayment(m, seasonQuarter);
                //payments.put(msp.getPaymentID(), msp);
            }
        }
    }

 */

    public void createSeasonQuarterPayment(int year){
        int yearValid = Validator.yearValidator(year);
        String yearValidShort = String.valueOf(yearValid).substring(2);
        //String[] quarters = {"Q1", "Q2", "Q3", "Q4"};
        LocalDate[] dateQuarters = {
                LocalDate.of(year, 1, 1),
                LocalDate.of(year, 4, 1),
                LocalDate.of(year, 7, 1),
                LocalDate.of(year,10,1)
                };
        for (LocalDate d : dateQuarters) {
            for (Member m : memberManager.getAllMembers().values()) {
                //String seasonQuarter = yearValidShort + q;
                addPayment(new MembershipPayment(m,d));
                //MembershipPayment msp = new MembershipPayment(m, seasonQuarter);
                //payments.put(msp.getPaymentID(), msp);
            }
        }
    }

    public void printAllPayments() {
        for (MembershipPayment p : payments.values()) {
            System.out.println(p);
        }
    }

    public HashMap<Integer, MembershipPayment> getAllPayments() {
        return payments;
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


    public ArrayList<Integer> notPaidIDs(){
        ArrayList<Integer> membersNotPaid = new ArrayList<>();
        for (MembershipPayment payment : payments.values()){
            boolean pastPayment = payment.getDueDate().isBefore(LocalDate.now());
            if(!payment.getIsPaid() && pastPayment){
                int paymentID = payment.getPaymentID();
                membersNotPaid.add(paymentID);
            }
        }

        return membersNotPaid;
    }

    public ArrayList<Integer> paymentIDByMember(int memberID){
        ArrayList<Integer> paymentIDs = new ArrayList<>();
        for (MembershipPayment payment : payments.values()){
            if(payment.getMemberID() == memberID){
                int paymentID = payment.getPaymentID();
                paymentIDs.add(paymentID);
            }
        }

        return paymentIDs;
    }

    public ArrayList<Integer> futurePaymentsList(){
        ArrayList<Integer> futurePayments = new ArrayList<>();
        for (MembershipPayment payment : payments.values()){
            boolean futurePayment = payment.getDueDate().isAfter(LocalDate.now());
            if(futurePayment){
                int paymentID = payment.getPaymentID();
                futurePayments.add(paymentID);
            }
        }

        return futurePayments;
    }

    public double calculateQuarterSum(String seasonQuarter){
        Double sum = 0.0;
        for (MembershipPayment p : payments.values()) {
            if (p.getSeasonQuarter().equalsIgnoreCase(seasonQuarter)) {
                sum = sum + p.getAmount();
            }
        }
        return sum;
    }
    /*
    public ArrayList<String> futureSeasonQuarters() {
        ArrayList<String> seasonQuarters;

        for (MembershipPayment p : payments.values()) {
            if (p.getSeasonQuarter().equalsIgnoreCase(seasonQuarter)) {
                sum = sum + p.getAmount();
            }
        }
    }
    */




}
