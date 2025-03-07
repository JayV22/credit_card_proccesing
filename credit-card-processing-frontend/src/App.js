import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';

import Header from './components/Header';
import Login from './components/Login';
import Signup from './components/Signup';
import ShoppingCart from './components/ShoppingCart';
import PaymentPage from './components/PaymentPage';

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Header />
        <Routes>
          {/* If user goes to root, go to /cart by default */}
          <Route path="/" element={<Navigate to="/cart" />} />

          {/* Login & Signup */}
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />

          {/* Shopping Cart & Payment */}
          <Route path="/cart" element={<ShoppingCart />} />
          <Route path="/payment" element={<PaymentPage />} />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
