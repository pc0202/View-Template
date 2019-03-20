import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.opensaber.operations.VMUtils;
import io.opensaber.template.pojo.ViewTemplate;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class VTMain {

    public static void main(String[] args) {

        try {
            //build a sample person record
            ObjectNode personNode = JsonNodeFactory.instance.objectNode();
            String personJson = "{\"Person\": {\"nationalIdentifier\":\"124384379\",\"name\":\"Pritha Chattopadhyay\",\"gender\":\"FEMALE\",\"dob\":\"1990-12-10\"}}";
            personNode  = (ObjectNode) new ObjectMapper().readTree(personJson);

            //build the ViewTemplate
            String viewTemplateJson = getContent("viewtemplate.json");
            ViewTemplate viewTemplate = new ObjectMapper().readValue(viewTemplateJson, ViewTemplate.class);
            
            //action:- do the transformation
            VMUtils.transform(viewTemplate, personNode);
            System.out.println("print person node "+personNode);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    private static String getContent(String fileName) {
        InputStream in;
        try {
            in = VTMain.class.getClassLoader().getResourceAsStream(fileName);
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString(StandardCharsets.UTF_8.name());

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

}
