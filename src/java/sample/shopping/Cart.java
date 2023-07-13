/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

public class Cart {

    private final Map<String, Integer> cart;

    public Map<String, Integer> getCart() {
        return cart;
    }

    public Cart() {
        cart = new HashMap<>();
    }

    public void addToCart(String tea, int quantity) throws SQLException, NamingException, ClassNotFoundException {
        if (cart != null) {
            int totalQuantity = ClothesDAO.getProduct(tea).getQuantity();
            if (cart.containsKey(tea)) {
                int currentQuantity = cart.get(tea);
                int newQuantity = currentQuantity + quantity;
                if (newQuantity > totalQuantity) {
                    newQuantity = totalQuantity; // Adjust the quantity to the available quantity
                }
                cart.put(tea, newQuantity);
            } else {
                if (quantity > totalQuantity) {
                    quantity = totalQuantity; // Adjust the quantity to the available quantity
                }
                cart.put(tea, quantity);
            }
        }
    }

    public void removeFromCart(String tea) {
        if (cart != null) {
            if (cart.containsKey(tea)) {
                cart.remove(tea);
            }
        }
    }
}
