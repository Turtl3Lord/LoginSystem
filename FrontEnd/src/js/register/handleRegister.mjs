import { API_BASE_URL } from "../config.mjs";

export const handleRegister = {
    register: async (email, password, provider, name = 'LOCAL') => {
        try {
            // Redireciona para OAuth se não for LOCAL
            if (provider !== 'LOCAL') {
                window.location.href = '/oauth2/authorization/google';
                return;
            }

            const endpoint = `${API_BASE_URL}/api/auth/local/signup`;
            
            const response = await fetch(endpoint, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    email,
                    password,
                    provider,
                    name
                })
            });

            // Tenta obter os dados da resposta
            let responseData;
            try {
                responseData = await response.json();
            } catch {
                responseData = { message: await response.text() };
            }

            if (!response.ok) {
                // Mensagens de erro baseadas no status HTTP
                let errorMessage;
                
                switch (response.status) {
                    case 400:
                        errorMessage = responseData.message || 'Dados inválidos. Verifique os campos.';
                        break;
                    case 409:
                        errorMessage = 'Este e-mail já está cadastrado.';
                        break;
                    case 500:
                        errorMessage = 'Erro no servidor. Tente novamente mais tarde.';
                        break;
                    default:
                        errorMessage = responseData.message || 'Erro ao realizar cadastro.';
                }
                
                throw new Error(errorMessage);
            }

            return responseData;

        } catch (error) {
            // Se for erro de rede
            if (error.message === 'Failed to fetch') {
                throw new Error('Erro de conexão. Verifique sua internet.');
            }
            
            // Repassa o erro
            throw error;
        }
    }
};

// Exemplo de uso:
/*
try {
    const result = await handleRegister.register(email, password, 'LOCAL', name);
    alert('Cadastro realizado com sucesso!');
    window.location.href = '/dashboard.html';
} catch (error) {
    alert(error.message);
}
*/