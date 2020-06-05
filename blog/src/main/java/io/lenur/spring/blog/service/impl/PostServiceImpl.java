package io.lenur.spring.blog.service.impl;

import io.lenur.spring.blog.dao.PostDao;
import io.lenur.spring.blog.domain.Post;
import io.lenur.spring.blog.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private final PostDao postDao;

    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public Post create(Post post) {
        return postDao.create(post);
    }
}
