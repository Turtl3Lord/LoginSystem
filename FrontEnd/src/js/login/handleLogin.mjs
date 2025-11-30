const API_BASE = "https://loginsystem-1-23tz.onrender.com/api";

export const  handleLogin = {
    login: async (email, password, provider = 'LOCAL') => {
        if (provider !== 'LOCAL') {
            window.location.href = '/oauth2/authorization/google';
            return;
        }

        const endpoint = `${API_BASE}/auth/signin`;

        const response = await fetch(endpoint, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                email,
                password,
                provider
            })
        });

        if (!response.ok) {
            const text = await response.text();
            throw new Error(`Login failed: ${response.status} → ${text}`);
        }

        return await response.json();
    },

    logout: () => {
        localStorage.removeItem('authToken');
    }
};
