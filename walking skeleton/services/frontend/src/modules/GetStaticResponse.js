import React from "react";
import {getStaticResponse} from "../functions/functions";

const GetStaticResponse = () => {
    return (<button onClick={getStaticResponse}>Get a static response from the api</button>);
}

export default GetStaticResponse;