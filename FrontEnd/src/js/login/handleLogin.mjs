import { API_BASE_URL } from "../config.mjs";
export const  handleLogin = {
    login: async (email, password, provider = 'LOCAL') => {
        if (provider !== 'LOCAL') {
            window.location.href = '/oauth2/authorization/google';
            return;
        }

        const endpoint = `${API_BASE_URL}/api/auth/local/signin`;

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
