package com.ss.poss.domain.menu.model;

import java.io.Serial;
import java.io.Serializable;

public record MenuWebhook(
        String event
) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4949383417985968474L;
}
