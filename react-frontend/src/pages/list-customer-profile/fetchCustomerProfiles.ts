import {CustomerProfileService} from "../../api/CustomerProfileService";
import {useQuery} from "@tanstack/react-query";

const service = new CustomerProfileService();

export const useFetchCustomerProfiles = () => useQuery(['customer-profiles'], () => service.getCustomerProfiles());