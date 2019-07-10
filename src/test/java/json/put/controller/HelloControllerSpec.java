package json.put.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.runtime.server.EmbeddedServer;
import io.reactivex.Single;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class HelloControllerSpec
{
    private static EmbeddedServer server;
    private static TestClient client;
    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setupServer()
    {

        server = ApplicationContext.run(EmbeddedServer.class);

        client = server
                     .getApplicationContext()
                     .createBean(TestClient.class, server.getURL());
        objectMapper = server.getApplicationContext().getBean(ObjectMapper.class);
    }

    @AfterAll
    public static void stopServer()
    {
//        if (client != null)
//        {
//            client.stop();
//        }
        if (server != null)
        {
            server.stop();
        }
    }

    @Test
    public void testHelloWorldResponse() throws JsonProcessingException
    {
        List<Map<String, String>> maps = new ArrayList<>();
        int valuesCount = 1000000;
        for (int i = 0; i < valuesCount; i++)
        {
            maps.add(Collections.singletonMap(UUID.randomUUID().toString(), String.valueOf(i)));
        }
        Integer response = client.test(maps).blockingGet();
        assertEquals(valuesCount, (int)response);
    }
}