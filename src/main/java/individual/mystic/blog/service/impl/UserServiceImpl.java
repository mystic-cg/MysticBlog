package individual.mystic.blog.service.impl;

import individual.mystic.blog.dao.UserDAO;
import individual.mystic.blog.pojo.User;
import individual.mystic.blog.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public Mono<User> save(User user) {
        return userDAO.save(user)
                .doOnError(System.out::println)
                .onErrorResume(e -> userDAO.findUserByUserName(user.getUserName())
                        .flatMap(originUser -> {
                            user.setUserID(originUser.getUserID());
                            return userDAO.save(user);
                        })
                );
    }

    @Override
    public Mono<User> find(Integer id) {
        return null;
    }

    @Override
    public Mono<User> find(String name) {
        return userDAO.findUserByUserName(name);
    }

    @Override
    public Mono<Integer> remove(String name) {
        return userDAO.deleteUserByUserName(name);
    }
}
