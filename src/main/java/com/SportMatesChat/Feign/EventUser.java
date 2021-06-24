package com.SportMatesChat.Feign;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="posts", url="${sportMatesApi.url}")
public interface EventUser {

    @GetMapping("/eventUser/{eventId}/{userId}")
    boolean userIsInscriptedToEvent(@PathVariable("eventId") int eventId, @PathVariable("userId") int userId);

}
