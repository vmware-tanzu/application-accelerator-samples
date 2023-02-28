import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class Utils {
  constructor() {}

  getCurrentUrl = (): string => {
    return window.location.href
  }

  setDestinationUrlTo = (location: string) => {
    window.location.href = location
  }

  removeQueryParamsFromUrl = (urlWithPotentialQueryParams: string): string => {
    const url = new URL(urlWithPotentialQueryParams);
    url.search = '';
    return url.toString();
  }
}
