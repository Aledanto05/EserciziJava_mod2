package com.dslab.ecommercelab.controller;

import com.dslab.ecommercelab.entity.*;
import com.dslab.ecommercelab.service.EcommProductService;
import com.dslab.ecommercelab.service.EcommUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    private FinalOrderRepository repository;

    @Autowired
    private EcommUserService userService;

    @Autowired
    private EcommProductService productService;

    @PostMapping(path = "/add")
    public @ResponseBody
    String add(@RequestBody OrderRequest request) {

        Optional<User> user = userService.getByEmail(request.getUserEmail());
        if (!user.isPresent())
            return String.format("The user with email %s does not exist!", request.getUserEmail());

        FinalOrder finalOrder = new FinalOrder();
        List<OrderProduct> products = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : request.getProducts().entrySet()) {
            Optional<Product> product = productService.getById(entry.getKey());
            if (product.isPresent() && product.get().getItems() >= entry.getValue())
                products.add(new OrderProduct().setProduct(product.get()).setQuantity(entry.getValue()));
            else return "The product missed required qantity!";
        }

        finalOrder.setProducts(products).setUser(user.get()).setState("NEW");
        repository.save(finalOrder);

        for (final OrderProduct orderProduct : products)
            productService.add(orderProduct.getProduct().setItems(
                    orderProduct.getProduct().getItems() - orderProduct.getQuantity()
            ));
        return "Order created!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<FinalOrder> getAll () {
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Optional<FinalOrder> get (Authentication auth, @PathVariable Integer id) {
        Optional<FinalOrder> order = repository.findById(id);
        if (order.isPresent()) {
            User user = order.get().getUser();
            if(user.getEmail().equals(auth.getName()))
                return order;
        }
        return Optional.empty();
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody
    String delete (@PathVariable Integer id) {
        repository.deleteById(id);
        return "Order deleted!";
    }

}
