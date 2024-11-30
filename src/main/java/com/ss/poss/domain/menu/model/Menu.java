package com.ss.poss.domain.menu.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private Integer stock;
    private BigDecimal price;
}
