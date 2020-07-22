import com.fasterxml.jackson.databind.ObjectMapper;
import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.ApiWrapper;
import com.theghostspirit.ream.generator.core.ExportModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

public class ExportModelTest {

    String path = "./src/test/resources/";

    ExportModel exportModel = new ExportModel();

    /*
    @Mock
    private Api mockApi;
    private Api toTest = spy( new Api());
    */
    private Api toTest = new Api();


    @Test
    public void testGetJsonModel(){

        toTest.setName("Test");
        toTest.setServerPath("https://localhost:4200");

        exportModel.getJsonModel(this.toTest,this.path);

        try{
            String content = new String(Files.readAllBytes(Paths.get(path)));


        ObjectMapper objectMapper = new ObjectMapper();

        try{
            ApiWrapper apiWrapper = objectMapper.readValue(content, ApiWrapper.class);


       assertEquals(apiWrapper.getApi(), toTest);

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
    }

}
}
