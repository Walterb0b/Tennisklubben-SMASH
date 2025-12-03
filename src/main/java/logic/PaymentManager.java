package main.java.logic;

import main.java.membership.ActiveMembership;
import main.java.membership.Member;
import main.java.membership.MembershipPayment;
import main.java.membership.PassiveMembership;
import main.java.util.FileHandler;
import main.java.util.Formatter;
import main.java.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

    public void createMembershipPaymentWithNewMember(int memberID) {
        Member m = memberManager.getMember(memberID);
        LocalDate signUpDate = m.getSignUpDate();
        int year = signUpDate.getYear();
        LocalDate[] dateQuarters = {
                LocalDate.of(year, 1, 1),
                LocalDate.of(year, 4, 1),
                LocalDate.of(year, 7, 1),
                LocalDate.of(year,10,1),
                LocalDate.of(year, 1, 1).plusYears(1),
                LocalDate.of(year, 4, 1).plusYears(1),
                LocalDate.of(year, 7, 1).plusYears(1),
                LocalDate.of(year,10,1).plusYears(1)
        };

        for (LocalDate d : dateQuarters) {
                boolean dateSearch = m.getSignUpDate().isEqual(d) || m.getSignUpDate().isBefore(d);
                boolean membership = m.getMembership().isActive() || !m.getMembership().isActive(); //medlemmer m aktiv/passiv medlemsskab
                if (dateSearch && membership) {
                    addPayment(new MembershipPayment(m, d));
                }
        }
    }

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
                boolean dateSearch = m.getSignUpDate().isEqual(d) || m.getSignUpDate().isBefore(d);
                boolean membership = m.getMembership().isActive() || !m.getMembership().isActive(); //medlemmer m aktiv/passiv medlemsskab
                if (dateSearch && membership) {
                    addPayment(new MembershipPayment(m, d));
                }
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


    public ArrayList<MembershipPayment> getAllPaymentsSortedByDueDateMemberID() {
        ArrayList<MembershipPayment> paymentsSorted = new ArrayList<MembershipPayment>(payments.values());
        paymentsSorted.sort(byDueDateThenMemberID);
        return paymentsSorted;
    }

    public MembershipPayment getPayment(int paymentID){
        return payments.get(paymentID);
    }

    public void addNextSeason(){
        for (Member m : memberManager.getAllMembers().values()) {
          //
        }
    }

    Comparator<MembershipPayment> byDueDate = Comparator.comparing(MembershipPayment::getDueDate);
    Comparator<MembershipPayment> byMemberID = Comparator.comparing(MembershipPayment::getMemberID);
    Comparator<MembershipPayment> byDueDateThenMemberID = byDueDate.thenComparing(byMemberID);

    public void payFuturePayments(int memberID){}

    private void addQuarterlyPayment(){}

    private void missingPaymentsList(){}


    public ArrayList<Integer> notPaidIDs(){
        ArrayList<Integer> membersNotPaid = new ArrayList<>();
        for (MembershipPayment payment : getAllPaymentsSortedByDueDateMemberID()){
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
        for (MembershipPayment payment : getAllPaymentsSortedByDueDateMemberID()){
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
