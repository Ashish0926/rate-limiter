// Analytics TypeScript interfaces — aligned with actual API responses

// GET /analytics/request-rate → {"metric":"requests_per_minute","value":null|number}
export interface RequestRateResponse {
    metric: string;
    value: number | null;
}

// GET /analytics/blocked-rate → {"metric":"blocked_per_minute","value":null|number}
export interface BlockedRateResponse {
    metric: string;
    value: number | null;
}

// GET /analytics/top-users → ["user2","user3","user1"]
export type TopUsersResponse = string[];

// GET /analytics/top-endpoints → ["/api/hello","/api/v1/hello"]
export type TopEndpointsResponse = string[];

export interface TrendDataPoint {
    time: string;
    requests: number;
    blocked: number;
}
