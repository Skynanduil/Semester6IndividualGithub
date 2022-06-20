import React from "react";
import { useAuth0 } from "@auth0/auth0-react";

async function getAccessToken (){
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

export default getAccessToken;