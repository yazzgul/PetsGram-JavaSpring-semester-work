package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.model.Post;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.impl.PostService;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public String getPostsView(@RequestParam(name = "name", required = false) String name,
                               Principal principal,
                               Model model) {
        model.addAttribute("posts", postService.postsList(name));
        model.addAttribute("user", postService.getUserByPrincipal(principal));
        return "post_view";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post/{id}")
    public String getPostInfoView(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl currentUser, Model model) {
        Post post = postService.getPostById(id);

        log.info("Post found: {}", post);
        log.info("Images: {}", post.getImages());

        Boolean equal = postService.equalUsersIdByPost(post, currentUser.getUser().getId());

        model.addAttribute("post", post);
        model.addAttribute("images", post.getImages());
        model.addAttribute("currentUserPostBoolean", equal);
        return "post_info_view";
    }

    @PostMapping("/post/delete/{id}")
    public String deletePostView(@PathVariable Long id) {
        postService.deletePostById(id);
        return "redirect:/posts";
    }
    @PostMapping("/post/create")
    public String createPostView(@RequestParam("file1") MultipartFile file1,
                                 Post post,
                                 Principal principal) throws IOException {
        postService.savePost(principal, post, file1);
        return "redirect:/profile";
    }

}
