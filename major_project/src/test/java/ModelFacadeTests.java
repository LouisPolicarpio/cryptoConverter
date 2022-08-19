import com.google.gson.JsonSyntaxException;
import model.API.Http;
import model.API.ModelFacade;
import model.API.inputAPI.OnlineInputAPI;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ModelFacadeTests {

    @Test
    public void ModelFacadeOffline() {
        ModelFacade modelFacade = new ModelFacade("offline", "offline");

        assertThat(String.valueOf(modelFacade.getInput().getClass()), equalTo("class model.API.inputAPI.OfflineInputAPI"));
        assertThat(String.valueOf(modelFacade.getOutput().getClass()), equalTo("class model.API.outputAPI.OfflineOutputAPI"));

    }

    @Test
    public void ModelFacadeOnline() {
        ModelFacade modelFacade = new ModelFacade("online", "online");

        assertThat(String.valueOf(modelFacade.getInput().getClass()), equalTo("class model.API.inputAPI.OnlineInputAPI"));
        assertThat(String.valueOf(modelFacade.getOutput().getClass()), equalTo("class model.API.outputAPI.OnlineOutputAPI"));

    }

    @Test
    public void ModelFacadeOnlineOffline() {
        ModelFacade modelFacade = new ModelFacade("online", "offline");

        assertThat(String.valueOf(modelFacade.getInput().getClass()), equalTo("class model.API.inputAPI.OnlineInputAPI"));
        assertThat(String.valueOf(modelFacade.getOutput().getClass()), equalTo("class model.API.outputAPI.OfflineOutputAPI"));

    }

    @Test
    public void ModelFacadeOfflineOnline() {
        ModelFacade modelFacade = new ModelFacade("offline", "online");

        assertThat(String.valueOf(modelFacade.getInput().getClass()), equalTo("class model.API.inputAPI.OfflineInputAPI"));
        assertThat(String.valueOf(modelFacade.getOutput().getClass()), equalTo("class model.API.outputAPI.OnlineOutputAPI"));

    }
}
