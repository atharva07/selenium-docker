package com.atharvahiwase07.tests.flightreservation;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.atharvahiwase07.pages.flightregistration.FlightConfirmationPage;
import com.atharvahiwase07.pages.flightregistration.FlightSearchPage;
import com.atharvahiwase07.pages.flightregistration.FlightsSelectionPage;
import com.atharvahiwase07.pages.flightregistration.RegistrationConfirmationPage;
import com.atharvahiwase07.pages.flightregistration.RegistrationPage;
import com.atharvahiwase07.tests.AbstractTest;
import com.atharvahiwase07.tests.flightreservation.model.FlightReservationTestData;
import com.atharvahiwase07.utils.Config;
import com.atharvahiwase07.utils.Constants;
import com.atharvahiwase07.utils.JsonUtil;

import org.testng.annotations.Parameters;
import org.testng.Assert;

public class FlightReservationTest extends AbstractTest{

    private FlightReservationTestData testData;
    // Once we are done with scripting the test file execute the test with the folllowing commands.
    // Open the terminal.
    // go to the location of the pom.xml file
    // hit the command mvn clean test
    // check the reports in the target folder of the project.
    // dont forget to push the code.
    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath) {
        this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }
    
    @Test
    public void userRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        driver.manage().window().maximize();
        Assert.assertTrue(registrationPage.isAt());
        System.out.println(testData.firstName());
        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.register();   
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
        registrationConfirmationPage.gotoflightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest() {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(testData.passengerCount());
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightSelectionTest() {
        FlightsSelectionPage flightSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmflightsButton();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
    }
}
