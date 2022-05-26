import React from "react";
import {sendMessageToBroker} from "../functions/functions";

class SendMessageToBroker extends React.Component{
    constructor(props){
        super(props);
        this.state = {value: 'Hello World'};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event){
        event.preventDefault();
        sendMessageToBroker(this.state.value);
    }

    render(){
        return(
            <form onSubmit={this.handleSubmit}>
                <input type="text" value={this.state.value} onChange={this.handleChange}/>
                <button type="submit">Send message to broker</button>
            </form>
        )
    }
}

export default SendMessageToBroker;