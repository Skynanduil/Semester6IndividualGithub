import API from './API'
import getAccesToken from '../auth/getAccesToken';

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

export function sendMessageToMessageBroker(message){
    let config = await getAuthConfig();
    return API.post('/producer/produce?message=' + message, config);
}

export function createMessageInDB(title, content){
    let config = await getAuthConfig();
    return API.post('/producer/message/create', {title: title, content: content}, config);
}

export function getMessageFromDB(id){
    return API.get('/producer/message/' + id)
}

export function editMessageInDB(id, title, content){
    let config = await getAuthConfig();
    return API.put('/producer/message/edit', {id: id, title: title, content: content}, config);
}

export function deleteMessageInDB(id){
    let config = await getAuthConfig();
    return API.delete('/producer/message/' + id, config);
}