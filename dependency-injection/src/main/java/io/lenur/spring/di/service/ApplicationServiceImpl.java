package io.lenur.spring.di.service;

import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Override
    public void print() {
        System.out.println(ApplicationServiceImpl.class);
    }
}
