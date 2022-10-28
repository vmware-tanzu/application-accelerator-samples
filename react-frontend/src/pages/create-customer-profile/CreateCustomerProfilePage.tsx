import React, {useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import { CustomerProfile } from "../../api/CustomerProfileDomain";
import PageHeader from "../../components/PageHeader";
import './CreateCustomerProfilePage.css';
import CreateCustomerProfileForm from "./CreateCustomerProfileForm";
import {CustomerProfileService} from "../../api/CustomerProfileService";

const service = new CustomerProfileService();

function CreateCustomerProfile() {

    const navigate = useNavigate()

    const [error, setError] = useState<string>('')

    async function handleSubmit(profile: CustomerProfile) {
        setError('')

        try {
            await service.createCustomerProfile(profile)
            navigate("/customer-profiles/list")
        } catch (error) {
            if (axios.isAxiosError(error)) {
                setError(error.message)
            } else {
                setError('An unexpected error occurred: ' + error)
            }
        }
    }

    return (
        <>
            <PageHeader text={"Create Customer Profile"}/>
            <CreateCustomerProfileForm onSubmit={handleSubmit} error={error} />
        </>
    )
}

export default CreateCustomerProfile;
