import { browser, by, element } from 'protractor';
import { CONF } from '../conf';

export class Header {
  navigateTo() {
    return browser.get(browser.baseUrl);
  }

  signIn() {
    this.getHeaderSignIn().click();

    var emailInput = browser.driver.findElement(by.id('i0116'));
    emailInput.sendKeys(CONF.email);
    browser.driver.findElement(by.id(CONF.nextButtonId)).click();
    browser.driver.sleep(1000);

    var passwordInput = browser.driver.findElement(by.id('i0118'));
    passwordInput.sendKeys(CONF.password);
    browser.driver.findElement(by.id(CONF.nextButtonId)).click();
    browser.driver.sleep(1000);
    browser.driver.findElement(by.id(CONF.nextButtonId)).click();
    browser.driver.sleep(1000);
  }

  signOut() {
    this.getMenuButton().click();
    browser.driver.sleep(1000);

    this.getHeaderSignOut().click();
    browser.driver.sleep(1000);

    browser.driver.findElement(by.css('.tile-container')).click();
    browser.driver.sleep(500);
    browser.driver.sleep(2000);
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
    return element(by.id('menu-button'));
  }
  
  getUserName() {
    return element(by.id('user-name'));
  }

  getModelListTitle() {
    return element(by.id('list-title'));
  }
}
