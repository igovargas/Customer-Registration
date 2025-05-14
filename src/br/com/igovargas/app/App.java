package br.com.igovargas.app;

import br.com.igovargas.dao.CustomerMapDAO;
import br.com.igovargas.dao.ICustomerDAO;
import br.com.igovargas.ui.CustomerService;

public class App {
    public static void main(String[] args) {
        ICustomerDAO dao= new CustomerMapDAO();
        CustomerService service = new CustomerService(dao);
        service.start();
    }
}
