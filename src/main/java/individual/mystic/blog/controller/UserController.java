package individual.mystic.blog.controller;

import individual.mystic.blog.pojo.User;
import individual.mystic.blog.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Api("User Controller")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * narrow the request mapping based on the 'Content-Type' of the request
     * only permit 'application/json' request
     *
     * @param user user
     * @return user
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> save(@RequestBody User user) {
        return userService.save(user);
    }

    /**
     * narrow the request mapping based on the 'Content-Type' of the request
     * !text/plain means any content type other than text/plain
     *
     * @param user user
     * @return the result of delete user
     */
    @DeleteMapping(consumes = "!text/plain")
    public Mono<Void> remove(@RequestBody User user) {
        return userService.deleteByName(user.getUsername());
    }

}
