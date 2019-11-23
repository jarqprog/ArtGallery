package com.jarqprog.artdomain.model.picture;

import com.jarqprog.artdomain.model.author.Author;

public interface Picture extends PictureData {

    Author getAuthor();

}
