package ru.itis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "catFact")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CatFact extends BaseEntity {

    @Column(name = "fact")
    private String fact;
    @Column(name = "length")
    private int length;

    @ManyToMany(mappedBy = "catFacts")
    private List<User> users = new ArrayList<>();
}
