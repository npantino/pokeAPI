package com.example.http;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface SearchHistoryRepository {
    Optional<SearchHistory> findById(@NotNull Long id);
    List<SearchHistory> findAll();
    SearchHistory save(@NotBlank String name);
    void delete(@NotNull Long id);
    void deleteAll();
}
