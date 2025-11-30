 import { handleRegister } from "./handleRegister.mjs";
 
 document.getElementById('registerForm').addEventListener('submit', function(e) {
            e.preventDefault();
            
            const name = document.getElementById('name').value;
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;
                   if (password.length < 6) {
                alert('A senha deve ter no mínimo 6 caracteres');
                return;
            }
            handleRegister.register(email, password, 'LOCAL', name)
                .then(data => {
                    alert('Cadastro realizado com sucesso!');
                    localStorage.setItem('authToken', data.token);

                    window.location.href = 'dashboard.html';
                })
                .catch(error => {
                    alert('Erro no cadastro: ' + error.message);
                    console.error('Erro no cadastro:', error);
                });
            
            
        });