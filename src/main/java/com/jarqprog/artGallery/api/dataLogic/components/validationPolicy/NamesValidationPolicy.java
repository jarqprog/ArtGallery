package com.jarqprog.artGallery.api.dataLogic.components.validationPolicy;

import java.util.regex.Pattern;

public interface NamesValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
