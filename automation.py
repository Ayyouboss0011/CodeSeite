from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time

chrome_driver_path = "C:/Users/techn/Desktop/CodeSeite/chromedriver.exe"
driver = webdriver.Chrome(executable_path=chrome_driver_path)

driver.get("file:///C:/Users/techn/Desktop/CodeSeite/main.html")

codegeknackt = False

while not codegeknackt:
    for i in range(1, 10000):
        current_url = driver.current_url
        formatted_number = f"{i:04d}"

        input_field = driver.find_element("id", "code")
        input_field.send_keys(formatted_number)
        input_field.send_keys(Keys.ENTER)

        next_url = driver.current_url

        if current_url != next_url:
            print("Herzlichen Glückwunsch, der Code wurde geknackt!")
            print(f"Der Code lautet: {formatted_number}")
            codegeknackt = True
            break
        else:
            input_field.clear()

driver.quit()
