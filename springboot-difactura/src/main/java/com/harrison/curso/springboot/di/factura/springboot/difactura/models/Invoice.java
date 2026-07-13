package com.harrison.curso.springboot.di.factura.springboot.difactura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@AllArgsConstructor
@Component
@RequestScope
@JsonIgnoreProperties({"targetSource", "advisors"})
public class Invoice {

    @Getter
    @Autowired
    private Client client;

    @Getter
    @Value("${invoice.description.office}")
    private String description;

    @Getter
    @Autowired
    @Qualifier("default")
    private List<Item> items;

    public Invoice() {
        System.out.println("Creando el componente de la factura");
        System.out.println(client);
        System.out.println(description);
    }

    @PostConstruct
    public void init() {
        System.out.println("Creando el componente de la factura");
        client = new Client(client.getName().concat(" asd"), client.getLastname());
        description = description.concat(" del cliente ").concat(client.getName()).concat(" ").concat(client.getLastname());
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destruyendo el componente Invoice");
    }

    public int getTotalInvoice() {
/*        int total = 0;

        for (Item item : items) {
            total += item.getTotal();
        }

        return total;*/

        return items.stream()
                .map(Item::getTotal)
                .reduce(0, Integer::sum);
    }
}
