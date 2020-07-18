import { browser, by, element } from 'protractor';

export class Models {
  navigateTo() {
    return browser.get(browser.baseUrl + "/models");
  }

  getModelsListTitle() {
    return element(by.id('list-title'));
  }

  getModelImportButton() {
    return element(by.id('import-model'));
  }

  getRefreshListButton() {
    return element(by.id('refresh-list'));
  }

  getImportModalTitle() {
    return element(by.css('.model-import h1'));
  }

  getCancelImportButton() {
    return element(by.id('cancel-import'));
  }
}
