import {Utils} from "./utils";

describe('utils', () => {
  let utils: Utils

  beforeEach(() => {
    utils = new Utils()
  })

  describe('removeQueryParamsFromUrl', () => {
    it('should remove any query params', () => {
      const originalUrl = 'http://www.example.com/test?thing=blah'
      const expectedUrl = 'http://www.example.com/test'

      const actualUrl = utils.removeQueryParamsFromUrl(originalUrl)

      expect(actualUrl).toEqual(expectedUrl)
    });

    it('when there are no params, should not remove anything', () => {
      const originalUrl = 'http://www.example.com/test'
      const expectedUrl = 'http://www.example.com/test'

      const actualUrl = utils.removeQueryParamsFromUrl(originalUrl)

      expect(actualUrl).toEqual(expectedUrl)
    });

    it('when there are no params and no paths, should not remove anything', () => {
      const originalUrl = 'http://www.example.com'
      const expectedUrl = 'http://www.example.com/'

      const actualUrl = utils.removeQueryParamsFromUrl(originalUrl)

      expect(actualUrl).toEqual(expectedUrl)
    });
  })
})
