package service;

import model.Customer;
import java.util.Collection;
import java.util.HashSet;

public class CustomerService {
    private static Collection<Customer> customers = new HashSet<Customer>();
    private static CustomerService customerService = null;

    public static CustomerService getInstance() {
        if(null == customerService) {
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

}
