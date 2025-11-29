import { handleLogin } from './handleLogin.mjs';
const loginForm = document.getElementById('loginForm');
const loginBtn = document.getElementById('loginBtn');
const googleBtn = document.getElementById('googleBtn');
const loadingModal = document.getElementById('loadingModal');

loginForm.addEventListener('submit', (event) => {
    event.preventDefault();
});

loginBtn.addEventListener('click', () => {
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();

    if (!email || !password) {
        alert('Please fill in both email and password fields.');
        return;
    }

    showLoading();
    performLogin(email, password);
});

googleBtn.addEventListener('click', () => {
    window.location.href = '/oauth2/authorization/google';
});

function showLoading() {
    loadingModal.style.display = 'block';
}

function hideLoading() {
    loadingModal.style.display = 'none';
}

async function performLogin(email, password) {
    try {
        const data = await handleLogin.login(email, password);

        hideLoading();

        if (!data || (!data.success && !data.token)) {
            throw new Error(data?.message || 'Invalid credentials');
        }

        if (data.token) {
            localStorage.setItem('authToken', data.token);
        }

        window.location.href = 'dashboard.html';

    } catch (err) {
        hideLoading();
        alert('Login error: ' + err.message);
        console.error(err);
    }
}

window.addEventListener('click', (event) => {
    if (event.target === loadingModal) {
        hideLoading();
    }
});
