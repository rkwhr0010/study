package designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import designpattern.behavioral.observer.NameSpace2.Customer;

public class ObserverPatternEx01 {
    public static void main(String[] args) throws InterruptedException {
        /*
         * 문제 1, 고객이 지속적으로 신상품 입고에 관심을 가져야 한다.
         */
        // problem1();

        /* 문제 1을 해결하고자, 가게에서 모든 고객에게 신상품 입고를 알린다.
         * 심지어 관심없는 고객까지!!
         */
        problem2();
    }

    private static void problem1() throws InterruptedException {
        //가게와 고객들
        NameSpace1.Store store = new NameSpace1.Store("신발 가게");
        NameSpace1.Customer c1 = new NameSpace1.Customer(store);
        NameSpace1.Customer c2 = new NameSpace1.Customer(store);
        NameSpace1.Customer c3 = new NameSpace1.Customer(store);
        
        Thread t1 = new Thread(runnableTemplate(c1::checkNewProduct));
        Thread t2 = new Thread(runnableTemplate(c2::checkNewProduct));
        Thread t3 = new Thread(runnableTemplate(c3::checkNewProduct));

        t1.start();
        t2.start();
        t3.start();
        
        //신상품 입고
        Thread.sleep(ThreadLocalRandom.current().nextInt(10000));
        System.out.println("== 신상 입고 ==");
        store.addProduct(new NameSpace1.Product("멋진 운동화"));
    }

    static void problem2(){
        NameSpace2.Customer customer1 = new NameSpace2.Customer(true); //관심있는 사람
        NameSpace2.Customer customer2 = new NameSpace2.Customer(true); //관심있는 사람

        NameSpace2.Customer customer3 = new NameSpace2.Customer(false); //관심없는 사람
        NameSpace2.Customer customer4 = new NameSpace2.Customer(false); //관심없는 사람

        NameSpace2.Store store = new NameSpace2.Store("신발 가게 ", List.of(customer1, customer2, customer3, customer4));

        store.addProduct(new NameSpace2.Product("이쁜 구두"));
    }

    static Runnable runnableTemplate(Runnable run){
        return run::run;
    }

}
class NameSpace1 {

    static class Customer {
        static Long serial = 1L;
        Long id;
        Store store;
        
        public Customer(Store store) {
            id = serial++;
            this.store = store;
        }
    
        void checkNewProduct() {
            while(store.getNewProduct() == null){
                System.out.println(id + " 신상품 입고 대기 중 !!!");
                try{
                    Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
                } catch (Exception e) {}
            }
            System.out.println(id+ " 야호! 구매했다. " + store.getNewProduct());
        }
    }
    
    static class Store {
        String name;
        Product product; 
    
        public Store(String name) {
            this.name = name;
        }
    
        void addProduct(Product product){
            this.product = product;
        }
    
        Product getNewProduct(){
            return product;
        }
    }
    static class Product {
        String name;
    
        public Product(String name) {
            this.name = name;
        }
    
        @Override
        public String toString() {
            return "Product [name=" + name + "]";
        }
    }
}

class NameSpace2 {

    static class Customer {
        static Long serial = 1L;
        Long id;
        boolean isInterest; //관심있는지
        String message;

        public Customer(boolean isInterest) {
            this.isInterest = isInterest;
        }

        void alarm(String message){
            if(isInterest){
                System.out.println(message + "  와 신상이다.");
            } else {
                System.out.println("난.. 관심도 없는데!!!");
            }
        }

    }
    
    static class Store {
        List<NameSpace2.Customer> customers = new ArrayList<>();
        String name;
        Product product; 
    
        public Store(String name, List<NameSpace2.Customer> customers) {
            this.name = name;
            this.customers = customers;
        }
    
        void addProduct(Product product){
            this.product = product;
            customers.stream().forEach(c -> c.alarm(product.toString()));
        }
    }

    static class Product {
        String name;
    
        public Product(String name) {
            this.name = name;
        }
    
        @Override
        public String toString() {
            return "Product [name=" + name + "]";
        }
    }
}