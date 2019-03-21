import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.opensaber.views.Transformer;
import io.opensaber.views.ViewTemplate;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class VTMain {

    public static void personExample() {
        try {
            String personJson = "{\"Person\": " +
                    "               {\"nationalIdentifier\":\"nid823\"," +
                    "                \"firstName\":\"Ram\"," +
                    "                \"lastName\":\"Moorthy\"," +
                    "                \"gender\":\"MALE\"," +
                    "                \"dob\":\"1990-12-10\"}}";
            ObjectNode personNode = (ObjectNode) new ObjectMapper().readTree(personJson);

            // read from the ViewTemplate
            String viewTemplateJson = readFileContent("personVT1.json");
            ViewTemplate viewTemplate = new ObjectMapper().readValue(viewTemplateJson, ViewTemplate.class);

            // transform action
            System.out.println("Person record " + personNode);
            System.out.println("Person from view template" + new Transformer().transform(viewTemplate, personNode));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void simpleMathExample() {
        try {
            String mathProblem = "{\"Math\": " +
                    "               {\"a\": 5," +
                    "                \"b\": 2 }}";
            ObjectNode personNode = (ObjectNode) new ObjectMapper().readTree(mathProblem);

            // read from the ViewTemplate
            String viewTemplateJson = readFileContent("mathVT1.json");
            ViewTemplate viewTemplate = new ObjectMapper().readValue(viewTemplateJson, ViewTemplate.class);

            // transform action
            System.out.println("Maths record " + mathProblem);
            System.out.println("Maths from view templates " + new Transformer().transform(viewTemplate, personNode));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("**************************");
        personExample();
        System.out.println("**************************");
        simpleMathExample();
        System.out.println("**************************");
    }

    private static String readFileContent(String fileName) {
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
