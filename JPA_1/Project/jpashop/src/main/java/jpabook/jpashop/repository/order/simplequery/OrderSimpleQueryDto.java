package jpabook.jpashop.repository.order.simplequery;

import java.time.LocalDateTime;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Address;
import lombok.Data;

@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        orderId = orderId;
        name = name;
        orderDate = orderDate;
        orderStatus = orderStatus;
        address = address;
    }
}
