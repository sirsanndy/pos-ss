package com.ss.poss.domain.menu.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public record Menu(
        UUID menuId,
        String name,
        String description,
        Integer stock,
        BigDecimal price,
        UUID menuCategoryId,
        Boolean isActive
) implements Serializable {
    @Serial
    static final long serialVersionUID = 4114873224866368052L;
}
