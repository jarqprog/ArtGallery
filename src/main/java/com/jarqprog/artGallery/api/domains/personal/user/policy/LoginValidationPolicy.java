package com.jarqprog.artGallery.api.domains.personal.user.policy;

import java.util.regex.Pattern;

public interface LoginValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
