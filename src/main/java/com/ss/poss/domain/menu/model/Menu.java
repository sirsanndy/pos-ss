package com.ss.poss.domain.menu.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID menuId;
    private String name;
    private String description;
    private Integer stock;
    private BigDecimal price;
    private Boolean isActive;
}
