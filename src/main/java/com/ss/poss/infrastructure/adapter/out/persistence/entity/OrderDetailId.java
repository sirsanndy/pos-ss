package com.ss.poss.infrastructure.adapter.out.persistence.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class OrderDetailId implements Serializable {

    @Serial
    private static final long serialVersionUID = -1234985007452821508L;
    private UUID orderId;
    private UUID menuId;
}
