import { dashboardRequests } from "./dashboardRequests.mjs";
document.onload = (() => {
    console.log('Dashboard loaded');
    const token = localStorage.getItem('authToken');   
    if (!token) {
        window.location.href = 'index.html';
        return;
    }
    dashboardRequests.fetchUserData(token).then(data => {
        const name = data?.name || 'User';
        const userNameElem = document.getElementById('userName');
        userNameElem.textContent = `Welcome, ${name}!`;
        userNameElem.textContent = `Welcome, ${name}!`;
    }).catch(err => {
        console.error('Error fetching user data:', err);
        localStorage.removeItem('authToken');
        window.location.href = 'index.html';
    });
})();

    