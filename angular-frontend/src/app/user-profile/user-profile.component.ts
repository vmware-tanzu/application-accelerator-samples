import {Component, OnInit} from '@angular/core';
import {UserProfileService} from "./user-profile.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  sub!: string;
  email!: string;

  constructor(private userProfileService: UserProfileService) {}

  async ngOnInit() {
    this.email = (await this.userProfileService.getEmail()) || '';
    this.sub = (await this.userProfileService.getSub()) || '';
  }
}
