import { Models } from './models.po';
import { browser, logging } from 'protractor';

describe('model-manager models', () => {
  let models: Models;

  beforeEach(() => {
    models = new Models();
  });

  it('should display page title', () => {
    models.navigateTo();

    expect(models.getModelsListTitle().getText()).toEqual('My API Models');
  });

  it('should display import button', () => {
    expect(models.getModelImportButton().getText()).toEqual('Import add');
  });

  it('should display refresh button', () => {
    expect(models.getRefreshListButton().getText()).toEqual('Refresh refresh');
  });
  
  it('import modal should display title', () => {
    models.getModelImportButton().click();
    browser.sleep(1000);

    expect(models.getImportModalTitle().getText()).toEqual('Import an API Model');
  });

  it('import modal should display cancel button', () => {
    expect(models.getCancelImportButton().getText()).toEqual('Cancel');
  });

//   afterEach(async () => {
//     // Assert that there are no errors emitted from the browser
//     const logs = await browser.manage().logs().get(logging.Type.BROWSER);
//     expect(logs).not.toContain(jasmine.objectContaining({
//       level: logging.Level.SEVERE,
//     } as logging.Entry));
//   });
});
