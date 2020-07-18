import { Home } from './home.po';
import { browser, logging } from 'protractor';

describe('model-manager home', () => {
  let home: Home;

  beforeEach(() => {
    home = new Home();
  });

  it('should display app title', () => {
    home.navigateTo();
    expect(home.getHomeTitle().getText()).toEqual('Model Manager');
  });

  it('should display paragraph when disconnected', () => {
    expect(home.getHomeParagraph().getText()).toEqual('Log in to manage your models!');
  });

  it('should display sign in button when disconnected', () => {
    expect(home.getHomeButton().getText()).toEqual('Sign in');
  });

  it('can sign in', () => {
    home.signIn();

    expect(home.getHomeButton().getText()).toEqual('My Models');
  });

  it('should display paragraph when connected', () => {
    home.navigateTo();
    expect(home.getHomeParagraph().getText()).toEqual('Import, edit and deploy your API Models!');
  });

  it('should display models button when connected', () => {
    expect(home.getHomeButton().getText()).toEqual('My Models');
  });

  it('can navigate to My Models', () => {
    home.getHomeButton().click();
    browser.driver.sleep(1000);

    expect(home.getModelListTitle().getText()).toEqual('My API Models');
  });

  // afterEach(async () => {
  //   // Assert that there are no errors emitted from the browser
  //   const logs = await browser.manage().logs().get(logging.Type.BROWSER);
  //   expect(logs).not.toContain(jasmine.objectContaining({
  //     level: logging.Level.SEVERE,
  //   } as logging.Entry));
  // });
});
