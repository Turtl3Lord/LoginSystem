import { API_BASE_URL } from '../config.mjs';

document.addEventListener('DOMContentLoaded', () => {
    const deleteForm = document.getElementById('deleteForm');
    const deleteBtn = document.getElementById('deleteBtn');
    const loadingModal = document.getElementById('loadingModal');

    deleteBtn.addEventListener('click', async (e) => {
        e.preventDefault();

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        if (!email || !password) {
            alert('Por favor, preencha todos os campos');
            return;
        }

        const confirmed = confirm('Tem certeza que deseja deletar sua conta? Esta ação não pode ser desfeita.');
        
        if (!confirmed) {
            return;
        }

        loadingModal.style.display = 'flex';

        try {
            const response = await fetch(`${API_BASE_URL}/api/auth/local/delete`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                })
            });

            const data = await response.json();

            if (response.ok && data.success) {
                alert('Conta deletada com sucesso!');
                localStorage.removeItem('token');
                window.location.href = 'index.html';
            } else {
                alert(data.message || 'Erro ao deletar conta. Verifique suas credenciais.');
            }
        } catch (error) {
            console.error('Erro:', error);
            alert('Erro ao conectar com o servidor');
        } finally {
            loadingModal.style.display = 'none';
        }
    });
});