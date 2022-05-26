import React from 'react';
import './styling/App.css';
import NavBar from './components/NavBar';
import {BrowserRouter, Route, Routes} from 'react-router-dom';
import history from './utils/history';
import Landing from './components/pages/Landing';

const App = () => {
  return (
    <BrowserRouter history={history}>
      <div className="App">
        <NavBar/>
        <div className="root-container">
          <Routes>
            <Route element={<Landing/>} exact path ="/" />
          </Routes>
        </div>
    </div>
    </BrowserRouter>
  );
}

export default App;
