package model.cryptoInfo;

import model.cryptoInfo.Info;
import model.cryptoInfo.Urls;

import java.util.ArrayList;
import java.util.List;

public class InfoList {

    private List<Info> infoList;

    public InfoList() {
        this.infoList = new ArrayList<>();
    }


    public void addCurrency(int cur_id, String logo, String cur_name, String symbol, String description, String date_launched, String website) throws Exception {
        if(infoList.stream().anyMatch(item -> item.getId().equals(Integer.toString(cur_id)))){

            throw  new IllegalArgumentException("Already Added");
        }
        Info info = new Info(new Urls(website),logo,Integer.toString(cur_id),cur_name,symbol,description,date_launched);
        this.infoList.add(info);
    }


    public List<Info> getAllCurrency(){

        return this.infoList;
    }

    public void delCurrency(int cur_id)  {
        if (this.infoList.size() == 0){
            throw  new IllegalStateException ("List Empty");
        }
        this.infoList.removeIf(info -> info.getId().equals(Integer.toString(cur_id)));
    }

    public void delAllCurrency() {
        if (this.infoList.size() == 0){
            throw  new IllegalStateException ("List Empty");
        }
        this.infoList  = new ArrayList<>();

    }
}
