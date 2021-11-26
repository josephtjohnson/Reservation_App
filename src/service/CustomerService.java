package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class CustomerService {
    public Collection<Customer> customers = new HashSet<>();
    private static CustomerService customerService = null;

    public static CustomerService getInstance() {
        if(customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customers.add(customer);
    }
    public Customer getCustomer(String customerEmail) {
        for (Customer customer : customers) {
            if(customer.getEmail().equalsIgnoreCase(customerEmail)) {
                return customer;
            }
        }
        System.out.println("Customer does not exist");
        return null;
    }
    public Collection<Customer> getCustomers(){
        return customers;
    }

    public void customerList(){
        Collection<Customer> allCustomers = new HashSet<>(customers);
        if (allCustomers.isEmpty()) {
            System.out.println("No customers in database.");
        }
        else {
            for(Customer customer: allCustomers) {
                System.out.println(customer);
            }
        }

    }

}
