import React from "react";
import { useAuth0 } from "@auth0/auth0-react";

const getAccesToken = async () =>{
    const { getAccessTokenSilently } = useAuth0();
    try {
        const accessToken = await getAccessTokenSilently({
            audience: `https://authenticatemyapp/`,
            scope: "read:current_user",
        });

        return accessToken;
    } catch(e){
        console.log(e.message);
    }
}

export default getAccesToken;