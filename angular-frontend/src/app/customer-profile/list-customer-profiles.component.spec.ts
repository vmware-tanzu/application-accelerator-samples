import { fakeAsync, TestBed, tick } from '@angular/core/testing';
import { MatTableModule } from "@angular/material/table";
import { of, throwError } from "rxjs";
import { ListCustomerProfilesComponent } from "./list-customer-profiles.component";
import { CustomerProfile, CustomerProfileService } from "./customer-profile.service";

describe('ListCustomerProfilesComponent', () => {

  let customerProfileService: jasmine.SpyObj<CustomerProfileService>;

  beforeEach(async () => {

    customerProfileService = jasmine.createSpyObj('CustomerProfileService', ['getCustomerProfiles']);

    await TestBed.configureTestingModule({
      declarations: [
        ListCustomerProfilesComponent,
      ],
      imports: [
        MatTableModule
      ],
      providers: [
         { provide: CustomerProfileService, useValue: customerProfileService },
      ],
    }).compileComponents();
  });

  it('should show the table without customer profile data', () => {

    customerProfileService.getCustomerProfiles.and.returnValue(of([]));

    const fixture = TestBed.createComponent(ListCustomerProfilesComponent)
    fixture.detectChanges()

    const table: HTMLElement = fixture.nativeElement.querySelector('table');
    expect(table).toBeDefined()

    // Header row
    const headerRow = fixture.nativeElement.querySelector('table thead tr');
    validateHeaders(headerRow);

    const rows = fixture.nativeElement.querySelectorAll('table tbody tr');
    expect(rows).toBeDefined()
    expect(rows.length).toBe(0);
  });

  it('should show message when customer profile data cannot be retrieved', fakeAsync(() => {

    customerProfileService.getCustomerProfiles.and.returnValue(
      throwError(() => new Error('Customer Profiles cannot be retried: unit-test network error...')));

    const fixture = TestBed.createComponent(ListCustomerProfilesComponent)
    fixture.detectChanges()

    tick();  // flush the component's setTimeout()
    fixture.detectChanges();  // update couldNotRetrieve within setTimeout()

    const span: HTMLElement = fixture.nativeElement.querySelector('span');
    expect(span).toBeDefined()
    expect(span.innerHTML).toContain('Could not retrieve customer profiles...');
  }));

  it('should fill the table with the customer profile data', () => {

    const data = [
      new CustomerProfile("id123", "Test", "User", "test@user.com"),
      new CustomerProfile("id124", "Piet", "Hein", "piet@hein.com"),
    ];

    customerProfileService.getCustomerProfiles.and.returnValue(of(data));

    const fixture = TestBed.createComponent(ListCustomerProfilesComponent)
    fixture.detectChanges()

    const table: HTMLElement = fixture.nativeElement.querySelector('table');
    expect(table).toBeDefined()

    // Header row
    const headerRow = fixture.nativeElement.querySelector('table thead tr');
    validateHeaders(headerRow);

    const rows = fixture.nativeElement.querySelectorAll('table tbody tr');
    expect(rows).toBeDefined()
    expect(rows.length).toBe(2);

    // Data rows
    const row1 = rows[0];
    expect(row1.cells[0].innerHTML).toContain('id123');
    expect(row1.cells[1].innerHTML).toContain('Test');
    expect(row1.cells[2].innerHTML).toContain('User');
    expect(row1.cells[3].innerHTML).toContain('test@user.com');

    const row2 = rows[1];
    expect(row2.cells[0].innerHTML).toContain('id124');
    expect(row2.cells[1].innerHTML).toContain('Piet');
    expect(row2.cells[2].innerHTML).toContain('Hein');
    expect(row2.cells[3].innerHTML).toContain('piet@hein.com');

    const span: HTMLElement = fixture.nativeElement.querySelector('span');
    expect(span).toBeNull()
  });

  function validateHeaders(headerRow: HTMLTableRowElement) {
    expect(headerRow.cells[0].innerHTML).toContain('#');
    expect(headerRow.cells[1].innerHTML).toContain('First Name');
    expect(headerRow.cells[2].innerHTML).toContain('Last Name');
    expect(headerRow.cells[3].innerHTML).toContain('Email');
  }
});
