package com.jarqprog.artGallery.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "gallery_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class GalleryUser extends User implements MetadataSupplier {

    private static final long ENTITY_NUMBER = EntityNumberConstants.GALLERY_USER;

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
        return ENTITY_NUMBER;
    }
}
