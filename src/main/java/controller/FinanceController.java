package main.java.controller;

import main.java.logic.MemberManager;
import main.java.logic.PaymentManager;
import main.java.membership.MembershipPayment;
import main.java.util.FileHandler;
import main.java.util.Formatter;
import main.java.util.ScannerHelper;

import java.util.ArrayList;

import static main.java.membership.MembershipPayment.futurePaymentHeader;


public class FinanceController {
    private ScannerHelper sh;
    private FileHandler fh;
    private MemberManager memberManager;
    private PaymentManager paymentManager;

    public FinanceController(ScannerHelper sh, FileHandler fh, MemberManager memberManager, PaymentManager paymentManager) {
        this.sh = sh;
        this.fh = fh;
        this.memberManager = memberManager;
        this.paymentManager = paymentManager;
    }

    public void run()  {
        boolean running = true;
        while(running) {
            sh.printFinanceMenu();
            int choice = sh.navigateMenu(3);


            switch (choice) {
                case 1:
                    //tilføj betaling
                    registerPayment();
                    fh.savePaymentsToCSV();
                    break;
                case 2:
                    //vis medlemmer i restance
                    missingPaymentsList();
                    break;
                case 3:
                    futurePaymentsList();
                    printNextSeasonQuarterSum();
                    break;
                case 0:
                    //et skridt tilbage
                    // throw exception eller kør hovedmenu?
                    running = false;
                case -1:
                    //tilbage til hovedmenu
                    running = false;

            }

        }

    }

    private void registerPayment() {
        boolean registerCorrect = false;

        while(!registerCorrect) {
            int memberID = sh.selectMemberFromList();
            ArrayList<Integer> paymentList = paymentManager.paymentIDByMember(memberID);
            MembershipPayment payment;
            int viewCount = 0;
            int paymentID = 0;
            ArrayList<Integer> notPaidList = new ArrayList<>();
            for (int id : paymentList) {
                payment = paymentManager.getPayment(id);
                boolean isPaid = payment.getIsPaid();

                if (!isPaid) {
                    notPaidList.add(id);
                }
            }


            int userSelect = 0;
            if (notPaidList.isEmpty()) {
                System.out.println("Denne bruger har ingen udestående betalinger");
                registerCorrect = true;
            } else if (notPaidList.size()==1) {
                System.out.println(paymentManager.getPayment(notPaidList.getFirst()).paymentString());
                userSelect = 1;
            } else {
                for (int p : notPaidList) {
                    viewCount++;
                    System.out.println(viewCount + ". " + paymentManager.getPayment(p).paymentString());
                }
                System.out.println("\nVælg betaling fra listen");
                userSelect = sh.navigateMenu(viewCount);
            }

            if (!notPaidList.isEmpty()) {
                userSelect--;

                paymentID = notPaidList.get(userSelect);
                payment = paymentManager.getPayment(paymentID);


                boolean setPaid = sh.askConfirmYesNo("Vil du registrere denne betaling som betalt");

                if (setPaid) {

                    payment.setIsPaid(true);
                    System.out.println("Betaling for " + payment.paymentString() + " er betalt");
                }
                registerCorrect = true;
            }


        }

    }

        //PSEUDO KODE

        //Fremsøg medlem-metode
        //memberManager.searchMember()
        //viser liste over medlemmer
        //vælg medlem
        //vis kvartaler, der ikke er betalt
        //vælg kvartal til betaling



    private void missingPaymentsList() {
        ArrayList<Integer> membersNotPaid = paymentManager.notPaidIDs();
        ArrayList<String> restanceList = new ArrayList<>();
        String singleLine = "";
        System.out.println("\n" + futurePaymentHeader());
        String name = "";
        int memberID = 0;
        String notPaidQuarters = "";
        double notPaidSum = 0.0;
        for (int i = 0; i < membersNotPaid.size(); i++) {
            MembershipPayment thisPayment = paymentManager.getPayment(membersNotPaid.get(i));

            if (i == 0) {
                name = thisPayment.getMember().getName();
                memberID = thisPayment.getMemberID();
                notPaidQuarters = thisPayment.getSeasonQuarter();
                notPaidSum = thisPayment.getAmount();

            } else {
                if (thisPayment.getMemberID() == memberID) {
                    notPaidSum = notPaidSum + thisPayment.getAmount();
                    notPaidQuarters = notPaidQuarters + ", " + thisPayment.getSeasonQuarter();
                } else {
                    singleLine = Formatter.displayPaymentString(name, memberID, notPaidQuarters, notPaidSum);
                    System.out.println(singleLine);
                    name = thisPayment.getMember().getName();
                    memberID = thisPayment.getMemberID();
                    notPaidQuarters = thisPayment.getSeasonQuarter();
                    notPaidSum = thisPayment.getAmount();
                }

            }


            //System.out.println(thisPayment.displayPaymentString());
        }
        System.out.println("\n");
    }

    private void futurePaymentsList() {
        ArrayList<Integer> membersNotPaid = paymentManager.futurePaymentsList();

        String singleLine = "";
        System.out.println("\n" + futurePaymentHeader());
        String name = "";
        int memberID = 0;
        String notPaidQuarters = "";
        double notPaidSum = 0.0;
        for (int i = 0; i < membersNotPaid.size(); i++) {
            MembershipPayment thisPayment = paymentManager.getPayment(membersNotPaid.get(i));

            if (i == 0) {
                name = thisPayment.getMember().getName();
                memberID = thisPayment.getMemberID();
                notPaidQuarters = thisPayment.getSeasonQuarter();
                notPaidSum = thisPayment.getAmount();

            } else {
                if (thisPayment.getMemberID() == memberID) {
                    notPaidSum = notPaidSum + thisPayment.getAmount();
                    notPaidQuarters = notPaidQuarters + ", " + thisPayment.getSeasonQuarter();
                } else {
                    singleLine = Formatter.displayPaymentString(name, memberID, notPaidQuarters, notPaidSum);
                    System.out.println(singleLine);
                    name = thisPayment.getMember().getName();
                    memberID = thisPayment.getMemberID();
                    notPaidQuarters = thisPayment.getSeasonQuarter();
                    notPaidSum = thisPayment.getAmount();
                }

            }


            //System.out.println(thisPayment.displayPaymentString());
        }
        System.out.println("\n");
    }

    private void futurePaymentsListOLD() {
        ArrayList<Integer> futurePayments = paymentManager.futurePaymentsList();
        System.out.println("\n" + futurePaymentHeader());
        for (Integer p : futurePayments) {
            MembershipPayment thisPayment = paymentManager.getPayment(p);
            System.out.println(thisPayment.displayPaymentString() );

        }
        System.out.println("\n");

    }

    private void printNextSeasonQuarterSum() {
        String[] seasonQuarters = {"26Q1", "26Q2", "26Q3", "26Q4"};
        for (String sq : seasonQuarters) {
            System.out.println(sq + " sum: " + paymentManager.calculateQuarterSum(sq) + " kr.");
        }
        System.out.println("\n");

    }


}
