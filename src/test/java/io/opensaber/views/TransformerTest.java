package io.opensaber.views;

import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

public class TransformerTest {

    private Transformer transformer = new Transformer();
  
    @Test
    public void testTransformForPersonVT() throws JsonProcessingException, IOException{
        String personJson = "{\"Person\": " +
                "               {\"nationalIdentifier\":\"nid823\"," +
                "                \"firstName\":\"Ram\"," +
                "                \"lastName\":\"Moorthy\"," +
                "                \"gender\":\"MALE\"," +
                "                \"dob\":\"1990-12-10\"}}";
        ObjectNode personNode = (ObjectNode) new ObjectMapper().readTree(personJson);
        String viewTemplateJson = readFileContent("personVT1.json");
        ViewTemplate viewTemplate = new ObjectMapper().readValue(viewTemplateJson, ViewTemplate.class);

        JsonNode actualnode = transformer.transform(viewTemplate, personNode);  

        System.out.println("actualnode = "+actualnode);
        JsonNode expectedNode = new ObjectMapper().readTree("{\"Person\":{\"NAME\":\"Ram\",\"lastName\":\"Moorthy\",\"Name in passport\":\"Moorthy, Ram\"}}");
        System.out.println("expectedNode = "+expectedNode);

        assertEquals(expectedNode, actualnode);

    }
    

    @Test
    public void testTransformForMathVT() throws JsonProcessingException, IOException{
        String mathProblem = "{\"Math\": " +
                "               {\"a\": 5," +
                "                \"b\": 2 }}";
        ObjectNode node = (ObjectNode) new ObjectMapper().readTree(mathProblem);

        String viewTemplateJson = readFileContent("mathVT1.json");
        ViewTemplate viewTemplate = new ObjectMapper().readValue(viewTemplateJson, ViewTemplate.class);

        JsonNode actualnode = transformer.transform(viewTemplate, node); 
        JsonNode expectedNode = new ObjectMapper().readTree("{\"Math\":{\"addend_A\":5,\"addend_B\":2,\"SUM\":\"7\"}}");        
        assertEquals(expectedNode.toString(), actualnode.toString());

    }
  
    private static String readFileContent(String fileName) {
        InputStream in;
        try {
            in = Transformer.class.getClassLoader().getResourceAsStream(fileName);
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
