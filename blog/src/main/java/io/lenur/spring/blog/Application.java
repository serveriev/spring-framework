package io.lenur.spring.blog;

import io.lenur.spring.blog.config.HibernateConfig;
import io.lenur.spring.blog.config.PackageConfig;
import io.lenur.spring.blog.domain.Post;
import io.lenur.spring.blog.domain.User;
import io.lenur.spring.blog.service.PostService;
import io.lenur.spring.blog.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    private static final AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(PackageConfig.class, HibernateConfig.class);

    private static final UserService userService = context
            .getBean(UserService.class);

    private static final PostService postService = context
            .getBean(PostService.class);

    public static void main(String[] args) {
        User user = new User();
        user.setName("John");
        userService.create(user);

        Post post = new Post();
        post.setText("Test post");
        post.addUser(user);
        postService.create(post);
    }
}
