package ru.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByName(String name);
    List<Post> findPostsByUser_Id(Long id);
    void deleteById(Long id);

    Post save(Post post);
}
