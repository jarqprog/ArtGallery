package com.jarqprog.artGallery.api.domains.personal.contact.policy;

import java.util.regex.Pattern;

public interface NamesValidationPolicy {

    String getRestriction();
    Pattern getPolicy();

}
