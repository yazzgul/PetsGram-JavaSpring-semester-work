package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.CatFact;

public interface CatFactRepository extends JpaRepository<CatFact, Long> {

//    Boolean existsCatFactByCatFact(CatFact catFact);

//    CatFact findCatFactByCatFact(CatFact catFact);
//    CatFact findByFact(String fact);
//    CatFact findCatFactByFact(String fact);

}
