import React from 'react';
import {Link, NavLink} from "react-router-dom";
import '../styling/navigation.css';

const NavBar = () =>{
    return(
        <ul className='sidenav'>
            <li><NavLink to='/'>Landing</NavLink></li>
        </ul>
    );
}

export default NavBar;