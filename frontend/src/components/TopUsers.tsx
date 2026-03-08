'use client';

import { useEffect, useState } from 'react';
import { fetchTopUsers } from '@/services/analyticsApi';
import { Users } from 'lucide-react';

export default function TopUsers() {
    const [users, setUsers] = useState<string[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        let active = true;

        async function load() {
            try {
                const data = await fetchTopUsers();
                if (active) setUsers(data);
            } catch {
                // retain last state on error
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
            <div className="flex items-center gap-2 text-slate-700 font-semibold text-base">
                <Users className="w-4 h-4 text-indigo-500" />
                Top API Users
            </div>

            {loading ? (
                <div className="space-y-3">
                    {[...Array(5)].map((_, i) => (
                        <div key={i} className="h-8 animate-pulse rounded-lg bg-slate-100" />
                    ))}
                </div>
            ) : users.length === 0 ? (
                <p className="text-slate-400 text-sm">No data available</p>
            ) : (
                <ol className="space-y-3">
                    {users.map((apiKey, i) => (
                        <li key={apiKey} className="flex items-center gap-3 text-sm">
                            <span className="w-6 h-6 flex items-center justify-center rounded-full bg-indigo-50 text-indigo-600 font-bold text-xs shrink-0">
                                {i + 1}
                            </span>
                            <div className="flex-1 min-w-0">
                                <div className="w-full bg-slate-100 rounded-full h-1.5 mt-1">
                                    <div
                                        className="bg-indigo-500 h-1.5 rounded-full transition-all duration-500"
                                        style={{ width: `${100 - i * (100 / users.length)}%` }}
                                    />
                                </div>
                            </div>
                            <span className="font-mono text-slate-700 truncate max-w-[140px]">{apiKey}</span>
                        </li>
                    ))}
                </ol>
            )}
        </div>
    );
}
