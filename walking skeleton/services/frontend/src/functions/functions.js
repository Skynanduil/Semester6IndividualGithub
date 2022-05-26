import {
    createMessageInDB, deleteMessageInDB, editMessageInDB,
    getMessageFromDB,
    getStaticResponseFromAPI,
    sendMessageToMessageBroker
} from '../api/API_Calls'

export function getStaticResponse(){
    getStaticResponseFromAPI().then(
        res => alert(res.data)
    )
}

export const sendMessageToBroker = (message) => {
    sendMessageToMessageBroker(message).then(
        res => alert("The message [" + message + "] has been sent.")
    )
}

export const createMessage = (title, content) => {
    createMessageInDB(title, content).then(
        res => alert("The message with title [" + title + "] and content [" + content +"] was created and stored to the database." +
            "\nThe generated UUID = [" + res.data.id + "].")
    )
}

export const getMessage = (id) => {
    getMessageFromDB(id).then(
        res => alert("The message has been received.\n" +
            "Title: " + res.data.title +
            "\nContent: " + res.data.content +
            "\nUUID: " + res.data.id +
            "\nDate published: " + res.data.datePublished)
    )
}

export const editMessage = (id, title, content) => {
    editMessageInDB(id, title, content).then(
        res => alert("The message has been edited. \n"+
            "Title: " + res.data.title +
            "\nContent: " + res.data.content +
            "\nUUID: " + res.data.id +
            "\nDate published: " + res.data.datePublished)
    )
}

export const deleteMessage = (id) => {
    deleteMessageInDB(id).then(
        res => alert("Message with id [" + id + "] has been deleted.")
    )
}