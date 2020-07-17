import { browser, by, element } from 'protractor';

export class Home {
  navigateTo() {
    return browser.get(browser.baseUrl);
  }

  signIn() {
    this.getHomeButton().click();

    var emailInput = browser.driver.findElement(by.id('i0116'));
    emailInput.sendKeys('baba@quentincariteyoutlook.onmicrosoft.com');
    browser.driver.findElement(by.id('idSIButton9')).click();
    browser.driver.sleep(1000);

    var passwordInput = browser.driver.findElement(by.id('i0118'));
    passwordInput.sendKeys('sucePUTE1/');
    browser.driver.findElement(by.id('idSIButton9')).click();
    browser.driver.sleep(1000);
    browser.driver.findElement(by.id('idSIButton9')).click();
    browser.driver.sleep(1000);
  }

  getHomeTitle() {
    return element(by.css('app-home h1'));
  }

  getHomeParagraph() {
    return element(by.css('app-home p'));
  }

  getHomeButton() {
    return element(by.css('app-home button'));
  }

  getModelListTitle() {
    return element(by.css('.model-list-header h2'));
  }
}
