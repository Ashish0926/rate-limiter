import RequestRateCard from '@/components/RequestRateCard';
import BlockedRateCard from '@/components/BlockedRateCard';
import RequestTrendChart from '@/components/RequestTrendChart';
import TopUsers from '@/components/TopUsers';
import TopEndpoints from '@/components/TopEndpoints';
import { Activity } from 'lucide-react';

export const metadata = {
    title: 'Dashboard — Rate Limiter',
};

export default function DashboardPage() {
    return (
        <div className="min-h-screen bg-slate-50">
            {/* Header */}
            <header className="sticky top-0 z-10 bg-white/80 backdrop-blur-sm border-b border-slate-200 px-6 py-4">
                <div className="max-w-7xl mx-auto flex items-center gap-3">
                    <div className="bg-indigo-600 text-white rounded-lg p-1.5">
                        <Activity className="w-5 h-5" />
                    </div>
                    <div>
                        <h1 className="text-lg font-semibold text-slate-900 leading-tight">Rate Limiter Dashboard</h1>
                        <p className="text-xs text-slate-400">Real-time API analytics · auto-refreshes every 5 s</p>
                    </div>
                </div>
            </header>

            <main className="max-w-7xl mx-auto px-6 py-8 space-y-8">
                {/* Section 1 — Metric Cards */}
                <section>
                    <h2 className="text-xs font-semibold uppercase tracking-widest text-slate-400 mb-4">
                        Live Metrics
                    </h2>
                    <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
                        <RequestRateCard />
                        <BlockedRateCard />
                    </div>
                </section>

                {/* Section 2 — Trend Chart */}
                <section>
                    <h2 className="text-xs font-semibold uppercase tracking-widest text-slate-400 mb-4">
                        Request Trend
                    </h2>
                    <RequestTrendChart />
                </section>

                {/* Section 3 — Top Users & Endpoints */}
                <section>
                    <h2 className="text-xs font-semibold uppercase tracking-widest text-slate-400 mb-4">
                        Traffic Breakdown
                    </h2>
                    <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
                        <TopUsers />
                        <TopEndpoints />
                    </div>
                </section>
            </main>

            <footer className="text-center text-xs text-slate-300 py-6">
                Rate Limiter UI · {new Date().getFullYear()}
            </footer>
        </div>
    );
}
