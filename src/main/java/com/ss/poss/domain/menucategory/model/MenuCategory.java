package com.ss.poss.domain.menucategory.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuCategory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private UUID menuCategoryId;
    private String menuCategoryName;
    private String menuCategoryDesc;
}
