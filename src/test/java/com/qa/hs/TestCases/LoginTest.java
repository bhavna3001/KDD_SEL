package com.qa.hs.TestCases;

import com.qa.hs.keyword.engine.Engine;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    public Engine engine;
    @Test
    public void loginTest() throws IOException {
       engine= new Engine();

       engine.startExecution("Carshop");

    }
}
