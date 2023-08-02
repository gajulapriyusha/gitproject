import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.options import Options

@pytest.fixture(scope="module")
def setup():
    # Initialize the Chrome WebDriver
    options = Options()
    options.add_argument("--disable-extensions")
    driver = webdriver.Chrome(options=options)
    driver.maximize_window()
    yield driver
    # Teardown - close the browser after the test
    driver.quit()

def test_launch_url_verify_login_button(setup):
    driver = setup
    # Launch the URL
    url = "https://www.flipkart.com/account/login"
    driver.get(url)

   #Verify Login button is displayed 
    loginloc = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.XPATH, "//a[text()='Login']")))
    assert loginloc.is_displayed(), "Login button is not displayed."

   #Click on Login button
    loginloc.click()
   

def test_request_otp(setup):
    driver = setup
    # Input Phone Number and click on Request OTP button
    phnoloc = WebDriverWait(driver, 10).until(EC.presence_of_element_located((By.XPATH, "//span[text()='Enter Email/Mobile number']/ancestor::div/input")))
    phnoloc.send_keys("9177239866")
    requestotploc = WebDriverWait(driver, 10).until(EC.element_to_be_clickable((By.XPATH, "//button[text()='Request OTP']")))
    requestotploc.click()

def test_verify_message_page(setup):
    # Verify Please enter the OTP sent to MobileNumber Message
    driver = setup
    successmessageloc = WebDriverWait(driver, 10).until(EC.visibility_of_element_located((By.XPATH, "//div[text()='Please enter the OTP sent to']")))
    expected_success_message = "Please enter the OTP sent to"
    assert successmessageloc.text == expected_success_message
