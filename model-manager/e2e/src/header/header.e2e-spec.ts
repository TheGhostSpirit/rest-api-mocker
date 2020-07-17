import { Header } from './header.po';
import { browser, logging } from 'protractor';

describe('model-manager header', () => {
  let header: Header;

  beforeEach(() => {
    header = new Header();
  });

  it('should display app title', () => {
    header.navigateTo();
    expect(header.getHeaderTitle().getText()).toEqual('Model Manager');
  });

  it('should display sign in button', () => {
    header.navigateTo();
    expect(header.getHeaderSignIn().getText()).toEqual('Sign in');
  });

  it('can sign in', () => {
    header.navigateTo();
    header.signIn();

    expect(header.getHeaderSubTitle().getText).toEqual('My Models');
  });

  it('can navigate to My Models', () => {
    header.navigateTo();
    header.getHeaderSubTitle().click();
    browser.driver.sleep(3000);

    expect(header.getModelListTitle().getText()).toEqual('My API Models');
  });

  it('can sign out', () => {
    header.navigateTo();
    header.signOut();

    expect(header.getHeaderSignIn().getText()).toEqual('Sign in');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
