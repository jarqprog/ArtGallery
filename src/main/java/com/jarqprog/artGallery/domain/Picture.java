package com.jarqprog.artGallery.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pictures")
public class Picture implements DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    @OneToMany(mappedBy="picture", cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Set<Commentary> commentaries;

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
        '}';
    }
}
