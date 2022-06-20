import API from './API'

export function getStaticResponseFromAPI(){
    return API.get('/producer/static')
}

export function sendMessageToMessageBroker(message){
    return API.post('/producer/produce?message=' + message)
}

export function createMessageInDB(title, content){
    return API.post('/producer/message/create', {title: title, content: content})
}

export function getMessageFromDB(id){
    return API.get('/producer/message/' + id)
}

export function editMessageInDB(id, title, content){
    return API.put('/producer/message/edit', {id: id, title: title, content: content})
}

export function deleteMessageInDB(id){
    return API.delete('/producer/message/' + id)
}