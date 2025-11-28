package main.java.controller;

import main.java.logic.MemberManager;
import main.java.logic.PaymentManager;
import main.java.membership.MembershipPayment;
import main.java.util.ScannerHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static main.java.membership.MembershipPayment.futurePaymentHeader;


public class FinanceController {
    private ScannerHelper sh;
    private MemberManager memberManager;
    private PaymentManager paymentManager;

    public FinanceController(ScannerHelper sc, MemberManager memberManager, PaymentManager paymentManager) {
        this.sh = sc;
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
        int memberID = sh.selectMemberFromList();
        ArrayList<Integer> paymentList = paymentManager.paymentIDByMember(memberID);
        MembershipPayment payment;
        int viewCount = 0;
        int paymentID = 0;
        for (int id : paymentList) {
            payment = paymentManager.getPayment(id);
            boolean isPaid = payment.getIsPaid();

            if (!isPaid) {
                viewCount++;
                System.out.println(viewCount + ". " + payment.paymentString());

            }
        }

        System.out.println("\nVælg betaling fra listen");
        int userSelect = sh.navigateMenu(viewCount);
        userSelect--;
        paymentID = paymentList.get(userSelect);
        payment = paymentManager.getPayment(paymentID);


        boolean setPaid = sh.askConfirmYesNo("Vil du registrere denne betaling som betalt");

        if (setPaid) {

            payment.setIsPaid(true);
            System.out.println("Betaling for " + payment.paymentString() + " er betalt");
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
        ArrayList<Integer> notPaidList = paymentManager.notPaidIDs();
        for (int p : notPaidList) {
            System.out.println(paymentManager.getPayment(p) );
        }
    }

    private void futurePaymentsList() {
        ArrayList<Integer> futurePayments = paymentManager.futurePaymentsList();
        System.out.println("\n" + futurePaymentHeader());
        for (int p : futurePayments) {
            MembershipPayment thisPayment = paymentManager.getPayment(p);
            System.out.println(thisPayment.futurePaymentString() );

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
