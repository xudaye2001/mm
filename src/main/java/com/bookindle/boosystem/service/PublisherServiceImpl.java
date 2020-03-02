package com.bookindle.boosystem.service;

import com.bookindle.boosystem.entity.Publisher;
import com.bookindle.boosystem.repository.PublisherRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService{

    @Autowired
    PublisherRepostory publisherRepostory;

    @Override
    public Publisher publisherSave(Publisher publisher) {
        return publisherRepostory.save(publisher);

    }


}
