import React from "react";
import {createMessage} from "../functions/functions";

class CreateMessage extends React.Component{
    constructor(props){
        super(props);
        this.state = {title: 'Hello World', content: 'Hello World'};

        this.handleTitleChange = this.handleTitleChange.bind(this);
        this.handleContentChange = this.handleContentChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleTitleChange(event) {
        this.setState({title: event.target.value});
    }

    handleContentChange(event) {
        this.setState({content: event.target.value});
    }

    handleSubmit(event){
        event.preventDefault();
        createMessage(this.state.title, this.state.content);
    }

    render(){
        return(
            <form onSubmit={this.handleSubmit}>
                <label>Title: </label>
                <input type="text" value={this.state.title} onChange={this.handleTitleChange}/>
                <label>Content: </label>
                <input type="text" value={this.state.content} onChange={this.handleContentChange}/>
                <button type="submit">Create message and store in database</button>
            </form>
        )
    }
}

export default CreateMessage;