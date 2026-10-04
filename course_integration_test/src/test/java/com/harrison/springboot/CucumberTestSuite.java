package com.harrison.springboot;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines ("cucumber")
// Configuración de producción
@SelectPackages("features")
// Configuración para debugging
// @SelectClasspathResource(
//     value = "features/api/products.feature",
//     line = 17
// )
@ConfigurationParameter(
    key = GLUE_PROPERTY_NAME,
    value = "com.harrison.springboot.steps"
)
@ConfigurationParameter(
    key = PLUGIN_PROPERTY_NAME,
    value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel,pretty"
)
public class CucumberTestSuite {

}
