import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

@pytest.fixture(scope="module")
def setup():
    # Initialize the Chrome WebDriver
    driver = webdriver.Chrome()
    driver.maximize_window()
    yield driver
    # Teardown - close the browser after the test
    driver.quit()
    
def test_launch_url_verify_pagetitle(setup):
    driver = setup
    url = "https://automationpanda.com/2021/12/29/want-to-practice-test-automation-try-these-demo-sites/"
    # Launch the URL and Verify Page Title
    driver.get(url)
    expected_title = "Want to practice test automation? Try these demo sites! | Automation Panda"
    assert expected_title == driver.title

def test_verify_speaking_page(setup):
    driver = setup
    # Click on Speaking link and Verify the title of the Speaking Page
    speakingloc = driver.find_element(By.XPATH, "//a[contains(text(),'Speaking')]")
    speakingloc.click()
    driver.implicitly_wait(10)
    expected_speaking_title = "Speaking | Automation Panda"
    assert driver.title == expected_speaking_title

def test_verify_keynote_addresses(setup):
    driver = setup
    # Verify Keynote Addresses and Keynote Text in the page
    driver.implicitly_wait(10)
    keynoteloc = driver.find_element(By.XPATH, "//h2[contains(text(),'Keynote Addresses')]")
    assert keynoteloc.is_displayed(), "Keynote Addresses element is not present on the page"
    assert keynoteloc.text == "Keynote Addresses"

def test_verify_conference_talks(setup):
    driver = setup
    # Verify Conference Talks and Conference Text in the Page
    conferenceloc = driver.find_element(By.XPATH, "//h2[@id='conferences']")
    assert conferenceloc.is_displayed(),"Conference Talks element is not present on the page"
    assert conferenceloc.text == "Conference Talks"
