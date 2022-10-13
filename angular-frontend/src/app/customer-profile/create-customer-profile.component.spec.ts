import {CustomerProfile, CustomerProfileService} from "./customer-profile.service";
import {ComponentFixture, TestBed} from "@angular/core/testing";
import {CreateCustomerProfileComponent} from "./create-customer-profile.component";
import {By} from "@angular/platform-browser";
import {FormsModule} from "@angular/forms";

describe('CreateCustomerProfileComponent', () => {

  let customerProfileService: jasmine.SpyObj<CustomerProfileService>;

  let fixture : ComponentFixture<CreateCustomerProfileComponent>;

  beforeEach(async () => {

    customerProfileService = jasmine.createSpyObj('CustomerProfileService', ['createCustomerProfile']);

    await TestBed.configureTestingModule({
      declarations: [
        CreateCustomerProfileComponent,
      ],
      imports: [
        FormsModule
      ],
      providers: [
        {provide: CustomerProfileService, useValue: customerProfileService},
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(CreateCustomerProfileComponent)
    fixture.detectChanges()
  });

  it('should render the form', () => {

    const firstNameInput = fixture.debugElement.query(By.css('[data-testid="first-name"]'));
    expect(firstNameInput).toBeTruthy()
    const lastNameInput = fixture.debugElement.query(By.css('[data-testid="last-name"]'));
    expect(lastNameInput).toBeTruthy()
    const emailInput = fixture.debugElement.query(By.css('[data-testid="email"]'));
    expect(emailInput).toBeTruthy()
    const submitButton = fixture.nativeElement.querySelector('button');
    expect(submitButton).toBeTruthy()
    expect(submitButton.getAttribute('type')).toBe('button')
  });

  it('should implement first name data binding', () => {

    const firstNameInput = fixture.debugElement.query(By.css('[data-testid="first-name"]')).nativeElement;
    firstNameInput.value = 'Joe';
    firstNameInput.dispatchEvent(new Event('input'));

    fixture.detectChanges()

    expect(fixture.componentInstance.customerProfile.firstName).toEqual("Joe")
  });

  it('should implement last name data binding', () => {

    const firstNameInput = fixture.debugElement.query(By.css('[data-testid="last-name"]')).nativeElement;
    firstNameInput.value = 'Doe';
    firstNameInput.dispatchEvent(new Event('input'));

    fixture.detectChanges()

    expect(fixture.componentInstance.customerProfile.lastName).toEqual("Doe")
  });

  it('should implement email data binding', () => {

    const firstNameInput = fixture.debugElement.query(By.css('[data-testid="email"]')).nativeElement;
    firstNameInput.value = 'joe.doe@example.com';
    firstNameInput.dispatchEvent(new Event('input'));

    fixture.detectChanges()

    expect(fixture.componentInstance.customerProfile.email).toEqual("joe.doe@example.com")
  });

  it('should disable button if profile is invalid', () => {

    const component = fixture.componentInstance
    component.customerProfile.email = null
    fixture.detectChanges()

    const submitButton = fixture.nativeElement.querySelector('button')

    expect(submitButton.getAttribute('disabled')).toBeTruthy()
  });

  it('should enable button if profile is valid', () => {

    const component = fixture.componentInstance
    component.customerProfile.email = 'john.smith@example.com'
    fixture.detectChanges()

    const submitButton = fixture.nativeElement.querySelector('button')

    expect(submitButton.getAttribute('disabled')).not.toBeTruthy()
  });

  it('should call service on button click', () => {

    const component = fixture.componentInstance
    component.customerProfile.firstName = "John"
    component.customerProfile.lastName = "Smith"
    component.customerProfile.email = "john.smith@example.com"
    fixture.detectChanges()

    const submitButton = fixture.nativeElement.querySelector('button')
    submitButton.dispatchEvent(new Event('click'));

    expect(customerProfileService.createCustomerProfile)
      .toHaveBeenCalledOnceWith(new CustomerProfile('', "John", "Smith", "john.smith@example.com"))
  });
});
