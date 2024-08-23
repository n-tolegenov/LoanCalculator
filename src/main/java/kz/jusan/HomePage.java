package kz.jusan;

import kz.jusan.model.LoanFormModel;
import kz.jusan.response.LoanDetails;
import kz.jusan.service.LoanCalculatorService;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.validation.validator.RangeValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomePage extends WebPage {

    private LoanFormModel loanFormModel;
    private LoanCalculatorService loanCalculatorService;
    private List<LoanDetails> loanDetailsList;

    public HomePage() {

        add(new Label("message", new Model<>("Loan Calculator")));

        loanFormModel = new LoanFormModel();
        loanCalculatorService = new LoanCalculatorService();
        loanDetailsList = new ArrayList<>();

        Form<LoanFormModel> formN = new Form<>("form", new Model<>(loanFormModel));
        add(formN);

        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        formN.add(feedbackPanel);

        DateTextField dateOfLoanField = new DateTextField("dateOfLoan", new PropertyModel<>(loanFormModel, "dateOfLoan"), "yyyy-MM-dd");
        dateOfLoanField.setRequired(true);
        formN.add(dateOfLoanField);

        NumberTextField<Integer> loanTermField = new NumberTextField<>("loanTerm", new PropertyModel<>(loanFormModel, "loanTerm"), Integer.class);
        loanTermField.setRequired(true);
        loanTermField.add(RangeValidator.range(1, 360));
        formN.add(loanTermField);

        NumberTextField<Integer> loanAmountField = new NumberTextField<>("loanAmount", new PropertyModel<>(loanFormModel, "loanAmount"), Integer.class);
        loanAmountField.setRequired(true);
        loanAmountField.add(RangeValidator.range(50000, 20000000));
        formN.add(loanAmountField);

        NumberTextField<Double> interestRateField = new NumberTextField<>("interestRate", new PropertyModel<>(loanFormModel, "interestRate"), Double.class);
        interestRateField.setRequired(true);
        interestRateField.add(RangeValidator.range(0.0, 100.0));
        formN.add(interestRateField);

        ListView<LoanDetails> loanDetailsListView = new ListView<LoanDetails>("rows", new ListModel<>(loanDetailsList)) {
            @Override
            protected void populateItem(org.apache.wicket.markup.html.list.ListItem<LoanDetails> item) {
                LoanDetails details = item.getModelObject();
                item.add(new Label("id", details.getId()));
                item.add(new Label("paymentDate", details.getPaymentDate()));
                item.add(new Label("numberOfDays", details.getNumberOfDays()));
                item.add(new Label("principalDebt", details.getPrincipalDebt()));
                item.add(new Label("interest", details.getInterest()));
                item.add(new Label("remaining", details.getRemaining()));
                item.add(new Label("paymentAmount", details.getPaymentAmount()));
            }
        };

        add(loanDetailsListView);

        formN.add(new Button("submit") {
            @Override
            public void onSubmit() {
                super.onSubmit();
                loanDetailsList = loanCalculatorService.calculateLoanDetails(loanFormModel);
                loanDetailsListView.setModel(new ListModel<>(loanDetailsList));
            }
        });
    }
}