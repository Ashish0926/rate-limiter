# Rate Limiter UI

A modern Next.js 14 analytics dashboard for visualising real-time metrics from the Rate Limiter Spring Boot backend.

## Features

- **Live metric cards** — requests/min and blocked requests/min
- **Live trend chart** — dual-line chart showing both metrics over the last 20 polling samples
- **Top API users** — ranked list with relative progress bars
- **Top endpoints** — ranked list with relative progress bars
- Auto-refreshes every **5 seconds**

## Tech Stack

| Tool | Purpose |
|---|---|
| Next.js 14 (App Router) | Framework |
| TypeScript | Type safety |
| Tailwind CSS | Styling |
| Recharts | Charts |
| Axios | HTTP client |
| Lucide React | Icons |

## Project Structure

```
src/
├── app/
│   ├── dashboard/
│   │   └── page.tsx          # Main dashboard page
│   ├── globals.css
│   └── layout.tsx
├── components/
│   ├── BlockedRateCard.tsx   # Blocked requests/min card
│   ├── RequestRateCard.tsx   # Requests/min card
│   ├── RequestTrendChart.tsx # Dual-line trend chart
│   ├── TopEndpoints.tsx      # Top endpoints list
│   └── TopUsers.tsx          # Top API users list
├── services/
│   └── analyticsApi.ts       # Typed API client
└── types/
    └── analytics.ts          # TypeScript interfaces
```

## Prerequisites

- **Node.js** ≥ 18
- **Rate Limiter Spring Boot backend** running on `http://localhost:8080`

## Getting Started

### 1. Clone / navigate to the project

```bash
cd rate-limiter
```

### 2. Install dependencies

```bash
npm install
```

### 3. Configure environment

Copy the sample env file (already included):

```bash
# .env.local already contains:
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080/v1/analytics
```

Edit `.env.local` if your backend runs on a different host/port.

### 4. Run the dev server

```bash
npm run dev
```

Open [http://localhost:3000](http://localhost:3000) — it will redirect to `/dashboard` automatically.

### 5. Build for production

```bash
npm run build
npm start
```

## API Endpoints Consumed

| Endpoint | Description |
|---|---|
| `GET /analytics/request-rate` | Requests in the current minute |
| `GET /analytics/blocked-rate` | Blocked requests in the current minute |
| `GET /analytics/top-users` | API keys ranked by request volume |
| `GET /analytics/top-endpoints` | Endpoints ranked by usage |

## Environment Variables

| Variable | Default                              | Description |
|---|--------------------------------------|---|
| `NEXT_PUBLIC_API_BASE_URL` | `http://localhost:8080/v1/analytics` | Base URL for the analytics backend |
