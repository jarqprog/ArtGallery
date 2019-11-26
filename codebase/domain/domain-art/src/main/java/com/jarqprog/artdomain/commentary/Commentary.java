package com.jarqprog.artdomain.commentary;


import com.jarqprog.commondomain.Model;
import com.jarqprog.artdomain.picture.Picture;

public interface Commentary extends CommentaryData, Model {

    Picture getPicture();
}
