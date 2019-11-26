package com.jarqprog.artdomain.commentary;


import com.jarqprog.commondomain.absmodel.Model;
import com.jarqprog.artdomain.picture.Picture;

public interface Commentary extends CommentaryData, Model {

    Picture getPicture();
}
