package com.jarqprog.artdomain.model.commentary;


import com.jarqprog.commondomain.absmodel.Model;
import com.jarqprog.artdomain.model.picture.Picture;

public interface Commentary extends CommentaryData, Model {

    Picture getPicture();
}
