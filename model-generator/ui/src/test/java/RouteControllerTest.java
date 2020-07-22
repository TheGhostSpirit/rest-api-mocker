import static org.junit.Assert.*;

import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.Route;
import com.theghostspirit.ream.generator.ui.RouteController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class RouteControllerTest {

    private RouteController controller;

    private Api api;

    private Route newRoute;

    @Before
    public void beforeTest() {
        this.newRoute.setMethod("get");
        this.newRoute.setDescription("description route");
        this.newRoute.setPath("/menu");


        this.api = Mockito.mock(Api.class);


    /*    routeUrl = "fjzjda"


    }

    @Test
    public SetApiTest(){
        this.controller.setApi(this.api);
    }

    @Test
    */
    }
}
