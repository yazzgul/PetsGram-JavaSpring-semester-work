package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.model.User;
import ru.itis.services.impl.PostService;
import ru.itis.services.impl.UserCatFactService;
import ru.itis.services.impl.UserService;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class ProfileController {
    private final UserService userService;
    private  final PostService postService;
    private final UserCatFactService userCatFactService;

    @GetMapping("/profile")
    public String getProfileView(Model model, Principal principal) {
        User user = postService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        return "profile_view";
    }
    @GetMapping("/user/{id}")
    public String userInfo(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("posts", user.getPosts());
        return "user_info_view";
    }
    @GetMapping("/profile/random-cat-fact")
    public String userRandomCatFactInfo(Principal principal,
                                        Model model) {
        User user = userCatFactService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("catFacts", userCatFactService.getCatFactListByUserId(user.getId()));
        return "user_random_cat_fact_view";
    }

}
