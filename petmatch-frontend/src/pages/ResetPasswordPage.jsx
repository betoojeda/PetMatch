import React, { useState } from 'react';
import { useSearchParams, useNavigate, Link } from 'react-router-dom';
import '../App.css';

const ResetPasswordPage = () => {
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');
    const [searchParams] = useSearchParams();
    const navigate = useNavigate();
    const token = searchParams.get('token');

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (password !== confirmPassword) {
            setError('Las contraseñas no coinciden.');
            return;
        }
        setError('');
        setMessage('');

        try {
            const response = await fetch('/api/auth/reset-password', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ token, newPassword: password }),
            });
            if (!response.ok) {
                const errData = await response.json();
                throw new Error(errData.message || 'El token es inválido o ha expirado.');
            }
            setMessage('Tu contraseña ha sido actualizada con éxito.');
            setTimeout(() => navigate('/login'), 3000);
        } catch (err) {
            setError(err.message);
        }
    };

    if (!token) {
        return (
            <div className="auth-container light-theme">
                <div className="auth-form-wrapper">
                    <h2>Token Inválido</h2>
                    <p>No se ha proporcionado un token de reseteo. Por favor, solicita un nuevo enlace.</p>
                    <Link to="/forgot-password">Solicitar de nuevo</Link>
                </div>
            </div>
        );
    }

    return (
        <div className="auth-container light-theme">
            <div className="auth-form-wrapper">
                <h2>Resetear Contraseña</h2>
                <form onSubmit={handleSubmit}>
                    {message && <p style={{ color: 'green' }}>{message}</p>}
                    {error && <p className="error-message">{error}</p>}
                    <div className="input-group">
                        <input
                            type="password"
                            placeholder="Nueva Contraseña"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <div className="input-group">
                        <input
                            type="password"
                            placeholder="Confirmar Nueva Contraseña"
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                            required
                        />
                    </div>
                    <button type="submit" className="auth-button">Actualizar Contraseña</button>
                </form>
            </div>
        </div>
    );
};

export default ResetPasswordPage;
