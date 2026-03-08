'use client';

import { useEffect, useState } from 'react';
import { fetchRequestRate } from '@/services/analyticsApi';
import { Activity } from 'lucide-react';

export default function RequestRateCard() {
    const [value, setValue] = useState<number | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        let active = true;

        async function load() {
            try {
                const data = await fetchRequestRate();
                if (active) setValue(data.value);
            } catch {
                // keep old value on error
            } finally {
                if (active) setLoading(false);
            }
        }

        load();
        const id = setInterval(load, 5000);
        return () => { active = false; clearInterval(id); };
    }, []);

    return (
        <div className="bg-white rounded-2xl shadow-md p-6 flex flex-col gap-3 border border-slate-100">
            <div className="flex items-center gap-2 text-slate-500 text-sm font-medium uppercase tracking-wider">
                <Activity className="w-4 h-4 text-indigo-500" />
                Requests / min
            </div>
            {loading ? (
                <div className="h-10 w-24 animate-pulse rounded-lg bg-slate-100" />
            ) : (
                <span className="text-5xl font-bold text-slate-800 tabular-nums">
                    {value ?? '—'}
                </span>
            )}
            <p className="text-xs text-slate-400">Refreshes every 5 s</p>
        </div>
    );
}
