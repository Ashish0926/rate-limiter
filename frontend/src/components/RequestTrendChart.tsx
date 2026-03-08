'use client';

import { useEffect, useState } from 'react';
import {
    LineChart,
    Line,
    XAxis,
    YAxis,
    CartesianGrid,
    Tooltip,
    Legend,
    ResponsiveContainer,
} from 'recharts';
import { fetchRequestRate, fetchBlockedRate } from '@/services/analyticsApi';
import { TrendDataPoint } from '@/types/analytics';

const MAX_POINTS = 20;

function formatTime(date: Date): string {
    return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', second: '2-digit' });
}

export default function RequestTrendChart() {
    const [data, setData] = useState<TrendDataPoint[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        let active = true;

        async function load() {
            try {
                const [reqData, blockedData] = await Promise.all([
                    fetchRequestRate(),
                    fetchBlockedRate(),
                ]);

                if (!active) return;

                const point: TrendDataPoint = {
                    time: formatTime(new Date()),
                    requests: reqData.value ?? 0,
                    blocked: blockedData.value ?? 0,
                };

                setData((prev) => {
                    const updated = [...prev, point];
                    return updated.length > MAX_POINTS ? updated.slice(updated.length - MAX_POINTS) : updated;
                });
            } catch {
                // keep previous data on error
            } finally {
                if (active) setLoading(false);
            }
        }

        load();
        const id = setInterval(load, 5000);
        return () => { active = false; clearInterval(id); };
    }, []);

    return (
        <div className="bg-white rounded-2xl shadow-md p-6 border border-slate-100 flex flex-col gap-4">
            <h3 className="font-semibold text-slate-700 text-base">Request Trend (last {MAX_POINTS} samples)</h3>

            {loading ? (
                <div className="h-64 animate-pulse rounded-xl bg-slate-100" />
            ) : (
                <ResponsiveContainer width="100%" height={260}>
                    <LineChart data={data} margin={{ top: 5, right: 20, left: 0, bottom: 5 }}>
                        <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
                        <XAxis
                            dataKey="time"
                            tick={{ fontSize: 11, fill: '#94a3b8' }}
                            tickLine={false}
                            axisLine={false}
                        />
                        <YAxis
                            tick={{ fontSize: 11, fill: '#94a3b8' }}
                            tickLine={false}
                            axisLine={false}
                            allowDecimals={false}
                        />
                        <Tooltip
                            contentStyle={{
                                background: '#ffffff',
                                border: '1px solid #e2e8f0',
                                borderRadius: '0.75rem',
                                fontSize: '12px',
                            }}
                        />
                        <Legend wrapperStyle={{ fontSize: '12px' }} />
                        <Line
                            type="monotone"
                            dataKey="requests"
                            name="Requests / min"
                            stroke="#6366f1"
                            strokeWidth={2}
                            dot={false}
                            activeDot={{ r: 4 }}
                        />
                        <Line
                            type="monotone"
                            dataKey="blocked"
                            name="Blocked / min"
                            stroke="#f43f5e"
                            strokeWidth={2}
                            dot={false}
                            activeDot={{ r: 4 }}
                        />
                    </LineChart>
                </ResponsiveContainer>
            )}
        </div>
    );
}
