import { API_BASE_URL } from '../config.mjs';

document.addEventListener('DOMContentLoaded', async () => {
    const token = localStorage.getItem('authToken');
    
    if (!token) {
        window.location.href = 'index.html';
        return;
    }

    try {
        const response = await fetch(`${API_BASE_URL}/api/user/profile`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Failed to fetch user data');
        }

        const userData = await response.json();
        document.getElementById('userName').textContent = userData.name;
    } catch (error) {
        console.error('Error:', error);
        localStorage.removeItem('token');
        window.location.href = 'index.html';
    }

    document.getElementById('logoutBtn').addEventListener('click', () => {
        localStorage.removeItem('token');
        window.location.href = 'index.html';
    });

    document.getElementById('deleteBtn').addEventListener('click', () => {
        window.location.href = 'delete.html';
    });
});