import {getMessage} from "../functions/functions";
import React from "react";

class GetMessage extends React.Component{
    constructor(props){
        super(props);
        this.state = {id: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({id: event.target.value});
    }

    handleSubmit(event){
        event.preventDefault();
        getMessage(this.state.id);
    }

    render(){
        return(
            <form onSubmit={this.handleSubmit}>
                <label>Id: </label>
                <input type="text" value={this.state.id} onChange={this.handleChange}/>
                <button type="submit">Get message from Database</button>
            </form>
        )
    }
}

export default GetMessage;