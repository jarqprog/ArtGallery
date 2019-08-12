package com.jarqprog.artGallery.springData.repository;

import com.jarqprog.artGallery.domain.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Repository
@EnableTransactionManagement
public interface ContactRepository extends JpaRepository<Contact, Long> {



}
