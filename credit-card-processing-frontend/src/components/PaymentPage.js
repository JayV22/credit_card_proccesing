import React, { useState, useContext, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

function PaymentPage() {
  const { user } = useContext(AuthContext);
  const navigate = useNavigate();

  // Always call hooks at the top level
  const [formData, setFormData] = useState({
    email: '',
    identification: '',
    cardHolder: '',
    expiryDate: '',
    cardNumber: '',
    cvv: '',
    installments: '1'
  });

  // Use an effect to handle navigation if not logged in
  useEffect(() => {
    if (!user) {
      navigate('/login');
    }
  }, [user, navigate]);

  // If user is not available, render nothing (or a loading indicator)
  if (!user) {
    return null;
  }

  // Handle input changes with custom formatting
  const handleChange = (e) => {
    const { name, value } = e.target;
    let newValue = value;

    switch (name) {
      case 'cardHolder':
        // Only letters and spaces
        newValue = newValue.replace(/[0-9]+/g, '');
        break;
      case 'cardNumber':
        // Remove non-digits, limit to 12 digits
        let digits = newValue.replace(/\D/g, '').slice(0, 12);
        // Insert spaces every 4 digits => XXXX XXXX XXXX
        const parts = [];
        for (let i = 0; i < digits.length; i += 4) {
          parts.push(digits.substring(i, i + 4));
        }
        newValue = parts.join(' ');
        break;
      case 'cvv':
        // Exactly 3 digits
        newValue = newValue.replace(/\D/g, '').slice(0, 3);
        break;
      case 'expiryDate':
        // Allow only digits and slash
        newValue = newValue.replace(/[^0-9/]/g, '');
        // Auto-insert slash after 2 digits if not present
        if (newValue.length === 2 && !newValue.includes('/')) {
          newValue += '/';
        }
        // Limit total length to 5 => MM/YY
        newValue = newValue.slice(0, 5);
        break;
      default:
        break;
    }

    setFormData((prev) => ({ ...prev, [name]: newValue }));
  };

  // Handle the Pay button click
  const handlePayment = (e) => {
    e.preventDefault();

    // Simple example: Base64 "encrypt" the CVV
    const encryptedCVV = btoa(formData.cvv);

    alert(
      `Payment processed!\n\n` +
        `Email: ${formData.email}\n` +
        `ID: ${formData.identification}\n` +
        `Card Holder: ${formData.cardHolder}\n` +
        `Expiry: ${formData.expiryDate}\n` +
        `Card Number: ${formData.cardNumber}\n` +
        `CVV (encrypted): ${encryptedCVV}\n` +
        `Installments: ${formData.installments}`
    );
  };

  return (
    <div className="container">
      <h2>Card Payment</h2>
      <p className="subheader">
        This is an example of a Mercado Pago integration
      </p>

      {/* Summary box */}
      <div className="summary">
        <h3>Summary</h3>
        <p>
          Book x 1 <span className="price">$10</span>
        </p>
        <p>
          Total <span className="price">$10</span>
        </p>
      </div>

      {/* Buyer Details */}
      <div className="buyer-details">
        <h3>Buyer Details</h3>
        <div className="form-group">
          <label>E-mail</label>
          <input
            type="email"
            name="email"
            placeholder="Enter your email"
            value={formData.email}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>Identification number</label>
          <input
            type="text"
            name="identification"
            placeholder="Enter your ID number"
            value={formData.identification}
            onChange={handleChange}
          />
        </div>
      </div>

      {/* Card Details */}
      <div className="card-details">
        <h3>Card Details</h3>
        <div className="form-group">
          <label>Holder name</label>
          <input
            type="text"
            name="cardHolder"
            placeholder="Card holder name"
            value={formData.cardHolder}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>MM/YY</label>
          <input
            type="text"
            name="expiryDate"
            placeholder="MM/YY"
            value={formData.expiryDate}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>Card number</label>
          <input
            type="text"
            name="cardNumber"
            placeholder="XXXX XXXX XXXX"
            value={formData.cardNumber}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>CVV</label>
          <input
            type="password"
            name="cvv"
            placeholder="CVV"
            value={formData.cvv}
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label>Installments</label>
          <select
            name="installments"
            value={formData.installments}
            onChange={handleChange}
          >
            <option value="1">1</option>
            <option value="2">2</option>
          </select>
        </div>
      </div>

      <button className="pay-button" onClick={handlePayment}>
        Pay
      </button>

      <div className="back-link">
        <Link to="/cart">Go back to Shopping Cart</Link>
      </div>
    </div>
  );
}

export default PaymentPage;
