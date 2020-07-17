import { browser, by, element } from 'protractor';

export class Header {
  navigateTo() {
    return browser.get(browser.baseUrl);
  }

  signIn() {
    this.getHeaderSignIn().click();

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

  signOut() {
    this.getMenuButton().click();
    browser.driver.sleep(1000);

    this.getHeaderSignOut().click();
    browser.driver.sleep(1000);

    browser.driver.findElement(by.css('.tile-container')).click();
    browser.driver.sleep(200);
    browser.driver.sleep(3000);
  }

  getHeaderTitle() {
    return element(by.css('app-header h1'));
  }

  getHeaderSubTitle() {
    return element(by.css('app-header h2'));
  }

  getHeaderSignIn() {
    return element(by.css('app-header .signin'));
  }

  getHeaderSignOut() {
    return element(by.id('signout'));
  }

  getMenuButton() {
    return element(by.id('menuButton'));
  }

  getModelListTitle() {
    return element(by.css('.model-list-header h2'));
  }
}
