package com.easybookstore.service;

import com.easybookstore.entity.Book;

import java.util.List;

/**
 * Created by wesley on 17/5/24.
 */
public interface SearchService {
    public List<Book> proximityBookSearch(String s);
}
