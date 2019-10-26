package com.jarqprog.artGallery.api.dataLogic.components.validationPolicy;

import java.util.regex.Pattern;

public interface PasswordValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
