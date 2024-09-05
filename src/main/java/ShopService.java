import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) throws Exception {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            if (productRepo.getProductById(productId).isEmpty()) {
                throw new Exception("Product with id: " + productId + " not found!");
            }
            Product productToOrder = productRepo.getProductById(productId).get();
            products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products);

        return orderRepo.addOrder(newOrder);
    }


    public List<Order> getOrderByStatus(OrderStatus status) {
        return orderRepo.getOrders().stream()
                .filter(order -> order.status() == status)
                .toList();
    }

    public void updateOrder(String orderId, OrderStatus orderStatus) {

        Order order = orderRepo.getOrderById(orderId);

    }
}
