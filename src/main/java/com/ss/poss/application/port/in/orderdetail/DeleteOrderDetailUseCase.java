package com.ss.poss.application.port.in.orderdetail;

import java.util.UUID;

public interface DeleteOrderDetailUseCase {
    void deleteOrderDetailById(UUID orderDetailId);
}
