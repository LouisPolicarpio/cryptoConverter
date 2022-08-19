package model.API;

import model.API.inputAPI.InputAPI;
import model.API.inputAPI.OfflineInputAPI;
import model.API.inputAPI.OnlineInputAPI;
import model.API.outputAPI.OfflineOutputAPI;
import model.API.outputAPI.OnlineOutputAPI;
import model.API.outputAPI.OutputAPI;

public class ModelFacade {

    private InputAPI input;
    private OutputAPI output;

    public ModelFacade(String inStatus, String outStatus) {
        System.out.println(inStatus);
        System.out.println(outStatus);

        if(inStatus.equals("online")){
            this.input = new OnlineInputAPI();
        }else{
            this.input = new OfflineInputAPI();
        }

        if(outStatus.equals("online")){
            this.output =  new OnlineOutputAPI();
        }else{
            this.output =  new OfflineOutputAPI();
        }
    }

    public InputAPI getInput() {
        return input;
    }

    public OutputAPI getOutput() {
        return output;
    }

}
