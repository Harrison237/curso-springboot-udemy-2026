package com.harrison.springboot.configuration.constants;

public abstract class ProductConstants {
    public static final String PRODUCT_BODY = """
            {
                "id": %d,
                "sku": "%s",
                "name": "%s",
                "description": "%s",
                "price": %s
            }
        """;

    public static final String makeProductBodyWithId(Integer id, String sku, String name, String description, String price) {
        return String.format(PRODUCT_BODY, id, sku, name, description, price);
    }

    public static final String makeProductBodyWithoutId(String sku, String name, String description, String price) {
        String template = PRODUCT_BODY.replaceFirst("\"id\": %d,", "");

        return String.format(template, sku, name, description, price);
    }
}
