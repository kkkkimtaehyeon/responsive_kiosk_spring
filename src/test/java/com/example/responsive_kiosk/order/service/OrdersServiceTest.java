package com.example.responsive_kiosk.order.service;


import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.responsive_kiosk.order.dto.OrderDetailRequestDto;
import com.example.responsive_kiosk.order.dto.OrderRequestDto;
import com.example.responsive_kiosk.order.entity.OrderDetails;
import com.example.responsive_kiosk.order.entity.Orders;
import com.example.responsive_kiosk.order.repository.OrderDetailRepository;
import com.example.responsive_kiosk.order.repository.OrderRepository;
import com.example.responsive_kiosk.product.entity.Category;
import com.example.responsive_kiosk.product.entity.Menu;
import com.example.responsive_kiosk.product.repository.CategoryRepository;
import com.example.responsive_kiosk.product.repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrdersServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    CategoryRepository categoryRepository;

    int TEST_CASE_COUNT = 3;
    String TEMPERATURE = "ice";
    Double MENU_PRICE = 3000.0;
    String MENU_DESCRIPTION = "this is latte";
    String MENU_IMAGE_PATH = "a/b/menu.jpg";
    String CATEGORY_NAME = "coffee";
    Double PRICE = 6000.0;
    String MENU_NAME = "latte";
    Integer AMOUNT = 2;
    String TAKEOUT = "takeout";
    Double TOTAL_PRICE = 12000.0;

    @AfterEach
    public void clear() {
        orderDetailRepository.deleteAll();
        orderRepository.deleteAll();
        menuRepository.deleteAll();
    }

    @Test
    public void 주문_저장() {
        //given
        Category category = categoryRepository.save(Category.builder()
                .name(CATEGORY_NAME)
                .build());

        for(int i = 0; i < TEST_CASE_COUNT; i++) {
            menuRepository.save(Menu.builder()
                    .category(category)
                    .name(MENU_NAME+i)
                    .price(MENU_PRICE)
                    .description(MENU_DESCRIPTION)
                    .imagePath(MENU_IMAGE_PATH)
                    .build());
        }

        OrderRequestDto orderRequestDto = OrderRequestDto.builder()
                .totalPrice(TOTAL_PRICE)
                .takeout(TAKEOUT)
                .orderDetailRequestDtoList(generateDummyDtoList())
                .build();

        //when
        ResponseEntity<Long> order = orderService.save(orderRequestDto);

        //then
        Long orderId = order.getBody();
        Orders testOrders = orderRepository.findById(orderId).orElse(null);

        assertNotNull(testOrders);

        for(int i = 0; i < TEST_CASE_COUNT; i++) {
            List<OrderDetails> testOrderDetailList = testOrders.getOrderDetails();
            assertNotNull(testOrderDetailList);

            String expected = MENU_NAME + i;
            OrderDetails orderDetails = testOrderDetailList.get(i);
            assertThat(orderDetails.getMenu().getName()).isEqualTo(expected);
        }


    }

    public List<OrderDetailRequestDto> generateDummyDtoList() {
        List<OrderDetailRequestDto> requestDtoList = new ArrayList<>();

        for(int i = 0; i < TEST_CASE_COUNT; i++) {
            OrderDetailRequestDto requestDto = OrderDetailRequestDto.builder()
                    .menuName(MENU_NAME + i)
                    .temperature(TEMPERATURE)
                    .price(PRICE)
                    .amount(AMOUNT)
                    .build();
            requestDtoList.add(requestDto);
        }
        return requestDtoList;
    }
}


