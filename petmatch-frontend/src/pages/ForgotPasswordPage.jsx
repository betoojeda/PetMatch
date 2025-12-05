import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../App.css';

const ForgotPasswordPage = () => {
    const [email, setEmail] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage('');
        setError('');
        try {
            const response = await fetch('/api/auth/forgot-password', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email }),
            });
            if (!response.ok) {
                throw new Error('El correo no fue encontrado.');
            }
            setMessage('Si el correo está registrado, recibirás un enlace para resetear tu contraseña.');
        } catch (err) {
            setError(err.message);
        }
    };

    return (
        <div className="auth-container light-theme">
            <div className="auth-form-wrapper">
                <h2>Recuperar Contraseña</h2>
                <p>Introduce tu correo electrónico y te enviaremos un enlace para resetear tu contraseña.</p>
                <form onSubmit={handleSubmit}>
                    {message && <p style={{ color: 'green' }}>{message}</p>}
                    {error && <p className="error-message">{error}</p>}
                    <div className="input-group">
                        <input
                            type="email"
                            placeholder="Correo electrónico"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit" className="auth-button">Enviar</button>
                    <p style={{ marginTop: '1rem' }}>
                        <Link to="/login">Volver a Iniciar Sesión</Link>
                    </p>
                </form>
            </div>
        </div>
    );
};

export default ForgotPasswordPage;
