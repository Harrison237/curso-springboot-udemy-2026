package com.harrison.springboot.interfaces;

import java.util.Map;

public interface PropertyLoader {
    public void loadProperties(Map<String, String> storage);
}
