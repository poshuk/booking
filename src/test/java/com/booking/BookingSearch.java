package com.booking;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class BookingSearch extends WebDriverSettings {

    @Test
    public void searchingNewYorkFromFirstTillThirtiethSeptemberSuccess() {

        HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
        homePage.open();
        homePage.chooseEnglish_US_Language();
        homePage.sendCityToSearchForm("New York");
        homePage.setCheckInAndCheckOutDateNewYork();

        SearchResultPage searchResultPage = homePage.submitForm();
        searchResultPage.applyAvailableRoomsFilter();
        searchResultPage.checkCheckInCheckOutAndCity("checkin=2019-09-01&checkout=2019-09-30", "New York");
    }

    @Test
    public void searchingLondonFromTenthTillTwentiethJulySuccess() {
        HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
        homePage.open();
        homePage.chooseEnglish_US_Language();
        homePage.sendCityToSearchForm("London");
        homePage.setCheckInAndCheckOutDateLondon();

        SearchResultPage searchResultPage = homePage.submitForm();
        searchResultPage.applyAvailableRoomsFilter();
        searchResultPage.checkCheckInCheckOutAndCity("checkin=2019-07-10&checkout=2019-07-20", "London");
    }

    @Test
    public void searchingParisFromSeventhTillThirtiethOctoberSuccess() {
        HomePage homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
        homePage.open();
        homePage.chooseEnglish_US_Language();
        homePage.sendCityToSearchForm("Paris");
        homePage.setCheckInAndCheckOutDateParis();

        SearchResultPage searchResultPage = homePage.submitForm();
        searchResultPage.applyAvailableRoomsFilter();
        searchResultPage.checkCheckInCheckOutAndCity("checkin=2019-10-07&checkout=2019-10-30", "Paris");
    }


}
