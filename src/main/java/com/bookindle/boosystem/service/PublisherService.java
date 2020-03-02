package com.bookindle.boosystem.service;

import com.bookindle.boosystem.entity.Publisher;
import com.bookindle.boosystem.repository.PublisherRepostory;
import org.springframework.beans.factory.annotation.Autowired;

public interface PublisherService {
    Publisher publisherSave(Publisher publisher);
}
