package com.booking;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SearchResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    private static By LIST_OF_HOTELS = By.id("right");
    private static By LIST_OF_FILTERS = By.id("left");
    private static By AVAILABLE_ROOMS_FILTER = By.cssSelector(".sr-cta-button-row");

    @FindBy(css = "#filter_out_of_stock a")
    private WebElement availableHotelsFilter;

    @CacheLookup
    @FindBy(css= ".address [href]")
    private List<WebElement> listOfAddressAndDates;


    public void applyAvailableRoomsFilter() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LIST_OF_HOTELS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LIST_OF_FILTERS));
        availableHotelsFilter.click();
        WebElement availableRooms = driver.findElement(AVAILABLE_ROOMS_FILTER);
        wait.until(ExpectedConditions.stalenessOf(availableRooms));
    }

    public void checkCheckInCheckOutAndCity(String checkInCheckOutDate, String city) {
        int i = 1;
        for (WebElement el: listOfAddressAndDates) {
            String link = el.getAttribute("href");
            String date =  StringUtils.substringBetween(link, "hapos="+i+"&", "&dest_id");
            String metropolis = StringUtils.substringBetween(el.getText(), ", ", " –");
            i++;
            Assert.assertEquals(checkInCheckOutDate, date);
            Assert.assertEquals(city, metropolis);
        }
    }
}
