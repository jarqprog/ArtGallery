package com.jarqprog.artGallery.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "gallery_users")
public class GalleryUser extends User implements MetadataSupplier {

    private static final long entityNumber = 102;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public GalleryUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public long getEntityNumber() {
        return entityNumber;
    }
}
