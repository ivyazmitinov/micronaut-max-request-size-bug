package json.put.controller.controller;

import io.micronaut.context.annotation.Context;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.reactivex.Single;

import java.util.List;
import java.util.Map;

@Context
@Controller("/test")
public class TestController
{

    @Post
    public Single<Integer> test(@Body List<Map<String, String>> object)
    {
        return Single.just(object.size());
    }
}
