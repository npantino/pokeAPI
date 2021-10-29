package com.example.http;

import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    public SearchHistory save(@NotBlank String name) {
        SearchHistory searchHistory = new SearchHistory(name);
        entityManager.persist(searchHistory);
        return searchHistory;
    }

    @Override
    public void delete(@NotNull Long id) {
        SearchHistory searchHistory = this.findById(id).get();
        entityManager.remove(searchHistory);
    }

    public void deletex(@NotNull Long id) {
        SearchHistory searchHistory = this.findById();
        entityManager.remove(searchHistory);
    }
}
