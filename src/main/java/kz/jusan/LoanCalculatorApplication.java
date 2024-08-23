package kz.jusan;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class LoanCalculatorApplication extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }

    @Override
    public void init() {
        super.init();
    }

}
