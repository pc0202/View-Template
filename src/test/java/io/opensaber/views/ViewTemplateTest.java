package io.opensaber.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ViewTemplateTest {

    private ViewTemplate vt;

    @Before
    public void init() {
        vt = new ViewTemplate();
        FunctionDefinition fd = new FunctionDefinition();
        fd.setName("name");
        fd.setResult("expression");
        List<FunctionDefinition> fds = new ArrayList<>();
        fds.add(fd);
        vt.setFunctionDefinitions(fds);
    }

    @Test
    public void testGetExpression() {

        String result = vt.getExpression("name");

        assertEquals(vt.getFunctionDefinitions().get(0).getResult(), result);
        assertNotEquals("unexpected", result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetExpressionException() {

        vt.getExpression("invalid_name");

    }

}
