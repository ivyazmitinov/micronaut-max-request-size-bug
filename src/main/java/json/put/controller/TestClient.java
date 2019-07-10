package json.put.controller;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import java.util.List;
import java.util.Map;

@Client("/test")
public interface TestClient
{
    @Post
    Single<Integer> test(@Body List<Map<String, String>> object);
}
