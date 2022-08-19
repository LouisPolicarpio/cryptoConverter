import model.cryptoInfo.Info;
import model.cryptoInfo.InfoList;
import model.cryptoInfo.Urls;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.MockedConstruction;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class InfoListTests {
    private InfoList infoList;


    @BeforeEach
    void setup(){
        infoList  = new InfoList();
    }

    @Test
    public void addCurrencyTestSuccessBase() throws Exception {
        infoList.addCurrency(1,"logo","cur_name", "symbol", "description", "date",  "website");

        Urls url = new Urls("website");
        Info info =  new Info(url,"logo","1", "cur_name", "symbol", "description",  "date");

        assertThat(infoList.getAllCurrency().get(0), samePropertyValuesAs(info,"urls") );
        assertThat(infoList.getAllCurrency().get(0).getUrls().getWebsite()[0], equalTo(info.getUrls().getWebsite()[0] ));
    }

    @Test
    public void addCurrencyTestSuccessMultiAdd() throws Exception {
        infoList.addCurrency(1,"logo","cur_name", "symbol", "description", "date",  "website");
        infoList.addCurrency(2,"logo","cur_name", "symbol", "description", "date",  "website2");

        Urls url = new Urls("website");
        Urls url2 = new Urls("website2");
        Info info =  new Info(url,"logo","1", "cur_name", "symbol", "description",  "date");
        Info info2 =  new Info(url2,"logo","2", "cur_name", "symbol", "description",  "date");

        assertThat(infoList.getAllCurrency().get(0), samePropertyValuesAs(info,"urls") );
        assertThat(infoList.getAllCurrency().get(1), samePropertyValuesAs(info2,"urls") );

        assertThat(infoList.getAllCurrency().get(0).getUrls().getWebsite()[0], equalTo(info.getUrls().getWebsite()[0] ));
        assertThat(infoList.getAllCurrency().get(1).getUrls().getWebsite()[0], equalTo(info2.getUrls().getWebsite()[0] ));
    }

    @Test
    public void addCurrencyTestSuccessAlreadyAdded() throws Exception {
        infoList.addCurrency(2,"logo","cur_name", "symbol", "description", "date",  "website");
        assertThrows(IllegalArgumentException.class, () -> {
            infoList.addCurrency(2,"logo","cur_name", "symbol", "description", "date",  "website2");
        });

    }

    @Test
    public void delCurrencyBase() throws Exception {
        infoList.addCurrency(1,"logo","cur_name", "symbol", "description", "date",  "website");
        infoList.addCurrency(2,"logo","cur_name", "symbol", "description", "date",  "website2");

        infoList.delCurrency(1);

        Urls url2 = new Urls("website2");
        Info info2 =  new Info(url2,"logo","2", "cur_name", "symbol", "description",  "date");

        assertThat(infoList.getAllCurrency().size(), equalTo(1) );
        assertThat(infoList.getAllCurrency().get(0), samePropertyValuesAs(info2,"urls") );
        assertThat(infoList.getAllCurrency().get(0).getUrls().getWebsite()[0], equalTo(info2.getUrls().getWebsite()[0] ));

    }

    @Test
    public void delCurrencyEmpty() throws Exception {
        assertThrows(IllegalStateException.class, () -> {
            infoList.delCurrency(1);
        });
    }

    @Test
    public void delAllCurrencyBase() throws Exception {
        infoList.addCurrency(1,"logo","cur_name", "symbol", "description", "date",  "website");
        infoList.addCurrency(2,"logo","cur_name", "symbol", "description", "date",  "website2");

        infoList.delAllCurrency();
        assertThat(infoList.getAllCurrency().size(), equalTo(0) );

    }

    @Test
    public void delAllCurrencyBaseEmpty() throws Exception {
        assertThrows(IllegalStateException.class, () -> {
            infoList.delAllCurrency();
        });
    }

}
