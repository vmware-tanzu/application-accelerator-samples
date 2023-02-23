import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfileComponent } from './user-profile.component';
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {UserProfileService} from "./user-profile.service";

describe('UserProfileComponent', () => {
  let component: UserProfileComponent;
  let fixture: ComponentFixture<UserProfileComponent>;
  let userProfileService: jasmine.SpyObj<UserProfileService>;

  beforeEach(async () => {
    userProfileService = jasmine.createSpyObj('UserProfileService', ['getEmail', 'getSub']);

    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ UserProfileComponent ],
      providers: [
        { provide: UserProfileService, useValue: userProfileService }
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(UserProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set sub and email when component is initialized', async () => {
    const expectedSub = '123456789';
    const expectedEmail = 'john@doe.com';

    userProfileService.getEmail.and.resolveTo(expectedEmail);
    userProfileService.getSub.and.resolveTo(expectedSub);
    await component.ngOnInit();

    expect(userProfileService.getEmail).toHaveBeenCalled();
    expect(userProfileService.getSub).toHaveBeenCalled();
    expect(component.email).toEqual(expectedEmail);
    expect(component.sub).toEqual(expectedSub);
  });

  it('should set sub and email to an empty string if they are not returned by the service', async () => {
    userProfileService.getEmail.and.resolveTo();
    userProfileService.getSub.and.resolveTo();
    await component.ngOnInit();

    expect(userProfileService.getEmail).toHaveBeenCalled();
    expect(userProfileService.getSub).toHaveBeenCalled();
    expect(component.email).toEqual('');
    expect(component.sub).toEqual('');
  });
});
