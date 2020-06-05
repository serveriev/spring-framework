package io.lenur.spring.blog.dao.impl;

import io.lenur.spring.blog.dao.PostDao;
import io.lenur.spring.blog.domain.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostDaoImpl extends AbstractDao<Post> implements PostDao {
}
