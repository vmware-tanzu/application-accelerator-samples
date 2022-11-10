import React from 'react';
import {render, screen} from '@testing-library/react';
import { MemoryRouter } from 'react-router-dom';
import App from './App';

test('renders the application', () => {
  const Component = render(<MemoryRouter><App/></MemoryRouter>);

  // Has a navigation
  expect(Component.getByRole("navigation")).toBeTruthy();
});
