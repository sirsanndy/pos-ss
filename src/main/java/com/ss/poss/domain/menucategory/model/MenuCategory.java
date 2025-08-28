package com.ss.poss.domain.menucategory.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public record MenuCategory(
        UUID menuCategoryId,
        String menuCategoryName,
        String menuCategoryDesc,
        Boolean isActive
) implements Serializable {
    @Serial
    private static final long serialVersionUID = 4150276998337236924L;
}