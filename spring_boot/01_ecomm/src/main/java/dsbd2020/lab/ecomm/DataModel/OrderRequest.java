package dsbd2020.lab.ecomm.DataModel;

import javax.validation.constraints.NotNull;
import java.util.Map;

//localhost:8082

public class OrderRequest {

    @NotNull
    private Integer userId;
    //private String userEmail; //alternativa

    @NotNull
    private Map<Integer, Integer> products; //associazione tra productId e la quantità

    public Integer getUserId() {
        return userId;
    }

    public OrderRequest setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public OrderRequest setProducts(Map<Integer, Integer> products) {
        this.products = products;
        return this;
    }
}
