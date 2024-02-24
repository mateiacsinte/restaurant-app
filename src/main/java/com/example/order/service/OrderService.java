package com.example.order.service;

import com.example.order.dtos.OrderDTO;

import com.example.order.entities.Order;
import com.example.order.entities.Product;
import com.example.order.entities.Waiter;
import com.example.order.exceptions.EntityNotFoundException;
import com.example.order.repository.OrderRepository;
import com.example.order.repository.ProductRepository;
import com.example.order.repository.WaiterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final WaiterRepository waiterRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, WaiterRepository waiterRepository,
            ProductRepository productRepository){

        this.orderRepository = orderRepository;
        this.waiterRepository = waiterRepository;
        this.productRepository = productRepository;
    }

    public OrderDTO createOrder(OrderDTO orderRequestDTO) throws EntityNotFoundException {
        Order newOrder = new Order();
        newOrder.setOrderDetails(orderRequestDTO.getOrderDetails());
        newOrder.setOrderValue(orderRequestDTO.getOrderValue());
        newOrder.setIsDone(orderRequestDTO.getIsDone());
        newOrder.setTableNumber(orderRequestDTO.getTableNumber());

        Waiter waiter = this.getAssignedWaiter(orderRequestDTO.getOrderWaiterId());

        newOrder.setOrderWaiter(waiter);

        List<Product> products  = this.getAssignedProducts(orderRequestDTO.getOrderProducts());

        newOrder.setOrderValue(
                products.stream()
                        .map(Product::getProductPrice)
                        .reduce(0f, Float::sum));

        newOrder.setOrderProducts(products);
        Order order = orderRepository.save(newOrder);
        return new OrderDTO(order);
    }

    public List<OrderDTO> getOrders(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }

    private List<Product> getAssignedProducts(List<Integer> orderProducts)throws EntityNotFoundException {
        if(orderProducts == null) return null;

        return orderProducts.stream()
                .map(pId -> {
                    try {
                        return productRepository.findById(pId).orElseThrow(
                                   () -> new EntityNotFoundException(String.format(
                                           "No product found matching id %d was found",pId)));
                    } catch (EntityNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                })
                .toList();
    }

    private Waiter getAssignedWaiter(Integer orderWaiterId) throws EntityNotFoundException {
        if(orderWaiterId == null) return null;

        Optional<Waiter> waiterEntityOpt = waiterRepository.findById(orderWaiterId);
        if(waiterEntityOpt.isPresent()){
            return waiterEntityOpt.get();
        }else{
            throw new EntityNotFoundException(String.format("No waiter matching id %d was found",orderWaiterId
            ));
        }
    }

    public OrderDTO deleteOrder(Integer orderId) throws EntityNotFoundException {
        Optional<Order> order = this.orderRepository.findById(orderId);
        if(order.isPresent()){
            this.orderRepository.delete(order.get());
            return new OrderDTO(order.get());
        }else{
            throw new EntityNotFoundException(String.format("No oroder matching id %d was found",orderId
            ));
        }
    }
}
