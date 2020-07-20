import { browser, by, element } from 'protractor';
import { CONF } from '../conf';

export class Home {
  navigateTo() {
    return browser.get(browser.baseUrl);
  }

  signIn() {
    this.getHomeButton().click();
    browser.driver.sleep(1000);

    browser.driver.findElement(by.css('.tile-container')).click();
    browser.driver.sleep(1000);

    var passwordInput = browser.driver.findElement(by.id('i0118'));
    passwordInput.sendKeys(CONF.password);
    browser.driver.findElement(by.id(CONF.nextButtonId)).click();
    browser.driver.sleep(1000);
    browser.driver.findElement(by.id(CONF.nextButtonId)).click();
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
    return element(by.id('list-title'));
  }
}
