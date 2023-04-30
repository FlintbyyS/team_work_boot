package ru.flint.team_work_boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.flint.team_work_boot.entity.User;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);
}
