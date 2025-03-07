import React, { useContext } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

const ShoppingCart = () => {
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  // If not logged in, redirect to /login
  if (!user) {
    navigate('/login');
    return null;
  }

  return (
    <div className="container">
      <h2>Shopping Cart</h2>
      <p className="subheader">This is an example of a Mercado Pago integration</p>

      <div className="cart-container">
        <div className="product-info">
          <img
            src="https://m.media-amazon.com/images/I/51Z0nLAfLmL._SX324_BO1,204,203,200_.jpg"
            alt="Book"
            className="product-image"
          />
          <div className="product-details">
            <h3>How to Win Friends & Influence People</h3>
            <p>Author: Dale Carnegie</p>
            <p>Number of pages: 336</p>
            <p>Price: $10</p>
          </div>
        </div>

        <div className="cart-summary">
          <p className="subtotal-label">Subtotal:</p>
          <p className="subtotal-price">$10</p>
          <Link to="/payment" className="checkout-button">
            Checkout
          </Link>
        </div>
      </div>
    </div>
  );
};

export default ShoppingCart;
