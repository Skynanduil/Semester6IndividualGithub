import React from "react";
import {Route} from "react-router-dom";

const PrivateRoute = ({component, path}) => {
    const auth= true;
    return auth ? <Route component={component} path={path}/> : <h1>Loading...</h1>
}