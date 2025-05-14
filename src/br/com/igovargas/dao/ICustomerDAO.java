package br.com.igovargas.dao;
import br.com.igovargas.domain.Customer;
import java.util.Collection;

public interface ICustomerDAO {

    public Boolean register(Customer customer);
    public void exclude(Long cpf);
    public void change(Customer customer);
    public Customer consult(Long cpf);
    public Collection<Customer> searchAll();
}
