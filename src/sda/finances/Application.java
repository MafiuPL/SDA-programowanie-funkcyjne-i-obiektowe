package sda.finances;

import Finances.model.Expense;
import Finances.model.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RENT on 2017-02-20.
 */
public class Application {
    public static void main(String[] args) {
        List<Expense> expenses = init();
        expenses.forEach(expense -> {
            expense.getProducts().stream()
                    .filter(product -> product.getUnitPrice() <= 3)
                    .forEach(product -> System.out.println(product));
        });
        expenses.stream()
                .filter(expense -> expense.getType().equals("spozywczak"))
                .forEach(expense -> {
                    expense.getProducts().stream()
                            .filter(product -> product.getUnitPrice() <= 3)
                            .forEach(product -> System.out.println(product));
                });
        double Cebula = expenses.stream()
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getName().equals("Cebula"))
                        .mapToDouble(product -> product.getUnitPrice() * product.getAmount())
                        .sum())

                .sum();
        System.out.println(Cebula);

        double spozywczak = expenses.stream()
                .filter(expense -> expense.getType().equals("spozywczak"))
                .mapToDouble(expense -> expense.getPrice())
                .sum();
        System.out.println(spozywczak);

        expenses.stream()
                .filter(expense -> expense.getDate().isBefore(LocalDate.of(2017, 02, 19)))
                .forEach(expense -> expense.getProducts()
                        .forEach(product -> System.out.println(product)));
        System.out.println();

        System.out.println(expenses.stream()
                .filter(expense -> expense.getDate().isEqual(LocalDate.of(2016, 5, 20)))
                .mapToDouble(expense -> expense.getPrice())
                .sum());

        System.out.println();
        System.out.println(expenses.stream()
                .filter(expense -> expense.getDate().isEqual(LocalDate.of(2016, 5, 20)))
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getName().equals("tabsy"))
                        .mapToDouble(product -> product.getUnitPrice() * product.getAmount())
                        .sum())
                .sum());

        System.out.println();
        System.out.println(expenses.stream()
                .mapToDouble(expense -> expense.getProducts()
                        .stream()
                        .filter(product -> product.getName().startsWith("p"))
                        .mapToDouble(product -> product.getAmount() * product.getUnitPrice())
                        .sum())
                .sum());

        System.out.println();
        System.out.println(expenses.stream()
                .filter(expense -> expense.getType().equals("spozywczak"))
                .mapToDouble(expense -> expense.getProducts().stream()
                        .filter(product -> product.getAmount() % 2 == 0)
                        .mapToDouble(product -> product.getAmount() * product.getUnitPrice())
                        .sum())
                .sum());

        System.out.println();
        expenses.stream()
                .map(expense -> expense.getProducts()
                        .stream()
                        .max((e1, e2) ->
                                (e1.getUnitPrice() * e1.getAmount()) >
                                        (e2.getAmount() * e2.getUnitPrice()) ? 1 : -1)
                        .get())
                .forEach(product -> System.out.println(product));

        System.out.println();
        System.out.println(expenses.stream()
                .map(expense -> expense.getProducts()
                        .stream()
                        .max((e1, e2) ->
                                (e1.getUnitPrice() * e1.getAmount()) >
                                        (e2.getAmount() * e2.getUnitPrice()) ? 1 : -1)
                        .get())
                .max((e1, e2) ->
                        (e1.getUnitPrice() * e1.getAmount()) >
                                (e2.getAmount() * e2.getUnitPrice()) ? 1 : -1)
                .get());


    }


    private static List<Expense> init() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Cebula", 5, 2));
        products.add(new Product("Kalafior", 3, 1));
        products.add(new Product("Musztarda", 4, 6));
        products.add(new Product("Czosnek", 7, 3));
        Expense expense = new Expense("spozywczak", products, 2017, 5, 20);

        List<Product> products2 = new ArrayList<>();
        products2.add(new Product("tapeta", 4, 3));
        products2.add(new Product("pedzel", 1, 2));
        products2.add(new Product("wiadro", 3, 5));
        Expense expense2 = new Expense("budowlaniec", products2, 2017, 3, 14);

        List<Product> products3 = new ArrayList<>();
        products3.add(new Product("syropek", 14, 13));
        products3.add(new Product("tabsy", 7, 8));
        products3.add(new Product("chusteczki", 6, 9));
        Expense expense3 = new Expense("apteka", products3, 2016, 5, 20);

        List<Product> products4 = new ArrayList<>();
        products4.add(new Product("mandarynki", 20, 23));
        products4.add(new Product("pomarancze", 19, 22));
        products4.add(new Product("truskawki", 10, 33));
        Expense expense4 = new Expense("spozywczy", products4, 2017, 10, 11);

        return Arrays.asList(expense, expense2, expense3, expense4);

    }
}
