import axios from 'axios';
import {
    RequestRateResponse,
    BlockedRateResponse,
    TopUsersResponse,
    TopEndpointsResponse,
} from '@/types/analytics';

const BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL || 'http://localhost:8080/v1/analytics';

const api = axios.create({
    baseURL: BASE_URL,
    timeout: 5000,
});

export async function fetchRequestRate(): Promise<RequestRateResponse> {
    const response = await api.get<RequestRateResponse>('/request-rate');
    return response.data;
}

export async function fetchBlockedRate(): Promise<BlockedRateResponse> {
    const response = await api.get<BlockedRateResponse>('/blocked-rate');
    return response.data;
}

export async function fetchTopUsers(): Promise<TopUsersResponse> {
    const response = await api.get<TopUsersResponse>('/top-users');
    return response.data;
}

export async function fetchTopEndpoints(): Promise<TopEndpointsResponse> {
    const response = await api.get<TopEndpointsResponse>('/top-endpoints');
    return response.data;
}
