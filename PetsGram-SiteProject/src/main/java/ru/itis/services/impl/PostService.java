package ru.itis.services.impl;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.model.Image;
import ru.itis.model.Post;
import ru.itis.model.User;
import ru.itis.repository.PostRepository;
import ru.itis.repository.UserRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public List<Post> postsList(String name) {
        if (name != null) return postRepository.findPostsByName(name);
//         List<Post> posts = postRepository.findAll();
        return postRepository.findAll();
    }
    public void savePost(Principal principal, Post post, MultipartFile file1) throws IOException {
        post.setUser(getUserByPrincipal(principal));
        Image image1;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            post.addImageToPost(image1);
        }
        log.info("Saving new Post: Name: {}, Author email: {}", post.getName(), post.getUser().getEmail());
        Post postFromDb = postRepository.save(post);
        postFromDb.setPreviewImageId(postFromDb.getImages().get(0).getId());
        postRepository.save(postFromDb);
    }
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Cacheable("posts")
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
    public User getUserByPrincipal(Principal principal) {
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException("Not exists user"));
    }
    public Boolean equalUsersIdByPost(Post post, Long currentUserId) {
        if (post.getUser().getId() == currentUserId) {
            return true;
        }
        return false;
    }
    @Cacheable("posts")
    public List<Post> getPostsByUserId(Long id) {
        return postRepository.findPostsByUser_Id(id);
    }

}
