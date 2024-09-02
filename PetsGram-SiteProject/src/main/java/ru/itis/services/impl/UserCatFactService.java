package ru.itis.services.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dto.CatFactDto;
import ru.itis.model.*;
import ru.itis.repository.CatFactRepository;
import ru.itis.repository.UserCatFactRepository;
import ru.itis.repository.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserCatFactService {
    private final UserRepository userRepository;
    private final CatFactRepository catFactRepository;
    private final UserCatFactRepository userCatFactRepository;


    public List<CatFact> getCatFactListByUserId(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user.getCatFacts();
    }

    public void deleteCatFactByUser(Principal principal, Long catFactId) {
        User user = userRepository.findById(getUserByPrincipal(principal).getId()).orElse(null);
        List<CatFact> catFacts = user.getCatFacts();
        CatFact catFactToRemove = catFacts.stream()
                .filter(catFact -> catFact.getId().equals(catFactId))
                .findFirst()
                .orElse(null);

        catFacts.remove(catFactToRemove);
        user.setCatFacts(catFacts);
        userRepository.save(user);

//        List<UserCatFact> userCatFacts = userCatFactRepository.findAllByCatFact(catFactToRemove);
//        if (!userCatFacts.isEmpty()) {
//            return;
//        }
//        catFactRepository.delete(catFactToRemove);
        checkCatFactInDbAndDeleteIfUserIsEmpty(catFactToRemove);
    }
    public void checkCatFactInDbAndDeleteIfUserIsEmpty(CatFact catFactToRemove) {
        List<UserCatFact> userCatFacts = userCatFactRepository.findAllByCatFact(catFactToRemove);
        if (!userCatFacts.isEmpty()) {
            return;
        }
        catFactRepository.delete(catFactToRemove);
    }
    public Boolean existsCatFactDtoInUserList(Principal principal, CatFactDto catFactDto) {
        CatFact catFact = catFactDto.formCatFactDto();

        User user = getUserByPrincipal(principal);
        if (!userCatFactRepository.findAllByUser(getUserByPrincipal(principal)).isEmpty()) {
            List<UserCatFact> userCatFacts = userCatFactRepository.findAllByUser(getUserByPrincipal(principal));
            for (UserCatFact userCatFact : userCatFacts) {
                if (userCatFact.getCatFact().equals(catFact)) {
                    return true;
                }
            }
        }
        return false;

    }


    public void saveCatFact(Principal principal, CatFact catFact) throws IOException {
        log.info("CatFact with fact {}", catFact.getFact());
        User user = getUserByPrincipal(principal);
        if (!userCatFactRepository.findAllByUser(getUserByPrincipal(principal)).isEmpty()) {
            List<UserCatFact> userCatFacts = userCatFactRepository.findAllByUser(getUserByPrincipal(principal));
            for (UserCatFact userCatFact : userCatFacts) {
                if (userCatFact.getCatFact().equals(catFact)) {
                    return;
                }
            }
        }

        catFact.getUsers().add(user);
        user.getCatFacts().add(catFact);

        catFactRepository.save(catFact);
        userRepository.save(user);

    }

    public User getUserByPrincipal(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Not exists user"));
    }
    public CatFact getCatFactById(Long id) {
        return catFactRepository.findById(id).orElse(null);
    }
}
