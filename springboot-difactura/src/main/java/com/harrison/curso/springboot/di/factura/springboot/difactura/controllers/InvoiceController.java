package com.harrison.curso.springboot.di.factura.springboot.difactura.controllers;

import com.harrison.curso.springboot.di.factura.springboot.difactura.models.Client;
import com.harrison.curso.springboot.di.factura.springboot.difactura.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private Invoice invoice;

    @GetMapping("/show")
    public Invoice show() {
        Client c = new Client(invoice.getClient().getName(), invoice.getClient().getLastname());

        return new Invoice(c, invoice.getDescription(), invoice.getItems());
    }
}
