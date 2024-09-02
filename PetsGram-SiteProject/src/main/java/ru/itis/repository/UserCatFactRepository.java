package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.CatFact;
import ru.itis.model.User;
import ru.itis.model.UserCatFact;

import java.util.List;

public interface UserCatFactRepository extends JpaRepository<UserCatFact, Long> {
    List<UserCatFact> findAllByCatFact(CatFact catFact);
    List<UserCatFact> findAllByUser(User user);
}
