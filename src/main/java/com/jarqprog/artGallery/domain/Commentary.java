package com.jarqprog.artGallery.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="commentaries")
public class Commentary implements DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="picture_id")
    private Picture picture;

    public Commentary(String content, User user, Picture picture) {
        this.content = content;
        this.user = user;
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Commentary{" +
                "id=" + id +
                ", content='" + content + '\'' +
//                ", userId=" + user.getId() +//todo uncomment after user development
                ", pictureId=" + picture.getId() +
                '}';
    }
}
