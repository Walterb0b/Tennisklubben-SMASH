package main.java.controller;

import main.java.logic.MemberManager;
import main.java.logic.PaymentManager;
import main.java.util.ScannerHelper;


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
                    addQuarterlyPayment();
                case 2:
                    //vis medlemmer i restance
                    missingPaymentsList();
                case 3:

                    futurePaymentsList();
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

    private void addQuarterlyPayment() {
        //PSEUDO KODE

        //Fremsøg medlem-metode
        //memberManager.searchMember()
        //viser liste over medlemmer
        //vælg medlem
        //vis kvartaler, der ikke er betalt
        //vælg kvartal til betaling

    }

    private void missingPaymentsList() {
        //no body
    }

    private void futurePaymentsList() {
        paymentManager.printAllPayments();
    }


}
