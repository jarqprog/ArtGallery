package com.jarqprog.artGallery.domain.personal;

import com.jarqprog.artGallery.domain.Model;

public interface User extends UserData, Model {

    Contact getContact();

}
