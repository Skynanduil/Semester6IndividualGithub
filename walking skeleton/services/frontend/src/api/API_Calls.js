import API from './API'
import getAccessToken from '../auth/getAccesToken';

const getAuthConfig = async () =>{
    let accessToken = await getAccessToken();
    let config = {
        headers: {
            'Authorization': 'Bearer ' + accessToken
        }
    }

    return config;
}

export function getStaticResponseFromAPI(){
    return API.get('/producer/static')
}

export async function sendMessageToMessageBroker(message){
    let config = await getAuthConfig();
    return API.post('/producer/produce?message=' + message, config);
}

export async function createMessageInDB(title, content){
    let config = await getAuthConfig();
    return API.post('/producer/message/create', {title: title, content: content}, config);
}

export function getMessageFromDB(id){
    return API.get('/producer/message/' + id)
}

export async function editMessageInDB(id, title, content){
    let config = await getAuthConfig();
    return API.put('/producer/message/edit', {id: id, title: title, content: content}, config);
}

export async function deleteMessageInDB(id){
    let config = await getAuthConfig();
    return API.delete('/producer/message/' + id, config);
}