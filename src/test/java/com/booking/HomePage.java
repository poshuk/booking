package com.booking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    private static By LANGUAGE_POPUP = By.cssSelector("#current_language_foldout");
    private static By PLACEHOLDER = By.id("ss");
    private static By LIST_SUGGESTED_DEST = By.cssSelector("[aria-label='List of suggested destinations ']");
    private static By CALENDAR_FORM = By.cssSelector(".bui-calendar__main");

    @FindBy(css = "[data-id='language_selector']")
    private WebElement elementLanguageSelector;

    @FindBy(css = "#current_language_foldout .select_foldout_wrap:nth-of-type(2) [data-lang='en-us'] a")
    private WebElement englishUsSelectedAnchor;

    @FindBy(id = "ss")
    private WebElement placeholderElement;

    @FindBy(css = "[aria-label='List of suggested destinations '] li:nth-of-type(1)")
    private WebElement selectedFirstPlace;

    @FindBy(css = "[class='bui-calendar__month']")
    private WebElement month;

    @CacheLookup
    @FindBy(css = "[data-bui-ref='calendar-next']")
    private WebElement nextMonth;

    @FindBy(css = "[data-date='2019-09-01']")
    private WebElement checkInNewYork;

    @FindBy(css = "[data-date='2019-07-10']")
    private WebElement checkInLondon;

    @FindBy(css = "[data-date='2019-10-07']")
    private WebElement checkInParis;

    @FindBy(css = "[data-date='2019-09-30']")
    private WebElement checkOutNewYork;

    @FindBy(css = "[data-date='2019-07-20']")
    private WebElement checkOutLondon;

    @FindBy(css = "[data-date='2019-10-30']")
    private WebElement checkOutParis;

    @FindBy(className = "sb-searchbox__button")
    private WebElement searchButton;

    public void open() {
        driver.get("https://www.booking.com");
    }

    public void chooseEnglish_US_Language() {
        wait.until(ExpectedConditions.elementToBeClickable(elementLanguageSelector));
        elementLanguageSelector.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(LANGUAGE_POPUP));
        wait.until(ExpectedConditions.elementToBeClickable(englishUsSelectedAnchor));
        englishUsSelectedAnchor.click();
    }

    public void sendCityToSearchForm(String city) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PLACEHOLDER));
        placeholderElement.sendKeys(city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LIST_SUGGESTED_DEST));
        selectedFirstPlace.click();
    }

    public void setCheckInAndCheckOutDateNewYork() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CALENDAR_FORM));
        while (!month.getText().equals("September 2019")) {
            nextMonth.click();
            wait.until(ExpectedConditions.elementToBeClickable(nextMonth));
        }
        checkInNewYork.click();
        wait.until(ExpectedConditions.elementToBeClickable(checkOutNewYork));
        checkOutNewYork.click();
    }

    public void setCheckInAndCheckOutDateLondon() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CALENDAR_FORM));
        while (!month.getText().equals("July 2019")) {
            nextMonth.click();
            wait.until(ExpectedConditions.elementToBeClickable(nextMonth));
        }
        checkInLondon.click();
        wait.until(ExpectedConditions.elementToBeClickable(checkOutLondon));
        checkOutLondon.click();
    }

    public void setCheckInAndCheckOutDateParis() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CALENDAR_FORM));
        while (!month.getText().equals("October 2019")) {
            nextMonth.click();
            wait.until(ExpectedConditions.elementToBeClickable(nextMonth));
        }
        checkInParis.click();
        wait.until(ExpectedConditions.elementToBeClickable(checkOutParis));
        checkOutParis.click();
    }


    public SearchResultPage submitForm() {
        searchButton.click();
        return new SearchResultPage(driver);
    }
}