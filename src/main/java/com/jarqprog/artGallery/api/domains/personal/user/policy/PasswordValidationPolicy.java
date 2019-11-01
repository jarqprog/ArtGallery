package com.jarqprog.artGallery.api.domains.personal.user.policy;

import java.util.regex.Pattern;

public interface PasswordValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
