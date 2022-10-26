import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
// import { OidcClientNotification, OidcSecurityService, OpenIdConfiguration, UserDataResult } from 'angular-auth-oidc-client';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  title = 'angular-frontend';

  constructor(private http: HttpClient) { }

  ngOnInit() {

  }
}
