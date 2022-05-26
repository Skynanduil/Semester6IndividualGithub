import React from 'react';
import {testAPI} from "../../api/API_Calls";

const TITLE = 'Landing';

const Landing = () => {
    document.title = TITLE;
    return <div>
        <button onClick={handleClick}>Click me</button>
    </div>
};

function handleClick(){
    testAPI()
        .then(res => alert(res.data.text));
}

export default Landing;