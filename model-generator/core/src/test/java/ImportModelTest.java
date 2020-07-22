import com.theghostspirit.ream.generator.core.Api;
import com.theghostspirit.ream.generator.core.ImportModel;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImportModelTest {

    String path = "./src/test/resources/BurgerApi.json";
    String path2 = "./src/test/resources/BurgerApi-w.json";
    String path3 = "./src/test/resources/BurgerApi.yaml";
    String path4 = "./src/test/resources/BurgerApi-w.yaml";

    private ImportModel importModel = new ImportModel();


    @Test
    public void testApiImport(){

        Api validApi = this.importModel.ImportJsonFile(path);
        assertEquals(validApi.getName(), "BurgerApi");
        Api invalidApi = this.importModel.ImportJsonFile(path2);
        assertTrue(invalidApi.getName().isBlank());

        Api yamlApi = this.importModel.ImportJsonFile(path3);
        assertEquals(yamlApi.getName(), "BurgerApi");
        Api invalidYaml = this.importModel.ImportJsonFile(path4);
        assertTrue(invalidYaml.getName().isBlank());

    }

}
