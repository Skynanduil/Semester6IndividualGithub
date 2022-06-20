import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Auth0Provider } from "@auth0/auth0-react";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Auth0Provider
    domain='dev-1pg2sjy3.eu.auth0.com'
    clientId='t53cjDwFlsNVKis1C5jMtOav3JL1IjkV'
    redirectUri={window.location.origin}
    audience='https://authenticatemyapp/'>
    <React.StrictMode>
      <App />
    </React.StrictMode>
  </Auth0Provider>
);

reportWebVitals();
