package com.example.apitest.repository;

import com.example.apitest.domain.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @NonNull
    List<User> findAll();

    @NonNull
    Optional<User> findById(@NonNull Integer integer);

    void deleteById(@NonNull Integer integer);
}
