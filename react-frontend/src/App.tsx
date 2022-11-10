import React from 'react';
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import { Routes, Route } from "react-router-dom";
import CustomerProfiles from "./pages/list-customer-profile/CustomerProfiles";
import CreateCustomerProfile from './pages/create-customer-profile/CreateCustomerProfilePage';
import Layout from './Layout';

const queryClient = new QueryClient()

export default function App() {
    return (
    <QueryClientProvider client={queryClient}>
      <Routes>
          <Route path="/" element={<Layout />}>
              <Route index element={<CustomerProfiles />} />
              <Route path="customer-profiles/list" element={<CustomerProfiles />} />
              <Route path="customer-profiles/create" element={<CreateCustomerProfile />} />
          </Route>
      </Routes>
    </QueryClientProvider>
  );
}