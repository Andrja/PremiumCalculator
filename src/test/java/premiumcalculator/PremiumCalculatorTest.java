package premiumcalculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import premiumcalculator.dto.Policy;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PremiumCalculatorTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testPremiumCalculatorFirstCase() {

        Policy policy = null;
        try {
            policy = (Policy) jsonFileToAnyClass(
                    "incoming/PolicyFirst.json", Policy.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseEntity<String> res = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/countpremium",
                policy, String.class);

        Assert.assertEquals(200, res.getStatusCodeValue());
        Assert.assertEquals("2.28 EUR", res.getBody());

    }

    @Test
    public void testPremiumCalculatorSecondCase() {

        Policy policy = null;
        try {
            policy = (Policy) jsonFileToAnyClass(
                    "incoming/PolicySecond.json", Policy.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseEntity<String> res = this.restTemplate.postForEntity(
                "http://localhost:" + port + "/countpremium",
                policy, String.class);

        Assert.assertEquals(200, res.getStatusCodeValue());
        Assert.assertEquals("17.13 EUR", res.getBody());

    }

    private Object jsonFileToAnyClass(String fileName, Class someClass) throws IOException {
        ClassLoader classLoader = PremiumCalculatorTest.class.getClassLoader();
        URL url = classLoader.getResource(fileName);
        Assert.assertNotNull(url);
        File file = new File(url.getFile());
        return mapper.readValue(file, someClass);
    }

}