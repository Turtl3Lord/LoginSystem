
const API_BASE = "https://loginsystem-1-23tz.onrender.com/api";

export const  dashboardRequests = {

    fetchUserData: async (token) => {
    console.log('Fetching user data with token:', token);
    const endpoint = `${API_BASE}/user/profile`;

    const response = await fetch(endpoint, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });

    console.log('Response status:', response.status);

    if (!response.ok) {
        const text = await response.text();
        throw new Error(`Fetch user data failed: ${response.status} → ${text}`);
    }

    return await response.json();   // ✔ AQUI
}

}