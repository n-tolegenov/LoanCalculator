package kz.jusan.service;

import kz.jusan.model.LoanFormModel;
import kz.jusan.response.LoanDetails;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LoanCalculatorService {
    public List<LoanDetails> calculateLoanDetails(LoanFormModel loanFormModel) {

        double loanAmount = loanFormModel.getLoanAmount();
        double annualInterestRate = loanFormModel.getInterestRate();
        int months = loanFormModel.getLoanTerm();
        LocalDate startDate = loanFormModel.getDateOfLoan();

        double monthlyInterestRate = annualInterestRate / 100 / 12;
        double monthlyPrincipalPayment = loanAmount / months;

        List<LoanDetails> paymentSchedule = new ArrayList<>();
        double remainingPrincipal = loanAmount;

        LocalDate previousDate = startDate;

        for (int i = 1; i <= months; i++) {

            double interestPayment = remainingPrincipal * monthlyInterestRate; // процентный платеж
            double totalPayment = monthlyPrincipalPayment + interestPayment;

            remainingPrincipal -= monthlyPrincipalPayment;

            LocalDate paymentDate = startDate.plusMonths(i);

            long numberOfDays = ChronoUnit.DAYS.between(previousDate, paymentDate);
            previousDate = paymentDate;

            LoanDetails details = new LoanDetails();
            details.setId((long) i);
            details.setPaymentDate(paymentDate);
            details.setNumberOfDays((int) numberOfDays);
            details.setPrincipalDebt(Math.round(monthlyPrincipalPayment * 100.0) / 100.0);
            details.setInterest(Math.round(interestPayment * 100.0) / 100.0);
            details.setRemaining(remainingPrincipal > 0 ? Math.round(remainingPrincipal * 100.0) / 100.0 : 0);
            details.setPaymentAmount(Math.round(totalPayment * 100.0) / 100.0);

            paymentSchedule.add(details);
        }

        return paymentSchedule;
    }
}
