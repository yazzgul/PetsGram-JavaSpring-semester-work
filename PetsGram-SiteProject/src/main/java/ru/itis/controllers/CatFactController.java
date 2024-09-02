package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.api.CatFactApiClient;
import ru.itis.dto.CatFactDto;
import ru.itis.model.CatFact;
import ru.itis.services.impl.UserCatFactService;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CatFactController {
    private final CatFactApiClient catFactApiClient;
    private final UserCatFactService userCatFactService;

    @GetMapping("/random-cat-fact")
    public String getRandomCatFact(CatFactDto catFactDto, Principal principal, Model model) {
        CatFactDto CatFactDto = catFactApiClient.getRandomCatFact();

        model.addAttribute("catFact", CatFactDto);
        model.addAttribute("existInUserList", userCatFactService.existsCatFactDtoInUserList(principal, catFactDto));
        return "random_cat_fact_view";
    }
    @PostMapping("/random-cat-fact/save")
    public String saveFactView(CatFact catFact,
                               Principal principal) throws IOException {
        log.info("CatFact from daveFactView with fact: {}", catFact.getFact());
        userCatFactService.saveCatFact(principal, catFact);
        return "redirect:/profile";
    }
    @PostMapping("/random-cat-fact/delete/{id}")
    public String deleteFactView(@PathVariable Long id,
                               Principal principal) throws IOException {
        userCatFactService.deleteCatFactByUser(principal, id);
        return "redirect:/profile";
    }
    @GetMapping("/random-cat-fact/{id}")
    public String getCatFactInfoView(@PathVariable Long id, Model model) {

        CatFact catFact = userCatFactService.getCatFactById(id);
        model.addAttribute("catFact", catFact);

        return "random_cat_fact_info_view";
    }

}
