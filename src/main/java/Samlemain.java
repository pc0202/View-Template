import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Samlemain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
         String expression = "#/functionDefinitions/concat($name, $nationalIdentifier)";

        String subString = StringUtils.substringBetween(expression, "(", ")");
        String[] commaSeparatedArr = subString.split("\\s*,\\s*");
        List<String> result = Arrays.stream(commaSeparatedArr).collect(Collectors.toList());


    }

}
