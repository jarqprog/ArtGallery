package com.jarqprog.artGallery.domain.artistic;

import com.jarqprog.artGallery.domain.Model;

public interface Commentary extends CommentaryData, Model {

    Picture getPicture();
}
