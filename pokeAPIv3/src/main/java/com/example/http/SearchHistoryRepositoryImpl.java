package com.example.http;

import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Singleton
public class SearchHistoryRepositoryImpl implements SearchHistoryRepository {
    private final EntityManager entityManager;

    public SearchHistoryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @ReadOnly
    public Optional<SearchHistory> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(SearchHistory.class, id));
    }

    @Override
    @Transactional
    public List<SearchHistory> findAll() {
        String queryString = "select name from search_history order by id desc";
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "my-secret-pw");
        Statement st = conn.createStatement();
    }

    @Override
    @Transactional
    public SearchHistory save(@NotBlank String name) {
        SearchHistory searchHistory = new SearchHistory(name);
        entityManager.persist(searchHistory);
        return searchHistory;
    }

    @Override
    @Transactional
    public void delete(@NotNull Long id) {
        SearchHistory searchHistory = this.findById(id).get();
        entityManager.remove(searchHistory);
    }

    @Override
    @Transactional
    public void deleteAll() {
        String queryString = "delete from SearchHistory";
        Query query = entityManager.createQuery(queryString);
        query.executeUpdate();
    }
}
