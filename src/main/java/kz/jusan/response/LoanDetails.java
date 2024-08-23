package kz.jusan.response;

import java.time.LocalDate;

public class LoanDetails {
    private Long id;
    private LocalDate paymentDate;
    private Integer numberOfDays;
    private Double principalDebt;
    private Double interest;
    private Double remaining;
    private Double paymentAmount;

    public LoanDetails() {
    }

    public LoanDetails(Long id, LocalDate paymentDate, Integer numberOfDays, Double principalDebt, Double interest, Double remaining, Double paymentAmount) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.numberOfDays = numberOfDays;
        this.principalDebt = principalDebt;
        this.interest = interest;
        this.remaining = remaining;
        this.paymentAmount = paymentAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Double getPrincipalDebt() {
        return principalDebt;
    }

    public void setPrincipalDebt(Double principalDebt) {
        this.principalDebt = principalDebt;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String toString() {
        return "LoanDetails{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", numberOfDays=" + numberOfDays +
                ", principalDebt=" + principalDebt +
                ", interest=" + interest +
                ", remaining=" + remaining +
                ", paymentAmount=" + paymentAmount +
                '}';
    }
}
